package client.config;


import client.FixInitiator;
import client.handler.LogonSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import quickfix.Application;
import quickfix.ApplicationFunctionalAdapter;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.Initiator;
import quickfix.MemoryStoreFactory;
import quickfix.SLF4JLogFactory;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

@Configuration
@SuppressWarnings("unused")
class ClientConfig {

    @Bean
    SessionSettings sessionSettings(@Value("${quickfixj.config}") final String configFile)
            throws ConfigError {
        return new SessionSettings(configFile);
    }

    @Bean
    Initiator initiator(final SessionSettings sessionSettings,
                        final Application application) throws ConfigError {
        return new SocketInitiator(
                application,
                new MemoryStoreFactory(),
                sessionSettings,
                new SLF4JLogFactory(sessionSettings),
                new DefaultMessageFactory());
    }

    @Bean
    ApplicationFunctionalAdapter application() {
        return new ApplicationFunctionalAdapter();
    }

    @Profile("ssl")
    @Bean
    LogonSender logonSender(final ApplicationFunctionalAdapter functionalAdapter,
                            @Value("${quickfixj.user}") final String user,
                            @Value("${quickfixj.password}") final String password
    ) {
        return new LogonSender(functionalAdapter, user, password);
    }


    @Bean
    FixInitiator fixInitiator(final Initiator initiator) {
        return new FixInitiator(initiator);
    }

}