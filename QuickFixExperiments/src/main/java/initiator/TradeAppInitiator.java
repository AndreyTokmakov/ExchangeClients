package initiator;


import quickfix.*;

class TradeInitiator implements Application
{
    @Override
    public void onCreate(SessionID sessionId) {
        System.out.println("Executor Session Created with SessionID = " + sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        System.out.println("onLogon: SessionID = " + sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        System.out.println("onLogout: SessionID = " + sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("onLogout: SessionID = " + sessionId + ", message: " + message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("fromAdmin: SessionID = " + sessionId + ", message: " + message);
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
}


public class TradeAppInitiator
{
    public static void main(String[] args)
    {
        SocketInitiator socketInitiator = null;
        try {
            SessionSettings initiatorSettings = new SessionSettings(
                    "/home/andtokm/Projects/M2/ExchangeClients/QuickFixExperiments/src/main/resources/initiatorSettings.conf");
            Application initiatorApplication = new TradeInitiator();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(initiatorSettings);
            FileLogFactory fileLogFactory = new FileLogFactory(initiatorSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            socketInitiator = new SocketInitiator(initiatorApplication, fileStoreFactory, initiatorSettings, fileLogFactory, messageFactory);
            socketInitiator.start();
            SessionID sessionId = socketInitiator.getSessions().get(0);
            Session.lookupSession(sessionId).logon();
        } catch (ConfigError e) {
            e.printStackTrace();
        }
    }
}
