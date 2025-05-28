package binance_client;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.jetbrains.annotations.NotNull;
import quickfix.*;

import quickfix.field.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BinanceFixApiClientOld
{
    private final static class TradeInitiator implements Application
    {
        private static final String publicKeyFile  = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pub";
        private static final String privateKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pem";

        private static final String TIMESTAMP_PATTERN = "yyyyMMdd-HH:mm:ss.SSS";
        private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(TIMESTAMP_PATTERN);
        private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);

        private static String publicKey = "MnYw0HiTFOdoa1CKughFD5rGHSPsTiEKbpNnxZuKoxg2fc1YdXqfIwURUyPumvfE";
        private static Ed25519PrivateKeyParameters privateKey = null;

        public TradeInitiator()
        {
            try {
                FORMATTER.setTimeZone(TimeZone.getTimeZone("UTC"));
                // publicKey = getPublicKey();
                privateKey = loadPrivateKey();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
                    List<String> params = List.of("100500", "SPOT", "1", getCurrentTimeGmt());
                    System.out.println(params);

                    final String data = String.join( Character.toString(1), params);
                    final byte[] payload = data.getBytes();

                    Signer signer = new Ed25519Signer();
                    signer.init(true, privateKey);
                    signer.update(payload, 0, payload.length);
                    byte[] signatureBytes = signer.generateSignature();

                    message.setString(96, publicKey);
                    message.setString(553,   new String(Base64.getEncoder().encode(signatureBytes)));
                }
            } catch (FieldNotFound | CryptoException e) {
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


        private static String getPublicKey() {
            try {
                String key  = Files.readString(Paths.get(publicKeyFile), Charset.defaultCharset());
                List<String> lines = Arrays.asList(key.split("\n"));
                return lines.get(1);
            } catch (IOException e) {
                return "";
            }
        }

        public static Ed25519PrivateKeyParameters loadPrivateKey() throws IOException
        {
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
            return  new Ed25519PrivateKeyParameters(privateKeyBytes, 0);
        }

        public static String getCurrentTime()
        {
            return DT_FORMATTER.format(LocalDateTime.now());
        }

        public static String getCurrentTimeGmt()
        {
            return FORMATTER.format(new Date());
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

            Session.lookupSession(sessionId).logon();
            sleep(2000);

            /*
            NewOrderSingle newOrderSingle = new NewOrderSingle(
                    new ClOrdID("456"),
                    new Side(Side.BUY),
                    new TransactTime(),
                    new OrdType(OrdType.MARKET)
            );*/

            /*
            System.out.println("=".repeat(40) + " " + sessionId + " " + "=".repeat(40));
            System.out.println(newOrderSingle + "\n" + "=".repeat(120));
            Session.sendToTarget(newOrderSingle, sessionId);
            */

            // sleep(1);
            // socketInitiator.stop();

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
