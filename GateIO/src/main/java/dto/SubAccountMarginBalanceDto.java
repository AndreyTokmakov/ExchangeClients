package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class SubAccountMarginBalanceDto {
    @JsonProperty("uid")
    String userId;

    @JsonProperty("available")
    List<MarginAccountDto> availableBalances;
}