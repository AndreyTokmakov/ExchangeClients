package impl.client;

import dto.*;
import impl.client.config.GateIOMainAccountConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// TODO: --> "/api/v4" --> Prefix ???
@FeignClient(
        url = "${gate.api.url}", // Have to run as WebService
        name = "GateIoAccountClient",
        // configuration = GateIOAuthDecoratorConfig.class       // To call REST Api around each call
        configuration = GateIOMainAccountConfiguration.class    // To generate signature around each call
)
public interface GateIoAccountClient
{
    @GetMapping(value = "api/v4/wallet/sub_account_futures_balances", produces = "application/json")
    String getAccountBalance(@RequestParam("accountId") String accountId);

    @GetMapping(value = "api/v4/spot/accounts", produces = "application/json")
    List<SpotAccountDto> getAccountSpotBalance(@RequestParam("currency") String currency);

    @GetMapping(value = "api/v4/margin/accounts", produces = "application/json")
    List<MarginAccountDto> getAccountMarginBalance(@RequestParam("currency") String currency);

    @GetMapping(value = "api/v4/wallet/total_balance", produces = "application/json")
    AccountTotalBalanceDto getTotalBalances(@RequestParam("currency") String currency);

    @GetMapping(value = "api/v4/wallet/sub_account_balances", produces = "application/json")
    List<SubAccountBalanceDto> getSubAccountBalances(@RequestParam("sub_uid") String subAccountUid);

    @GetMapping(value = "api/v4/wallet/sub_account_margin_balances", produces = "application/json")
    List<SubAccountMarginBalanceDto> getSubAccountMarginBalances(@RequestParam("sub_uid") String subAccountUid);
}
