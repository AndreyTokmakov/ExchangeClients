package impl.service.operation;

import impl.service.ApiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class GateIOBalanceOperationService
{
    @Autowired
    private ApiService apiService;

    public void fetchBalances()
    {
        // log.info("Balance now: {}", balanceResponse);
        // final String response = apiService.getAccountBalance();
        final String response = apiService.getTotalBalances();
        // final String response = apiService.getSubAccountBalances();
        // final String response = apiService.getSubAccountMarginBalances();
    }
}
