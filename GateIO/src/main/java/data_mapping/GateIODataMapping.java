package data_mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AccountTotalBalanceDto;
import dto.SubAccountBalanceDto;
import dto.SubAccountMarginBalanceDto;

import java.util.List;
import java.util.Optional;


public class GateIODataMapping
{
    private final static ObjectMapper mapper = new ObjectMapper();


    public static <T> Optional<T> readValue(String data,
                                            com.fasterxml.jackson.core.type.TypeReference<T> typeReference) {
        try {
            final T result = mapper.readValue(data, typeReference);
            return Optional.of(result);
        } catch (final Exception exc) {
            System.err.println(exc.getMessage());
            return Optional.empty();
        }
    }

    public static void parseTotalBalance()
    {
        final Optional<AccountTotalBalanceDto> responseDto = readValue(TestData.TOTAL_BALANCE,
                new TypeReference<>() {});

        responseDto.ifPresent(System.out::println);
    }

    public static void parse_SubAccountBalance()
    {
        final Optional<List<SubAccountBalanceDto>> responseDto = readValue(
                TestData.SUB_ACCOUNT_BALANCES, new TypeReference<>() {});

        responseDto.ifPresent(balances -> balances.forEach(System.out::println));
    }

    public static void parse_SubAccountMarginBalance()
    {
        final Optional<List<SubAccountMarginBalanceDto>> responseDto = readValue(
                TestData.SUB_ACCOUNT_MARGIN_BALANCES, new TypeReference<>() {});

        responseDto.ifPresent(balances -> balances.forEach(balance -> {
                System.out.println("User id: " + balance.getUserId());
                balance.getAvailableBalances().forEach(balanceAvailable -> {
                    var bal = balanceAvailable;
                    System.out.println(bal.getCurrencyPair() + " | " + bal.getLocked() + " | " +  bal.getRisk());
                    System.out.println("\t" + bal.getBaseDetails());
                    System.out.println("\t" + bal.getQuoteDetails());
                });
            }
        ));
    }

    public static void parse_SubAccountFutureBalances()
    {
        final Optional<List<SubAccountBalanceDto>> responseDto = readValue(
                TestData.SUB_ACCOUNT_FUTURE_BALANCES, new TypeReference<>() {});

        responseDto.ifPresent(balances -> balances.forEach(System.out::println));
    }


    public static void main(String[] args)
    {
        parseTotalBalance();
        // parse_SubAccountBalance();
        // parse_SubAccountFutureBalances();
        // parse_SubAccountMarginBalance();
    }
}
