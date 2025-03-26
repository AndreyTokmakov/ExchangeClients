package impl.client.config;

import feign.Logger;
import feign.RequestInterceptor;
import impl.utils.HmacUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class GateIOSignatureGeneratorConfig
{
    RequestInterceptor requestInterceptor(GateIOConfigProperties.AccountConfig accountConfig) {
        return requestTemplate -> {
            long timestamp = 1742997517;// System.currentTimeMillis() / 1000;
            String path = requestTemplate.path();
            String queryLine = requestTemplate.queryLine().substring(1);
            String hashedPayload = encodePayload(requestTemplate.body());

            var signatureContent = requestTemplate.method() + path + queryLine + timestamp;
            if (requestTemplate.body() != null) {
                signatureContent += new String(requestTemplate.body(), StandardCharsets.UTF_8);
            }

            requestTemplate.header("KEY", accountConfig.getKey());
            requestTemplate.header("SIGN", getSignature(signatureContent, accountConfig.getSecret()));
            requestTemplate.header("Timestamp", String.valueOf(timestamp));


            System.out.println("=".repeat(160));
            String signatureString = requestTemplate.method() + "\n" + requestTemplate.path() + "\n"  + queryLine
                    + "\n" + hashedPayload + "\n" + timestamp;
            System.out.println(signatureString);

            System.out.println("=".repeat(160));

            // Hex.encodeHexString(HmacUtils.getSha256(signatureContent, secretKey))
            // String signature = HmacUtils.getSha256Str(signatureString, accountConfig.getSecret());
            // System.out.println(signature);

            String signature = "";
            try {
                signature = Hex.encodeHexString(getSha512(signatureString,  accountConfig.getSecret()));
            }
            catch (Exception exc) {
                System.out.println("************ ERROR ***********");
            }
            System.out.println(signature);

            System.out.println("=".repeat(160));

            // var str1 = HmacUtils.getSha256Str("message", accountConfig.getSecret());
            // var str2 = Hex.encodeHexString(HmacUtils.getSha256(signatureContent, accountConfig.getSecret()));
        };
    }

    private String encodePayload(byte[] payload)
    {
        final String payloadString = payload != null  ? new String(payload) : "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(payloadString.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getSha512(String message, String secret)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        var hacSha512 = Mac.getInstance("HmacSHA512");
        var secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hacSha512.init(secretKeySpec);
        return hacSha512.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    private String getSignature(String signatureContent, String secretKey) {
        return Hex.encodeHexString(HmacUtils.getSha256(signatureContent, secretKey));
    }

    final static class OneLineLogger extends Logger {
        @Override
        protected void log(String configKey, String format, Object... args)  {
            System.err.printf(configKey + " - " + format + "%n", args);
        }
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Logger logger() {
        return new OneLineLogger();
    }


    /*
    private static String getSignature() {
        var body = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        var queryString = request.getQueryString();
        var message = String.valueOf(timestamp)
                .concat(request.getMethod())
                .concat(request.getRequestURI())
                .concat((queryString != null ? queryString : ""))
                .concat((body != null && !body.equals("null") ? body : ""));
        return HmacUtils.getSha256Str(message, apiKey);
    }*/
}
