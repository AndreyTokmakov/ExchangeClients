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
    private static final String HEADER_KEY = "KEY";
    private static final String HEADER_SIGN = "SIGN";
    private static final String HEADER_TIMESTAMP = "Timestamp";
    private static final String HASH_ALGORITHM = "HmacSHA512";

    private final static String signDelimiter = "\n";
    private final static byte[] emptyByteArray = new byte[0];

    private final MessageDigest digest;
    private final Mac hacSha512;

    public GateIOSignatureGeneratorConfig() throws NoSuchAlgorithmException {
        digest = MessageDigest.getInstance("SHA-512");
        hacSha512 = Mac.getInstance(HASH_ALGORITHM);
    }

    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /*
    @Getter
    @RequiredArgsConstructor
    enum HMacEncodeType
    {
        Sha256("HmacSHA256"),
        Sha512("HmacSHA512");

        private final String algorithmName;

        @Override
        public String toString() {
            return algorithmName;
        }
    }*/

    RequestInterceptor requestInterceptor(GateIOConfigProperties.AccountConfig accountConfig)
    {
        return requestTemplate ->
        {
            final long timestamp = getTimestamp();
            final String signatureString = String.join(signDelimiter,
                    requestTemplate.method(),
                    requestTemplate.path(),
                    requestTemplate.queryLine().substring(1),
                    encodePayload(requestTemplate.body()),
                    String.valueOf(timestamp)
            );


            String signature = "";
            try {
                signature = Hex.encodeHexString(getSha512(signatureString,  accountConfig.getSecret()));
            }
            catch (Exception exc) {
                System.out.println("************ ERROR ***********");
            }

            requestTemplate.header(HEADER_KEY, accountConfig.getKey());
            requestTemplate.header(HEADER_SIGN, signature);
            requestTemplate.header(HEADER_TIMESTAMP, String.valueOf(timestamp));
        };
    }

    private String encodePayload(byte[] payload)
    {
        digest.update(null == payload ? emptyByteArray : payload);
        return Hex.encodeHexString(digest.digest());
    }

    public byte[] getSha512(String message, String secret)
            throws InvalidKeyException
    {
        var secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HASH_ALGORITHM);
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
}
