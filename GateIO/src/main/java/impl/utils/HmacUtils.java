package impl.utils;

import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class HmacUtils
{
    private static final String ENCODE_TYPE_HMAC_SHA_256 = "HmacSHA256";

    @SneakyThrows
    public static byte[] getSha256(String message, String secret) {
        var hacSha256 = Mac.getInstance(ENCODE_TYPE_HMAC_SHA_256);
        var secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ENCODE_TYPE_HMAC_SHA_256);
        hacSha256.init(secretKeySpec);
        return hacSha256.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    public static String getSha256Str(String message, String secret) {
        if (secret == null || secret.isEmpty()) {
            return null;
        }
        var bytes = getSha256(message, secret);

        if (bytes == null || bytes.length < 1) {
            return null;
        }

        return byteToHex(bytes);
    }

    private static String byteToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        var stringBuilder = new StringBuilder();
        String temp;
        for (var aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xff);
            if (temp.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }
}
