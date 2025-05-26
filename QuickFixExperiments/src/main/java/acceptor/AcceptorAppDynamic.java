package acceptor;

import common.FixApplicationImpl;
import quickfix.*;
import quickfix.mina.acceptor.DynamicAcceptorSessionProvider;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;

import static quickfix.Acceptor.SETTING_SOCKET_ACCEPT_ADDRESS;
import static quickfix.Acceptor.SETTING_SOCKET_ACCEPT_PORT;

public class AcceptorAppDynamic
{
    public static void main(String[] args) throws ConfigError, FieldConvertError {
        Application fixApplication = new FixApplicationImpl();
        Connector connector = createConnector(fixApplication, AcceptorAppDynamic.class.getClassLoader()
                .getResourceAsStream("fixsession_acceptor_dynamic.conf"));



        connector.start();
    }

    private static InetSocketAddress getAcceptorSocketAddress(SessionSettings settings, SessionID sessionID)
            throws ConfigError,  FieldConvertError
    {
        String acceptorHost = "0.0.0.0";
        if (settings.isSetting(sessionID, SETTING_SOCKET_ACCEPT_ADDRESS)) {
            acceptorHost = settings.getString(sessionID, SETTING_SOCKET_ACCEPT_ADDRESS);
        }

        int acceptorPort = (int) settings.getLong(sessionID, SETTING_SOCKET_ACCEPT_PORT);
        return new InetSocketAddress(acceptorHost, acceptorPort);
    }

    private static Connector createConnector(Application application, InputStream acceptorConfig)
            throws ConfigError, FieldConvertError
    {
        SessionSettings settings = new SessionSettings(acceptorConfig);

        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        SocketAcceptor acceptor = new SocketAcceptor(application, storeFactory, settings, logFactory, messageFactory);

        Iterator sectionIterator = settings.sectionIterator();
        SessionID sessionID = (SessionID) sectionIterator.next();

        InetSocketAddress socketAddress = getAcceptorSocketAddress(settings, sessionID);

        acceptor.setSessionProvider(socketAddress, new DynamicAcceptorSessionProvider(
                settings, (SessionID) null, application, storeFactory, logFactory, messageFactory));

        return acceptor;
    }
}