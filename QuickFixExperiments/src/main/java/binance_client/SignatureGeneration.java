package binance_client;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

// https://developers.binance.com/docs/binance-spot-api-docs/testnet/fix-api#signaturecomputation

public class SignatureGeneration
{
    private final static String publicKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pub";
    private final static String privateKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pem";

    public static void readPubicKey() throws IOException
    {
        String key = Files.readString(Paths.get(publicKeyFile), Charset.defaultCharset());
        List<String> lines = Arrays.asList(key.split("\n"));
        System.out.println(lines.get(1));
    }

    public static Ed25519PrivateKeyParameters readPrivateKey() throws IOException
    {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
        Ed25519PrivateKeyParameters privateKey = new Ed25519PrivateKeyParameters(privateKeyBytes, 0);
        return privateKey;
    }


    public static void generateSignature() throws IOException, CryptoException
    {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
        Ed25519PrivateKeyParameters privateKey = readPrivateKey();

        List<String> params = List.of("5JQmUOsm", "SPOT", "1", "20240612-08:52:21.613");

        String data = String.join( Character.toString(1), params);
        final byte[] message = data.getBytes();

        System.err.println(data);


        // create the signature
        Signer signer = new Ed25519Signer();
        signer.init(true, privateKey);
        signer.update(message, 0, message.length);
        byte[] signature = signer.generateSignature();

        System.out.println(new String(signature));
    }

    public static void main(String[] args) throws IOException, CryptoException
    {
        // readPubicKey();
        // readPrivateKey();
        generateSignature();
    }
}
