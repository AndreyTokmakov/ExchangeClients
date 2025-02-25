package impl.client.config;

import feign.RequestInterceptor;
import impl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class GateIOAuthDecoratorConfig
{
    @Autowired
    private AuthService authService;

    @Bean
    RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            final String authToken = authService.authenticate();
            requestTemplate.header("Authorization", "Bearer " + authToken);
        };
    }
}
