package data_mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.SubAccountBalanceDto;

import java.util.List;

public class GateIODataMapping
{
    private final static ObjectMapper mapper = new ObjectMapper();



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


    public static void main(String[] args)
    {
        parseSubAcctBalancesResponse();
    }
}
