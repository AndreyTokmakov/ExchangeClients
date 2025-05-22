package impl.client.config;

import feign.Logger;
import feign.RequestInterceptor;
import impl.utils.HmacUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    @Getter
    @RequiredArgsConstructor
    enum HMacEncodeType
    {
        Sha256("HmacSHA256"),
        Sha512("HmacSHA512");

        private final String algorithmName;

        @Override
        public String toString(){
            return algorithmName;
        }
    }

    RequestInterceptor requestInterceptor(GateIOConfigProperties.AccountConfig accountConfig)
    {
        return requestTemplate ->
        {
            long timestamp = System.currentTimeMillis() / 1000;
            String path = requestTemplate.path();
            String queryLine = requestTemplate.queryLine().substring(1);
            String hashedPayload = encodePayload(requestTemplate.body());

            String signatureString = requestTemplate.method() + "\n" + path + "\n"  + queryLine
                    + "\n" + hashedPayload + "\n" + timestamp;
            String signature = "";
            try {
                signature = Hex.encodeHexString(getSha512(signatureString,  accountConfig.getSecret()));
            }
            catch (Exception exc) {
                System.out.println("************ ERROR ***********");
            }

            requestTemplate.header("KEY", accountConfig.getKey());
            requestTemplate.header("SIGN", signature);
            requestTemplate.header("Timestamp", String.valueOf(timestamp));
        };
    }

    private String encodePayload(byte[] payload)
    {
        final String payloadString = payload != null  ? new String(payload) : "";
        try {
            MessageDigest digest  = MessageDigest.getInstance("SHA-512");
            digest.update(payloadString.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getSha512(String message, String secret)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        var hacSha512 = Mac.getInstance(HMacEncodeType.Sha512.getAlgorithmName());
        var secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMacEncodeType.Sha512.getAlgorithmName());
        hacSha512.init(secretKeySpec);
        return hacSha512.doFinal(message.getBytes(StandardCharsets.UTF_8));
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
    private String getSignature(String signatureContent, String secretKey) {
        return Hex.encodeHexString(HmacUtils.getSha256(signatureContent, secretKey));
    }*/

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
