package api.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ByBitUserWalletBalanceResponseDto
{
    Integer retCode;
    String retMsg;
    Integer time;


    /*
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class RedeemRequestDto {
        String clientId;
        String redemptionAmount;
    }*/
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