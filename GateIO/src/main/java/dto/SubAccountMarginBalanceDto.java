package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class SubAccountMarginBalanceDto {
    @JsonProperty("uid")
    String userId;

    @JsonProperty("available")
    List<MarginAccountBalanceDto> availableBalances;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class AccountDetailDto {
        // TODO: Check for BigDecimal
        String available;
        String borrowed;
        String interest;
        String currency;
        String locked;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class MarginAccountBalanceDto {
        Boolean locked;

        @JsonProperty("currency_pair")
        String currencyPair;

        // TODO: Check for BigDecimal / Float
        String risk;

        @JsonProperty("base")
        AccountDetailDto baseDetails;

        @JsonProperty("quote")
        AccountDetailDto quoteDetails;
    }
}