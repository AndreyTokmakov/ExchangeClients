package dto;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public abstract class BalanceBase {
    String currency;
    BigDecimal available;
    BigDecimal locked;
    BigDecimal borrowed;
}
