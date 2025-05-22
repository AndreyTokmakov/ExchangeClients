package impl.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gate.api")
public class GateIOConfigProperties
{
    private String baseUrl;
    private AccountConfig mainAccount;

    @Data
    public static class AccountConfig {
        private String key;
        private String secret;
    }
}
