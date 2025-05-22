package encoding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaEncoding
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

    public static byte[] getSha256(String message, String secret)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac macAlgo = Mac.getInstance(HMacEncodeType.Sha256.getAlgorithmName());
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMacEncodeType.Sha256.getAlgorithmName());
        macAlgo.init(secretKeySpec);
        return macAlgo.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSha512(String message, String secret)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        var hacSha512 = Mac.getInstance("HmacSHA512");
        var secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hacSha512.init(secretKeySpec);
        return hacSha512.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    private static String byteToHex(byte[] bytes)
    {
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

    public static String getSha256Str(String message, String secret)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        if (secret == null || secret.isEmpty()) {
            return null;
        }
        var bytes = getSha256(message, secret);

        if (bytes == null || bytes.length < 1) {
            return null;
        }

        return byteToHex(bytes);
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



    private String getSignature(String signatureContent, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        return Hex.encodeHexString(getSha256(signatureContent, secretKey));
    }

    public static void main(String[] args)
    {
        HMacEncodeType sha256 = HMacEncodeType.Sha256;
        System.out.println(sha256);
        System.out.println(sha256.algorithmName);
    }
}
