package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginAccountDto
{
    Boolean locked;

    @JsonProperty("currency_pair")
    String currencyPair;

    BigDecimal risk;

    @JsonProperty("base")
    AccountDetailDto baseDetails;

    @JsonProperty("quote")
    AccountDetailDto quoteDetails;

    @Data
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountDetailDto extends BalanceBase {
        BigDecimal interest;
    }
}