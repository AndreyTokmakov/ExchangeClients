package impl.client.config;

import feign.Logger;
import impl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class GateIoClientConfig
{
    @Autowired
    private AuthService authService;

    private final static class OneLineLogger extends Logger  {
        @Override
        protected void log(String configKey, String format, Object... args)  {
            System.err.printf(configKey + " - " + format + "%n", args);
        }
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Logger logger() {
        return new OneLineLogger();
    }

    /*
    RequestInterceptor requestInterceptor(DeribitConfigProperties.AccountConfig accountConfig) {
        return requestTemplate -> {
            var auth = deribitAuthClientService.auth(accountConfig.getClientId(), accountConfig.getClientSecret());
            requestTemplate.header("Authorization", "Bearer " + auth.getAccessToken());
        };
    }*/
}
