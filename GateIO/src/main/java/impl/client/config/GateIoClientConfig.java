package impl.client.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class GateIoClientConfig
{
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
}
