package initiator;


import common.FixApplicationImpl;
import quickfix.*;

import java.io.InputStream;

public class InitiatorApp
{
    public static void main(String[] args) throws ConfigError
    {
        Application fixApplication = new FixApplicationImpl();
        Connector connector = createConnector(fixApplication, InitiatorApp.class.getClassLoader().getResourceAsStream(
                "fixsession_initiator.conf"));
        connector.start();
    }

    private static Connector createConnector(Application fixApp, InputStream acceptorConfig)
            throws ConfigError
    {
        SessionSettings settings = new SessionSettings(acceptorConfig);
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);

        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        return new SocketInitiator(fixApp, storeFactory, settings, logFactory, messageFactory);
    }
}