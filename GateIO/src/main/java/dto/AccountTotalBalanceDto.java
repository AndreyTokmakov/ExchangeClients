package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class AccountTotalBalanceDto
{
    @JsonProperty("details")
    DetailedBalancesDto detailedBalances;

    @JsonProperty("total")
    BalanceDto totalBalance;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class BalanceDto {
        String currency;
        BigDecimal amount;
        BigDecimal borrowed;

        @JsonProperty("unrealised_pnl")
        BigDecimal unrealisedPnl;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class DetailedBalancesDto {
        @JsonProperty("cross_margin")
        BalanceDto crossMarginBalance;

        @JsonProperty("spot")
        BalanceDto spotBalance;

        @JsonProperty("finance")
        BalanceDto financeBalance;

        @JsonProperty("margin")
        BalanceDto marginBalance;

        @JsonProperty("quant")
        BalanceDto quantBalance;

        @JsonProperty("futures")
        BalanceDto futuresBalance;

        @JsonProperty("delivery")
        BalanceDto deliveryBalance;

        @JsonProperty("warrant")
        BalanceDto warrantBalance;

        @JsonProperty("cbbc1")
        BalanceDto cbbc1Balance;
    }
}