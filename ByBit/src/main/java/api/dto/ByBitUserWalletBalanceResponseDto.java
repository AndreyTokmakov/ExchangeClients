package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ByBitUserWalletBalanceResponseDto
{
    Integer retCode;
    String retMsg;
    Long time;


    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class BalanceEntityDto
    {
        String totalEquity;
        String accountIMRate;
        String totalMarginBalance;
        String totalInitialMargin;
        String accountType; // UNIFIED
        String totalAvailableBalance;
        String accountMMRate;
        String totalPerpUPL;
        String totalWalletBalance;
        String accountLTV;
        String totalMaintenanceMargin;
        List<CoinDto> coin;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class CoinDto
    {
        String availableToBorrow;
        String bonus;
        String accruedInterest;
        String availableToWithdraw;
        String totalOrderIM;
        String equity;
        String totalPositionMM;
        String usdValue;
        String unrealisedPnl;
        Boolean collateralSwitch;
        String spotHedgingQty;
        BigDecimal borrowAmount;
        String totalPositionIM;
        String walletBalance;
        String cumRealisedPnl;
        String locked;
        String marginCollateral;
        String coin;
    }
}


/*

{
  "retCode": 0,
  "retMsg": "OK",
  "result": {
    "list": [
      {
        "totalEquity": "0",
        "accountIMRate": "0",
        "totalMarginBalance": "0",
        "totalInitialMargin": "0",
        "accountType": "UNIFIED",
        "totalAvailableBalance": "0",
        "accountMMRate": "0",
        "totalPerpUPL": "0",
        "totalWalletBalance": "0",
        "accountLTV": "0",
        "totalMaintenanceMargin": "0",
        "coin": [
          {
            "availableToBorrow": "",
            "bonus": "0",
            "accruedInterest": "0",
            "availableToWithdraw": "",
            "totalOrderIM": "0",
            "equity": "0",
            "totalPositionMM": "0",
            "usdValue": "0",
            "unrealisedPnl": "0",
            "collateralSwitch": false,
            "spotHedgingQty": "0",
            "borrowAmount": "0.000000000000000000",
            "totalPositionIM": "0",
            "walletBalance": "0",
            "cumRealisedPnl": "0",
            "locked": "0",
            "marginCollateral": true,
            "coin": "BTC"
          }
        ]
      }
    ]
  },
  "retExtInfo": {},
  "time": 1739877286418
}
*/