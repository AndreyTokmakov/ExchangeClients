package acceptor;

import quickfix.*;

class TradeExecutor implements Application
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

public class TradeAppExecutor
{
    public static void main(String[] args)
    {
        try {
            SessionSettings executorSettings = new SessionSettings(
                    "/home/andtokm/Projects/M2/ExchangeClients/QuickFixExperiments/src/main/resources/acceptor_settings.conf");
            Application application = new TradeExecutor();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(executorSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            FileLogFactory fileLogFactory = new FileLogFactory(executorSettings);
            SocketAcceptor socketAcceptor = new SocketAcceptor(application, fileStoreFactory, executorSettings, fileLogFactory, messageFactory);
            socketAcceptor.start();
        } catch (ConfigError exc) {
            System.err.println(exc.getMessage());
        }
    }
}