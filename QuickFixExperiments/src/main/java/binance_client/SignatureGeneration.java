package binance_client;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.*;

// https://developers.binance.com/docs/binance-spot-api-docs/testnet/fix-api#signaturecomputation

public class SignatureGeneration
{
    private static final String publicKeyFile  = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pub";
    private static final String privateKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pem";

    private static final String TIMESTAMP_PATTERN = "yyyyMMdd-HH:mm:ss.SSS";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);

    public static Ed25519PublicKeyParameters loadPublicKey() throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(publicKeyFile));

        System.out.println(new String(bytes));

        return new Ed25519PublicKeyParameters(bytes, 0);
    }

    public static Ed25519PrivateKeyParameters loadPrivateKey() throws IOException
    {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
        return  new Ed25519PrivateKeyParameters(privateKeyBytes, 0);
    }

    public static String getCurrentTime()
    {
        return FORMATTER.format(LocalDateTime.now().atZone(ZoneId.of("GMT")));
    }

    public static void generateSignature() throws IOException, CryptoException
    {
        Ed25519PrivateKeyParameters privateKey = loadPrivateKey();
        List<String> params = List.of(
                "100500",
                "SPOT",
                "1",
                getCurrentTime());

        final String data = String.join( Character.toString(1), params);
        final byte[] message = data.getBytes();

        // create the signature
        Signer signer = new Ed25519Signer();
        signer.init(true, privateKey);
        signer.update(message, 0, message.length);
        byte[] signatureBytes = signer.generateSignature();

        String signature = new String(Base64.getEncoder().encode(signatureBytes));
        System.out.println(signature);
    }

    public static void main(String[] args) throws IOException, CryptoException
    {
        // readPubicKey();
        // readPrivateKey();
        // generateSignature();

        // System.out.println(getCurrentTime());

    }
}
