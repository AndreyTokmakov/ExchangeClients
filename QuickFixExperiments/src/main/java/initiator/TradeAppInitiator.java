package initiator;


import quickfix.*;

import quickfix.fix44.Logon;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;

import java.util.concurrent.TimeUnit;

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
                    "/home/andtokm/Projects/M2/ExchangeClients/QuickFixExperiments/src/main/resources/initiator_settings.conf");
            Application initiatorApplication = new TradeInitiator();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(initiatorSettings);
            FileLogFactory fileLogFactory = new FileLogFactory(initiatorSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            SocketInitiator socketInitiator = new SocketInitiator(initiatorApplication, fileStoreFactory, initiatorSettings, fileLogFactory, messageFactory);
            socketInitiator.start();
            SessionID sessionId = socketInitiator.getSessions().get(0);

            Session.lookupSession(sessionId).logon();

            /* try {
                Logon logon = new Logon();
                logon.set(new quickfix.field.HeartBtInt(30));
                logon.set(new quickfix.field.ResetSeqNumFlag(true));
                logon.set(new quickfix.field.EncryptMethod(0));
                Session.sendToTarget(logon, sessionId);
            } catch (SessionNotFound sessionNotFound) {
                System.err.println("=".repeat(120));
                System.err.println(sessionNotFound.getMessage());
                System.err.println("=".repeat(120));
            } */

            sleep(2000);
            NewOrderSingle newOrderSingle = new NewOrderSingle(
                    new ClOrdID("456"),
                    new Side(Side.BUY),
                    new TransactTime(),
                    new OrdType(OrdType.MARKET)
            );

            System.out.println("=".repeat(40) + " " + sessionId + " " + "=".repeat(40));
            System.out.println(newOrderSingle + "\n" + "=".repeat(120));

            Session.sendToTarget(newOrderSingle, sessionId);

            sleep(1);
            socketInitiator.stop();

        } catch (ConfigError | SessionNotFound exc) {
            System.err.println(exc.getMessage());
        }
    }

    public static void sleep(int msSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(msSeconds);
        } catch (final InterruptedException exc) {
        }
    }
}
