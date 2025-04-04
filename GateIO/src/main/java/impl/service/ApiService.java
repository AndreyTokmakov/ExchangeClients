package impl.service;

import dto.*;
import impl.client.GateIoAccountClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ApiService
{
    @Autowired
    private GateIoAccountClient accountClient;

    public ApiService() {
        log.info("{} created!", this.getClass().getSimpleName());
    }

    public String getAccountBalance()
    {
        final String response = accountClient.getAccountBalance("111222222");
        return response;
    }

    public String getTotalBalances()
    {
        final AccountTotalBalanceDto response = accountClient.getTotalBalances("USDT");
        System.out.println(response);
        return response.toString();
    }

    public String getSubAccountBalances()
    {
        final List<SubAccountBalanceDto> subAccountBalances = accountClient.getSubAccountBalances("111222222");

        System.out.println("=".repeat(140));
        for (SubAccountBalanceDto balance : subAccountBalances)
        {
            System.out.println(balance.getUserId());
            System.out.println(balance.getAvailableBalances() + "\n");
        }
        System.out.println("=".repeat(140));

        return subAccountBalances.toString();
    }

    public String getSubAccountMarginBalances()
    {
        final List<SubAccountMarginBalanceDto> balances = accountClient.getSubAccountMarginBalances("111222222");

        System.out.println("=".repeat(140));
        for (SubAccountMarginBalanceDto marginBalance : balances)  {
            System.out.println(marginBalance.getUserId());
            for (MarginAccountDto balance: marginBalance.getAvailableBalances())
            {
                System.out.println("locked: " + balance.getLocked() + ", currency_pair: " + balance.getCurrencyPair() +
                        ", risk: " + balance.getRisk());
                System.out.println(balance.getBaseDetails());
                System.out.println(balance.getQuoteDetails());
            }
        }
        System.out.println("=".repeat(140));

        return balances.toString();
    }

    public String getAccountSpotBalance()
    {
        final List<SpotAccountDto> response = accountClient.getAccountSpotBalance("111222222");
        response.forEach(System.out::println);
        return response.toString();
    }

    public String getAccountMarginBalance()
    {
        final List<MarginAccountDto> response = accountClient.getAccountMarginBalance("111222222");
        response.forEach(System.out::println);
        return response.toString();
    }
}