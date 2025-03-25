package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Optional;

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
        BigDecimal amount;
        String currency;
        String borrowed;

        @JsonProperty("unrealised_pnl")
        String unrealisedPnl;
    }
    @Data
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


/**
 *
 * {
 *   "details": {
 *     "cross_margin": {
 *       "amount": "0",
 *       "currency": "USDT"
 *     },
 *     "spot": {
 *       "currency": "USDT",
 *       "amount": "42264489969935775.5160259954878034182418"
 *     },
 *     "finance": {
 *       "amount": "662714381.70310327810191647181",
 *       "currency": "USDT"
 *     },
 *     "margin": {
 *       "amount": "1259175.664137668554329559",
 *       "currency": "USDT",
 *       "borrowed": "0.00"
 *     },
 *     "quant": {
 *       "amount": "591702859674467879.6488202650892478553852",
 *       "currency": "USDT"
 *     },
 *     "futures": {
 *       "amount": "2384175.5606114082065",
 *       "currency": "USDT",
 *       "unrealised_pnl": "0.00"
 *     },
 *     "delivery": {
 *       "currency": "USDT",
 *       "amount": "1519804.9756702",
 *       "unrealised_pnl": "0.00"
 *     },
 *     "warrant": {
 *       "amount": "0",
 *       "currency": "USDT"
 *     },
 *     "cbbc": {
 *       "currency": "USDT",
 *       "amount": "0"
 *     }
 *   },
 *   "total": {
 *     "currency": "USDT",
 *     "amount": "633967350312281193.068368815439797304437",
 *     "unrealised_pnl": "0.00",
 *     "borrowed": "0.00"
 *   }
 * }
 */