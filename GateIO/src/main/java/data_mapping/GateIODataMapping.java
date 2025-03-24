package data_mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AccountTotalBalanceDto;
import dto.SubAccountBalanceDto;

import java.util.List;



public class GateIODataMapping
{
    private final static ObjectMapper mapper = new ObjectMapper();

    private final static class Data
    {
        public static String totalBalance = """
                {
                  "details": {
                    "cross_margin": {
                      "amount": "0",
                      "currency": "USDT"
                    },
                    "spot": {
                      "currency": "USDT",
                      "amount": "42264489969935775.5160259954878034182418"
                    },
                    "finance": {
                      "amount": "662714381.70310327810191647181",
                      "currency": "USDT"
                    },
                    "margin": {
                      "amount": "1259175.664137668554329559",
                      "currency": "USDT",
                      "borrowed": "0.00"
                    },
                    "quant": {
                      "amount": "591702859674467879.6488202650892478553852",
                      "currency": "USDT"
                    },
                    "futures": {
                      "amount": "2384175.5606114082065",
                      "currency": "USDT",
                      "unrealised_pnl": "0.00"
                    },
                    "delivery": {
                      "currency": "USDT",
                      "amount": "1519804.9756702",
                      "unrealised_pnl": "0.00"
                    },
                    "warrant": {
                      "amount": "0",
                      "currency": "USDT"
                    },
                    "cbbc": {
                      "currency": "USDT",
                      "amount": "0"
                    }
                  },
                  "total": {
                    "currency": "USDT",
                    "amount": "633967350312281193.068368815439797304437",
                    "unrealised_pnl": "0.00",
                    "borrowed": "0.00"
                  }
                }
                """;
    }



    public static void parseSubAcctBalancesResponse()
    {
        String subAcctBalancesResponse = "[{\"uid\":\"10003\",\"available\":{\"BTC\":\"0.1\",\"GT\":\"2000\",\"USDT\":\"10\"}}]";
        try {
            final List<SubAccountBalanceDto> responseDto = mapper.readValue(
                    subAcctBalancesResponse, new TypeReference< List<SubAccountBalanceDto>>() {});

            System.out.println(responseDto);
        } catch (final Exception exc) {
            System.err.println(exc.getMessage());
        }
    }

    public static void parseTotalBalance()
    {
        try {
            final AccountTotalBalanceDto responseDto = mapper.readValue(
                    Data.totalBalance, new TypeReference<AccountTotalBalanceDto>() {});

            System.out.println(responseDto);
        } catch (final Exception exc) {
            System.err.println(exc.getMessage());
        }
    }


    public static void main(String[] args)
    {
        // parseSubAcctBalancesResponse();
        parseTotalBalance();
    }
}
