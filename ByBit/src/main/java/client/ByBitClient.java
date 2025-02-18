package client;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


class OneLineLogger extends Logger {
    @Override
    protected void log(String configKey, String format, Object... args)  {
        System.err.printf(configKey + " - " + format + "%n", args);
    }
}

@Configuration
class ByBitClientConfig  {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Logger logger() {
        return new OneLineLogger();
    }
}

@FeignClient(
        url = "${bybit.api.baseUrl}",
        name = "ByBitClient",
        path = "v2/account/protected",
        configuration = ByBitClientConfig.class
)
public interface ByBitClient
{
    @GetMapping(value = "/get_account", produces = "application/json")
    public String getUserWalletBalance(@RequestParam("accountId") Long accountId);
}
