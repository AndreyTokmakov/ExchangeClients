package binance;


import initiator.InitiatorApp;
import quickfix.*;
import quickfix.field.MsgType;
import quickfix.field.Text;
import quickfix.fix44.QuoteRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class BinanceFixApiDebugClient
{

    public static final class ClientAppImpl implements Application
    {
        private final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(this.getClass().getName());

        private static final String publicKey = "MnYw0HiTFOdoa1CKughFD5rGHSPsTiEKbpNnxZuKoxg2fc1YdXqfIwURUyPumvfE";
        private static final String privateKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pem";
        private static final String TIMESTAMP_PATTERN = "yyyyMMdd-HH:mm:ss.SSS";
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);

        private final Signature signer;
        private PrivateKey privateKey = null;

        // private Session defaultSession;
        // private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        public ClientAppImpl()
        {
            // scheduledExecutorService.scheduleAtFixedRate(this::sendMessage, 5, 5, TimeUnit.SECONDS);
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

        public static String getCurrentTime()
        {
            return FORMATTER.format(LocalDateTime.now());
        }

        public static String getCurrentTimeGmt()
        {
            return FORMATTER.format( new Date().toInstant().atZone(ZoneOffset.UTC));
        }

        @Override
        public void onCreate(SessionID sessionID) {

        }

        @Override
        public void onLogon(SessionID sessionID) {
            LOGGER.info("Session is logged on");
           // defaultSession = Session.lookupSession(sessionID);
        }

        @Override
        public void onLogout(SessionID sessionID) {
            LOGGER.info("Session " + sessionID + " is over");
        }

        @Override
        public void toAdmin(Message message, SessionID sessionId) {
            LOGGER.info("To admin (" + sessionId + "): " + message);
            authorize(message);

            System.out.println("=".repeat(55) + " toAdmin " + "=".repeat(55) + "\n" +
                    sessionId + ", message: " + message + "\n" + "=".repeat(120));
        }

        @Override
        public void fromAdmin(Message message, SessionID sessionID)
                throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon
        {
            LOGGER.info("From admin (" + sessionID + "): " + message);
        }

        @Override
        public void toApp(Message message, SessionID sessionID) throws DoNotSend {
            LOGGER.info("Message is being sent:" + message);
        }

        @Override
        public void fromApp(Message message, SessionID sessionID)
                throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType
        {
            LOGGER.info("Message received:" + message);
        }

        private void authorize(Message message)
        {
            try
            {
                final String msgType = message.getHeader().getString(MsgType.FIELD);
                if (MsgType.LOGON.compareTo(msgType) == 0)
                {
                    final String timestamp = getCurrentTimeGmt();
                    List<String> params = List.of("A", "1001", "SPOT", "1", timestamp);

                    final String data = String.join( Character.toString(1), params);
                    final byte[] payload = data.getBytes();

                    signer.initSign(privateKey);
                    signer.update(payload);
                    byte[] signatureBytes = signer.sign();

                    final String rawData = new String(Base64.getEncoder().encode(signatureBytes));
                    message.setString(553, publicKey);
                    message.setString(95,  String.valueOf(rawData.length()));
                    message.setString(96,  rawData);
                    // message.setString(25035,  "1");
                }
            } catch (FieldNotFound | SignatureException | InvalidKeyException e) {
                System.err.println("***************** ERROR ***********************");
                throw new RuntimeException(e);
            }
        }

        /*
        private void sendMessage()
        {
            final String testMessage = "*** CLIENT HELLO ***";
            if (isLoggedOn())
            {
                QuoteRequest quoteRequest = new QuoteRequest();
                quoteRequest.setString(Text.FIELD, testMessage);
                defaultSession.send(quoteRequest);
                LOGGER.info("Test messages '" + testMessage + "' has been send");
            } else {
                LOGGER.info("session is not logged in, message will not send");
            }
        }

        private boolean isLoggedOn() {
            return defaultSession != null && defaultSession.isLoggedOn();
        }*/
    }

    public static void main(String[] args) throws ConfigError
    {
        Application fixApplication = new ClientAppImpl();
        Connector connector = createConnector(fixApplication, "binance_debug_client.conf");
        connector.start();
        for (int i = 0; i < 100; ++i) {
            Sleep(100);
            System.out.println(i);
        }
    }

    private static Connector createConnector(Application fixApp, String configFile)
            throws ConfigError
    {
        InputStream config = InitiatorApp.class.getClassLoader().getResourceAsStream(configFile);
        SessionSettings settings = new SessionSettings(config);
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);

        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        return new SocketInitiator(fixApp, storeFactory, settings, logFactory, messageFactory);
    }

    private static void Sleep(final long milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {}
    }
}
