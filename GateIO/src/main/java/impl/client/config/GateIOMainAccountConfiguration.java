package impl.client.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class GateIOMainAccountConfiguration
{
    private final GateIOConfigProperties properties;
    private final GateIOSignatureGeneratorConfig mainAccountConfig;

    @Bean
    RequestInterceptor gateIoRequestInterceptor() {
        System.out.println("* * * * * GateIOMainAccountConfiguration::gateIoRequestInterceptor() * * * * ");
        return mainAccountConfig.requestInterceptor(properties.getMainAccount());
    }
}
