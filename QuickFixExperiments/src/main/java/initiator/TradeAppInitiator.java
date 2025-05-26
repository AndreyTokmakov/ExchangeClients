package initiator;


import quickfix.*;

import quickfix.fix42.Logon;
import quickfix.field.*;
import quickfix.fix42.NewOrderSingle;

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
    // https://github.com/manodya/QuickFIXJ-Server-and-Client/tree/master

    public static void main(String[] args)
    {
        try {
            SessionSettings initiatorSettings = new SessionSettings(
                    "/home/andtokm/Projects/M2/ExchangeClients/QuickFixExperiments/src/main/resources/initiatorSettings.conf");
            Application initiatorApplication = new TradeInitiator();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(initiatorSettings);
            FileLogFactory fileLogFactory = new FileLogFactory(initiatorSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            SocketInitiator socketInitiator = new SocketInitiator(initiatorApplication, fileStoreFactory, initiatorSettings, fileLogFactory, messageFactory);
            socketInitiator.start();
            SessionID sessionId = socketInitiator.getSessions().get(0);

            System.out.println("=".repeat(120));
            System.out.println(sessionId);
            System.out.println("=".repeat(120));
            Session.lookupSession(sessionId).logon();
            System.out.println("Done");
            System.out.println("=".repeat(120));

            Logon logon = new Logon();

            logon.set(new quickfix.field.HeartBtInt(30));
            logon.set(new quickfix.field.ResetSeqNumFlag(true));
            logon.set(new quickfix.field.EncryptMethod(0));

            try {
                Session.sendToTarget(logon, sessionId);
            } catch (SessionNotFound sessionNotFound) {
                sessionNotFound.printStackTrace();
            }

            for(int j = 0; j < 2; j ++){
                try {
                    Thread.sleep(10000);
                    NewOrderSingle newOrderSingle = new NewOrderSingle(
                            new ClOrdID("456"),
                            new HandlInst('3'),
                            new Symbol("AJCB"),
                            new Side(Side.BUY),
                            new TransactTime(),
                            new OrdType(OrdType.MARKET)
                    );
                    System.out.println("####New Order Sent :" + newOrderSingle.toString());
                    Session.sendToTarget(newOrderSingle, sessionId);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SessionNotFound sessionNotFound) {
                    sessionNotFound.printStackTrace();
                }
            }

        } catch (ConfigError exc) {
            System.err.println(exc.getMessage());
        }
    }
}
