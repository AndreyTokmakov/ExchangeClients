package binance_client;

import org.jetbrains.annotations.NotNull;
import quickfix.*;

import quickfix.fix44.Logon;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class BinanceFixApiClient
{
    private final static class TradeInitiator implements Application
    {
        private final static String publicKeyFile = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pub";

        private static String publicKey = "";

        public TradeInitiator() {
            publicKey = getPublicKey();
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
            try {
                final String msgType = message.getHeader().getString(MsgType.FIELD);
                if(MsgType.LOGON.compareTo(msgType) == 0)
                {
                    message.setString(96, publicKey);
                    message.setString(553, "_password");
                }
            } catch (FieldNotFound e) {
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
    }

    private final static String configFile =
            "/home/andtokm/Projects/M2/ExchangeClients/QuickFixExperiments/src/main/resources/binance_client.conf";


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
            NewOrderSingle newOrderSingle = new NewOrderSingle(
                    new ClOrdID("456"),
                    new Side(Side.BUY),
                    new TransactTime(),
                    new OrdType(OrdType.MARKET)
            );

            /*
            System.out.println("=".repeat(40) + " " + sessionId + " " + "=".repeat(40));
            System.out.println(newOrderSingle + "\n" + "=".repeat(120));

            Session.sendToTarget(newOrderSingle, sessionId);
            */

            sleep(1);
            socketInitiator.stop();

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
