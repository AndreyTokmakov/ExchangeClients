package binance_client;


import org.bouncycastle.crypto.CryptoException;
import org.jetbrains.annotations.NotNull;
import quickfix.*;

import quickfix.field.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BinanceFixApiClient
{
    private final static class TradeInitiator implements Application
    {
        private static final String publicKey = "MnYw0HiTFOdoa1CKughFD5rGHSPsTiEKbpNnxZuKoxg2fc1YdXqfIwURUyPumvfE";
        private static final String privateKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pem";
        private static final String TIMESTAMP_PATTERN = "yyyyMMdd-HH:mm:ss.SSS";
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);

        private final Signature signer;
        private PrivateKey privateKey = null;

        public TradeInitiator()
        {
            try {
                signer = Signature.getInstance("Ed25519");
                privateKey = loadPrivateKey();
            } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }

        public static PrivateKey loadPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
        {
            String privateKeyStr = Files.readAllLines(Paths.get(privateKeyFile)).get(1);
            byte[] decoded = Base64.getDecoder().decode(privateKeyStr.getBytes(StandardCharsets.UTF_8));

            KeyFactory keyFactory = KeyFactory.getInstance("Ed25519");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);

            return keyFactory.generatePrivate(keySpec);
        }

        @Override
        public void onCreate(SessionID sessionId) {
            System.out.println("Executor Session Created with SessionID = " + sessionId);
        }

        @Override
        public void onLogon(SessionID sessionId) {
            System.out.println("=".repeat(55) + " onLogon " + "=".repeat(55) + "\n" +
                    sessionId + "\n" + "=".repeat(120));
        }

        @Override
        public void onLogout(SessionID sessionId) {
            System.out.println("=".repeat(55) + " onLogout " + "=".repeat(55) + "\n" +
                    sessionId + "\n" + "=".repeat(120));
        }

        @Override
        public void toAdmin(Message message, SessionID sessionId)
        {
            try
            {
                final String msgType = message.getHeader().getString(MsgType.FIELD);
                if (MsgType.LOGON.compareTo(msgType) == 0)
                {
                    final String timestamp = getCurrentTimeGmt();
                    // final String timestamp = "20250528-03:56:26.557";

                    List<String> params = List.of("A", "100500", "SPOT", "2", timestamp);
                    System.out.println(params);

                    final String data = String.join( Character.toString(1), params);
                    final byte[] payload = data.getBytes();

                    signer.initSign(privateKey);
                    signer.update(payload);
                    byte[] signatureBytes = signer.sign();

                    message.setString(553, publicKey);
                    message.setString(96,   new String(Base64.getEncoder().encode(signatureBytes)));
                }
            } catch (FieldNotFound | SignatureException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }


            System.out.println("=".repeat(55) + " toAdmin " + "=".repeat(55) + "\n" +
                    sessionId + ", message: " + message + "\n" + "=".repeat(120));
        }

        @Override
        public void fromAdmin(Message message, SessionID sessionId)
                throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon
        {
            System.out.println("=".repeat(55) + " fromAdmin " + "=".repeat(55) + "\n" +
                    sessionId + ", message: " + message + "\n" + "=".repeat(120));
        }

        @Override
        public void toApp(Message message, SessionID sessionId) throws DoNotSend
        {
            System.out.println("toApp: SessionID = " + sessionId + ", message: " + message);
        }

        @Override
        public void fromApp(Message message, SessionID sessionId)
                throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType
        {
            System.out.println("fromApp: SessionID = " + sessionId + ", message: " + message);
        }

        public static String getCurrentTime()
        {
            return FORMATTER.format(LocalDateTime.now());
        }

        public static String getCurrentTimeGmt()
        {
            return FORMATTER.format( new Date().toInstant().atZone(ZoneOffset.UTC));
        }
    }

    private final static String configFile =
            "/home/andtokm/DiskS/M2/ExchangeClients/QuickFixExperiments/src/main/resources/binance_client.conf";


    public static void sleep(int msSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(msSeconds);
        } catch (final InterruptedException exc) {
        }
    }

    public static void main(String[] args)
    {
        try
        {
            SocketInitiator socketInitiator = getSocketInitiator();
            SessionID sessionId = socketInitiator.getSessions().get(0);

            sleep(2000);

            try (Session session = Session.lookupSession(sessionId))
            {
                //session.logon();
                System.out.println("-".repeat(120));
                System.out.println(session);
                System.out.println(session.getSessionID());
                System.out.println(session.getCheckCompID());
                System.out.println(session.isLoggedOn());
                System.out.println("-".repeat(120));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (ConfigError /*| SessionNotFound*/ exc) {
            System.err.println(exc.getMessage());
        }
    }

    @NotNull
    private static SocketInitiator getSocketInitiator() throws ConfigError
    {
        SessionSettings settings = new SessionSettings(configFile);

        Application initiatorApplication = new TradeInitiator();
        FileStoreFactory fileStoreFactory = new FileStoreFactory(settings);
        FileLogFactory fileLogFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        SocketInitiator socketInitiator = new SocketInitiator(initiatorApplication, fileStoreFactory, settings, fileLogFactory, messageFactory);
        socketInitiator.start();
        return socketInitiator;
    }
}
