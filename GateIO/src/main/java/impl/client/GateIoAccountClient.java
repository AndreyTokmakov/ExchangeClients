package impl.client;

import impl.client.config.GateIOAuthDecoratorConfig;
import impl.client.config.GateIOMainAccountConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        url = "${gate.api.url}", // Have to run as WebService
        // url = "http://localhost:50002",
        name = "GateIoAccountClient",
        // configuration = GateIOAuthDecoratorConfig.class    // To call REST Api around each call
        configuration = GateIOMainAccountConfiguration.class  // To generate signature around each call
)
public interface GateIoAccountClient {
    @GetMapping(value = "api/v4/wallet/sub_account_futures_balances", produces = "application/json")
    public String getAccountBalance(@RequestParam("accountId") String accountId);
}
