package binance;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

// https://developers.binance.com/docs/binance-spot-api-docs/testnet/fix-api#signaturecomputation

public class SignatureGeneration
{
    private static final String publicKeyFile  = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pub";
    private static final String privateKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pem";

    private static final String TIMESTAMP_PATTERN = "yyyyMMdd-HH:mm:ss.SSS";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);

    private static final Base64.Encoder base64Encoder = Base64.getEncoder();

    public static Ed25519PublicKeyParameters loadPublicKey_BouncyCastle() throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(publicKeyFile));
        return new Ed25519PublicKeyParameters(bytes, 0);
    }

    public static Ed25519PrivateKeyParameters loadPrivateKey_BouncyCastle() throws IOException
    {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
        return  new Ed25519PrivateKeyParameters(privateKeyBytes, 0);
    }

    public static PrivateKey loadPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        String privateKeyStr = Files.readAllLines(Paths.get(privateKeyFile)).get(1);
        byte[] decoded = Base64.getDecoder().decode(privateKeyStr.getBytes(StandardCharsets.UTF_8));

        KeyFactory keyFactory = KeyFactory.getInstance("Ed25519");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);

        return keyFactory.generatePrivate(keySpec);
    }


    public static String getCurrentTime()
    {
        return FORMATTER.format(LocalDateTime.now());
    }

    public static String getCurrentTimeGmt()
    {
        return FORMATTER.format( new Date().toInstant().atZone(ZoneOffset.UTC));
    }

    public static byte[] getPayload()
    {
        final String timestamp = "20250528-03:56:26.557";
        List<String> params = List.of(
                "A",
                "100500",
                "SPOT",
                "1",
                timestamp);

        final String data = String.join( Character.toString(1), params);
        return data.getBytes(StandardCharsets.US_ASCII);
    }

    public static void generateSignature1() throws IOException, CryptoException
    {
        Ed25519PrivateKeyParameters privateKey = loadPrivateKey_BouncyCastle();
        final byte[] message = getPayload();

        // create the signature
        Signer signer = new Ed25519Signer();
        signer.init(true, privateKey);
        signer.update(message, 0, message.length);
        byte[] signatureBytes = signer.generateSignature();

        String signature = new String(base64Encoder.encode(signatureBytes));

        System.out.println(signature);
    }

    public static void generateSignature2()
            throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, IOException, InvalidKeyException
    {
        PrivateKey privateKey = loadPrivateKey();
        final byte[] message = getPayload();

        Signature signer = Signature.getInstance("Ed25519");
        signer.initSign(privateKey);
        signer.update(message);
        byte[] signatureBytes = signer.sign();

        String signature = new String(base64Encoder.encode(signatureBytes));
        System.out.println(signature);
    }

    public static void main(String[] args) throws IOException, CryptoException,
            NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException
    {
        // readPubicKey();
        // readPrivateKey();
        // generateSignature1();
        generateSignature2();

    }

    // https://dev.java/learn/security/digital-signature/
}
