package client;

import client.config.ByBitClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        url = "${bybit.api.baseUrl}",
        name = "ByBitClient",
        configuration = ByBitClientConfig.class
)
public interface ByBitClient
{
    @GetMapping(value = "v5/account/wallet-balance", produces = "application/json")
    public String getUserWalletBalance(@RequestParam("accountType") String accountType,
                                       @RequestParam("coin") String coin);
}
