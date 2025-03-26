package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Map;

// SubAccountBalanceDto< Map<String, BigDecimal> >

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class SubAccountBalanceDto {
    @JsonProperty("uid")
    String userId;

    @JsonProperty("available")
    Map<String, BigDecimal> availableBalances;
}