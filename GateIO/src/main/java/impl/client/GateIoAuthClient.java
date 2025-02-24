package impl.client;

import impl.client.config.GateIoClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;;

@FeignClient(
        // url = "${gate.api.url}", // Have to run as WebService
        url = "http://localhost:50002",
        name = "GateIoAuthClient",
        configuration = GateIoClientConfig.class
)
public interface GateIoAuthClient {
    @GetMapping(value = "api/v4/auth", produces = "application/json")
    public String authenticate(@RequestParam("accountId") String accountId);
}