package acceptor;

import common.FixApplicationImpl;
import quickfix.*;
import quickfix.field.Text;
import quickfix.fix44.QuoteRequest;

import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AcceptorApp
{

    private final static class ServerApp implements Application
    {
        private final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(this.getClass().getName());

        private Session defaultSession;
        private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        public ServerApp()
        {
            scheduledExecutorService.scheduleAtFixedRate(this::sendMessage, 5, 5, TimeUnit.SECONDS);
        }

        @Override
        public void onCreate(SessionID sessionID) {

        }

        @Override
        public void onLogon(SessionID sessionID) {
            LOGGER.info("Session is logged on");
            defaultSession = Session.lookupSession(sessionID);
        }

        @Override
        public void onLogout(SessionID sessionID) {
            LOGGER.info("Session " + sessionID + " is over");
        }

        @Override
        public void toAdmin(Message message, SessionID sessionID) {
            LOGGER.info("To admin (" + sessionID + "): " + message);
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

        private void sendMessage()
        {
            final String testMessage = "*** MESSAGE ***";
            if (isLoggedOn())
            {
                QuoteRequest quoteRequest = new QuoteRequest();
                quoteRequest.setString(Text.FIELD, testMessage);
                defaultSession.send(quoteRequest);
                LOGGER.info("Test messages '" +testMessage + "' has been send");
            } else {
                LOGGER.info("session is not logged in, message will not send");
            }
        }

        private boolean isLoggedOn() {
            return defaultSession != null && defaultSession.isLoggedOn();
        }
    }

    public static void main(String[] args) throws ConfigError
    {
        Application fixApplication = new ServerApp();
        Connector connector = createConnector(fixApplication, AcceptorApp.class.getClassLoader()
                .getResourceAsStream("fixsession_acceptor.conf"));
        connector.start();
    }

    private static Connector createConnector(Application fixApp, InputStream acceptorConfig) throws ConfigError
    {
        SessionSettings settings = new SessionSettings(acceptorConfig);
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);

        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        return new SocketAcceptor(fixApp, storeFactory, settings, logFactory, messageFactory);
    }
}