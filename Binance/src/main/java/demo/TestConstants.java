package demo;

public class TestConstants {

    public static final String GET_DEPOSITS = """
            [
                {
                    "id": "3728979209553911040",
                    "amount": "13.7785",
                    "coin": "LINK",
                    "network": "ETH",
                    "status": 0,
                    "address": "qwe",
                    "addressTag": "",
                    "txId": "wqeq",
                    "insertTime": 1700222881000,
                    "transferType": 0,
                    "confirmTimes": "1/6",
                    "unlockConfirm": 64,
                    "walletType": 0
                },
                {
                    "id": "3728977772568641025",
                    "amount": "4.87163006",
                    "coin": "BTC",
                    "network": "BTC",
                    "status": 6,
                    "address": "qwe",
                    "addressTag": "",
                    "txId": "qwe",
                    "insertTime": 1700222796000,
                    "transferType": 0,
                    "confirmTimes": "1/1",
                    "unlockConfirm": 2,
                    "walletType": 0
                },
                {
                    "id": "3728974165567806721",
                    "amount": "36882.624777",
                    "coin": "USDT",
                    "network": "ETH",
                    "status": 6,
                    "address": "qwe",
                    "addressTag": "",
                    "txId": "qwe",
                    "insertTime": 1700222581000,
                    "transferType": 0,
                    "confirmTimes": "27/6",
                    "unlockConfirm": 64,
                    "walletType": 0
                },
                {
                    "id": "3728972675700694273",
                    "amount": "52686135.0095924",
                    "coin": "SHIB",
                    "network": "ETH",
                    "status": 6,
                    "address": "qwe",
                    "addressTag": "",
                    "txId": "wqe",
                    "insertTime": 1700222492000,
                    "transferType": 0,
                    "confirmTimes": "34/6",
                    "unlockConfirm": 64,
                    "walletType": 0
                },
                {
                    "id": "3728967116670835968",
                    "amount": "151.70785124",
                    "coin": "LINK",
                    "network": "ETH",
                    "status": 6,
                    "address": "qe",
                    "addressTag": "",
                    "txId": "qwe",
                    "insertTime": 1700222160000,
                    "transferType": 0,
                    "confirmTimes": "62/6",
                    "unlockConfirm": 64,
                    "walletType": 0
                },
                {
                    "id": "3728965125165091840",
                    "amount": "14813.74488705",
                    "coin": "DOGE",
                    "network": "DOGE",
                    "status": 6,
                    "address": "qwe",
                    "addressTag": "",
                    "txId": "qwe",
                    "insertTime": 1700222042000,
                    "transferType": 0,
                    "confirmTimes": "21/6",
                    "unlockConfirm": 50,
                    "walletType": 0
                },
                {
                    "id": "3728960090238557440",
                    "amount": "11.42983606",
                    "coin": "AAVE",
                    "network": "ETH",
                    "status": 1,
                    "address": "qwe",
                    "addressTag": "",
                    "txId": "qweqw",
                    "insertTime": 1700221742000,
                    "transferType": 0,
                    "confirmTimes": "64/6",
                    "unlockConfirm": 64,
                    "walletType": 0
                },
                {
                    "id": "3728959651430523136",
                    "amount": "747.962889",
                    "coin": "XRP",
                    "network": "XRP",
                    "status": 1,
                    "address": "qwe",
                    "addressTag": "290311283",
                    "txId": "qwe",
                    "insertTime": 1700221715000,
                    "transferType": 0,
                    "confirmTimes": "1/1",
                    "unlockConfirm": 0,
                    "walletType": 0
                },
                {
                    "id": "3727548332559040001",
                    "amount": "1.52471926",
                    "coin": "BTC",
                    "network": "BTC",
                    "status": 1,
                    "address": "qwe",
                    "addressTag": "",
                    "txId": "eqwe",
                    "insertTime": 1700137594000,
                    "transferType": 0,
                    "confirmTimes": "2/1",
                    "unlockConfirm": 2,
                    "walletType": 0
                }
            ]
            """;

    public static final String GET_WITHDRAWALS = """
            [
                {
                    "id": "3727548332",
                    "amount": "55779.705592",
                    "transactionFee": "1",
                    "coin": "BTC",
                    "status": 6,
                    "address": "asdsa",
                    "txId": "asd1",
                    "applyTime": "2023-11-16 14:17:42",
                    "network": "TRX",
                    "transferType": 0,
                    "info": "asd",
                    "confirmNo": 50,
                    "walletType": 0,
                    "txKey": "",
                    "completeTime": "2023-11-16 14:19:43"
                },
                {
                    "id": "3727548333",
                    "amount": "55779.705592",
                    "transactionFee": "1",
                    "coin": "ETH",
                    "status": 6,
                    "address": "asd",
                    "txId": "asd2",
                    "applyTime": "2023-11-16 14:17:42",
                    "network": "TRX",
                    "transferType": 0,
                    "info": "TNXoiAJ3dct8Fjg4M9fkLFh9S2v9TXc32G",
                    "confirmNo": 50,
                    "walletType": 0,
                    "txKey": "",
                    "completeTime": "2023-11-16 14:19:43"
                },
                {
                    "id": "372754834",
                    "amount": "55779.705592",
                    "transactionFee": "1",
                    "coin": "USDT",
                    "status": 6,
                    "address": "asda",
                    "txId": "asd3",
                    "applyTime": "2023-11-16 14:17:42",
                    "network": "TRX",
                    "transferType": 0,
                    "info": "asd",
                    "confirmNo": 50,
                    "walletType": 0,
                    "txKey": "",
                    "completeTime": "2023-11-16 14:19:43"
                }
            ]
            """;
    public static final String GET_BALANCES = """
            {
                "makerCommission": 8,
                "takerCommission": 10,
                "buyerCommission": 0,
                "sellerCommission": 0,
                "commissionRates": {
                    "maker": "0.00080000",
                    "taker": "0.00100000",
                    "buyer": "0.00000000",
                    "seller": "0.00000000"
                },
                "canTrade": true,
                "canWithdraw": true,
                "canDeposit": true,
                "brokered": false,
                "requireSelfTradePrevention": false,
                "preventSor": false,
                "updateTime": 1700222837339,
                "accountType": "SPOT",
                "balances": [
                    {
                        "asset": "BTC",
                        "free": "4.87163986",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "GMX",
                        "free": "4.87163986",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "ADA",
                        "free": "62446.00000000",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "ETH",
                        "free": "0.01233309",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "USDT",
                        "free": "36882.62477731",
                        "locked": "0.00000000"
                    }
                ],
                "permissions": [
                    "SPOT",
                    "TRD_GRP_002"
                ],
                "uid": 757781646
            }
            """;
    public static final String GET_SUBBALANCES = """
            {
                "balances": [
                    {
                        "asset": "BTC",
                        "free": "4.87163986",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "GMX",
                        "free": "2.87163986",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "ADA",
                        "free": "62446.00000000",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "ETH",
                        "free": "0.01233309",
                        "locked": "0.00000000"
                    },
                    {
                        "asset": "USDT",
                        "free": "36882.62477731",
                        "locked": "0.00000000"
                    }
                ]
            }
            """;

    public static final String GET_SUBACCOUNTS = """
            {
               "subAccounts":[
                  {
                     "email":"binance.trading@m2.com",
                     "isFreeze":false,
                     "createTime":1695022031000,
                     "isManagedSubAccount":false,
                     "isAssetManagementSubAccount":false
                  },
                  {
                     "email":"binance.earnhedging@m2.com",
                     "isFreeze":false,
                     "createTime":1695022184000,
                     "isManagedSubAccount":false,
                     "isAssetManagementSubAccount":false
                  },
                  {
                     "email":"otc@m2.com",
                     "isFreeze":false,
                     "createTime":1702645346000,
                     "isManagedSubAccount":false,
                     "isAssetManagementSubAccount":false
                  },
                  {
                     "email":"m2cl.prop@m2.com",
                     "isFreeze":false,
                     "createTime":1703077231000,
                     "isManagedSubAccount":false,
                     "isAssetManagementSubAccount":false
                  }
               ],
               "success":true
            }
            """;

    public static final String CREATE_INTERNAL_TRANSFER_RESPONSE = """
            {
                "tranId":11945860693,
                "clientTranId":"test"
            }
            """;

    public static final String GET_INTERNAL_TRANSFER_RESPONSE = """
            {
                "result": [
                    {
                        "tranId": 92275823339,
                        "fromEmail": "abctest@gmail.com",
                        "toEmail": "deftest@gmail.com",
                        "asset": "BNB",
                        "amount": "0.01",
                        "createTimeStamp": 1640317374000,
                        "fromAccountType": "USDT_FUTURE",
                        "toAccountType": "SPOT",
                        "status": "SUCCESS",
                        "clientTranId": "test"
                    }
                ],
                "totalCount": 1
            }
            """;

    public static final String GET_ALL_COINS_RESPONSE = """
            [
                {
                    "coin": "BTC",
                    "depositAllEnable": true,
                    "free": "0.08074558",
                    "freeze": "0.00000000",
                    "ipoable": "0.00000000",
                    "ipoing": "0.00000000",
                    "isLegalMoney": false,
                    "locked": "0.00000000",
                    "name": "Bitcoin",
                    "networkList": [
                        {
                            "addressRegex": "^(bnb1)[0-9a-z]{38}$",
                            "coin": "BTC",
                            "depositDesc": "Wallet Maintenance, Deposit Suspended",
                            "depositEnable": false,
                            "isDefault": false,
                            "memoRegex": "^[0-9A-Za-z-_]{1,120}$",
                            "minConfirm": 1,
                            "name": "BEP2",
                            "network": "BNB",
                            "specialTips": "Both a MEMO and an Address are required to successfully deposit your BEP2-BTCB tokens to Binance.",
                            "unLockConfirm": 0,
                            "withdrawDesc": "Wallet Maintenance, Withdrawal Suspended",
                            "withdrawEnable": false,
                            "withdrawFee": "0.00000220",
                            "withdrawIntegerMultiple": "0.00000001",
                            "withdrawMax": "9999999999.99999999",
                            "withdrawMin": "0.00000440",
                            "sameAddress": true,
                            "estimatedArrivalTime": 25,
                            "busy": false,
                            "contractAddressUrl": "https://bscscan.com/token/",
                            "contractAddress": "0x7130d2a12b9bcbfae4f2634d864a1ee1ce3ead9c"
                        },
                        {
                            "addressRegex": "^[13][a-km-zA-HJ-NP-Z1-9]{25,34}$|^(bc1)[0-9A-Za-z]{39,59}$",
                            "coin": "BTC",
                            "depositEnable": true,
                            "isDefault": true,
                            "memoRegex": "",
                            "minConfirm": 1,
                            "name": "BTC",
                            "network": "BTC",
                            "specialTips": "",
                            "unLockConfirm": 2,
                            "withdrawEnable": true,
                            "withdrawFee": "0.00050000",
                            "withdrawIntegerMultiple": "0.00000001",
                            "withdrawMax": "750",
                            "withdrawMin": "0.00100000",
                            "sameAddress": false,
                            "estimatedArrivalTime": 25,
                            "busy": false,
                            "contractAddressUrl": "",
                            "contractAddress": ""
                        }
                    ],
                    "storage": "0.00000000",
                    "trading": true,
                    "withdrawAllEnable": true,
                    "withdrawing": "0.00000000"
                }
            ]
            """;

    public static final String WITHDRAW_RESPONSE = """
            {
                "id":"7213fea8e94b4a5593d507237e5a555b"
            }
            """;

    public static final String GET_DEPOSIT_ADDRESS_RESPONSE = """
            {
                "address":"TDunhSa7jkTNuKrusUTU1MUHtqXoBPKETV",
                "coin":"USDT",
                "tag":"",
                "url":"https://tronscan.org/#/address/TDunhSa7jkTNuKrusUTU1MUHtqXoBPKETV"
            }
            """;

    public static final String GET_MARGIN_BALANCES = """
            {
                  "created" : true,
                  "borrowEnabled": true,
                  "marginLevel": "11.64405625",
                  "collateralMarginLevel" : "3.2",
                  "totalAssetOfBtc": "6.82728457",
                  "totalLiabilityOfBtc": "0.58633215",
                  "totalNetAssetOfBtc": "6.24095242",
                  "TotalCollateralValueInUSDT": "5.82728457",
                  "tradeEnabled": true,
                  "transferEnabled": true,
                  "transferInEnabled": true,
                  "transferOutEnabled": true,
                  "accountType": "MARGIN_1",
                  "userAssets": [
                      {
                          "asset": "BTC",
                          "borrowed": "0.00000000",
                          "free": "0.00499500",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "0.00499500"
                      },
                      {
                          "asset": "BNB",
                          "borrowed": "201.66666672",
                          "free": "2346.50000000",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "2144.83333328"
                      },
                      {
                          "asset": "ETH",
                          "borrowed": "0.00000000",
                          "free": "0.00000000",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "0.00000000"
                      },
                      {
                          "asset": "USDT",
                          "borrowed": "0.00000000",
                          "free": "0.00000000",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "0.00000000"
                      }
                  ]
            }
            """;

    public static final String GET_MARGIN_SUBACCOUNT_BALANCES = """
            {
                  "email":"123@test.com",
                  "marginLevel": "11.64405625",
                  "totalAssetOfBtc": "6.82728457",
                  "totalLiabilityOfBtc": "0.58633215",
                  "totalNetAssetOfBtc": "6.24095242",
                  "marginTradeCoeffVo":
                   {
                        "forceLiquidationBar": "1.10000000",
                        "marginCallBar": "1.50000000",
                        "normalBar": "2.00000000"
                   },
                  "marginUserAssetVoList": [
                      {
                          "asset": "BTC",
                          "borrowed": "0.00000000",
                          "free": "0.00499500",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "0.00499500"
                      },
                      {
                          "asset": "BNB",
                          "borrowed": "201.66666672",
                          "free": "2346.50000000",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "2144.83333328"
                      },
                      {
                          "asset": "ETH",
                          "borrowed": "0.00000000",
                          "free": "0.00000000",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "0.00000000"
                      },
                      {
                          "asset": "USDT",
                          "borrowed": "0.00000000",
                          "free": "0.00000000",
                          "interest": "0.00000000",
                          "locked": "0.00000000",
                          "netAsset": "0.00000000"
                      }
                  ]
            }
            """;

    public static final String GET_TRANSFERS = """
            {
                "total": 4,
                "rows": [
                    {
                        "asset": "USDT",
                        "amount": "1",
                        "type": "MAIN_UMFUTURE",
                        "status": "CONFIRMED",
                        "tranId": 11415955596,
                        "timestamp": 1544433328000,
                        "fee": "0.1",
                        "source": "Spot Account"
                    },
                    {
                        "asset": "USDT",
                        "amount": "2",
                        "type": "MAIN_UMFUTURE",
                        "status": "CONFIRMED",
                        "tranId": 11366865406,
                        "timestamp": 1544433328000,
                        "fee": "0.2",
                        "source": "Margin Account"
                    },
                    {
                        "asset": "BTC",
                        "amount": "0.5",
                        "type": "MAIN_UMFUTURE",
                        "status": "PENDING",
                        "tranId": 11415955597,
                        "timestamp": 1544433338000,
                        "fee": "0.005",
                        "source": "Isolated Margin Account"
                    },
                    {
                        "asset": "ETH",
                        "amount": "3",
                        "type": "MAIN_UMFUTURE",
                        "status": "FAILED",
                        "tranId": 11415955598,
                        "timestamp": 1544433348000,
                        "fee": "0.03",
                        "source": "Funding Account"
                    }
                ]
            }
            """;

    public static final String GET_SPOT_EXCHANGE_INFO = """
            {
              "timezone": "UTC",
              "serverTime": 1565246363776,
              "rateLimits": [],
              "exchangeFilters": [],
              "symbols": [
                {
                  "symbol": "ETHBTC",
                  "status": "TRADING",
                  "baseAsset": "ETH",
                  "baseAssetPrecision": 8,
                  "quoteAsset": "BTC",
                  "quotePrecision": 8,
                  "quoteAssetPrecision": 8,
                  "baseCommissionPrecision": 8,
                  "quoteCommissionPrecision": 8,
                  "orderTypes": [
                    "LIMIT",
                    "LIMIT_MAKER",
                    "MARKET",
                    "STOP_LOSS",
                    "STOP_LOSS_LIMIT",
                    "TAKE_PROFIT",
                    "TAKE_PROFIT_LIMIT"
                  ],
                  "icebergAllowed": true,
                  "ocoAllowed": true,
                  "otoAllowed": true,
                  "quoteOrderQtyMarketAllowed": true,
                  "allowTrailingStop": false,
                  "cancelReplaceAllowed": false,
                  "isSpotTradingAllowed": true,
                  "isMarginTradingAllowed": true,
                  "filters": [],
                  "permissions": [],
                  "permissionSets": [
                    [
                      "SPOT",
                      "MARGIN"
                    ]
                  ],
                  "defaultSelfTradePreventionMode": "NONE",
                  "allowedSelfTradePreventionModes": [
                    "NONE"
                  ]
                }
              ],
              "sors": [
                {
                  "baseAsset": "BTC",
                  "symbols": [
                    "BTCUSDT",
                    "BTCUSDC"
                  ]
                }
              ]
            }
            """;

    public static final String GET_MARGIN_EXCHANGE_INFO = """
            {
              "symbols": [
                {
                  "symbol": "BNBBTC",
                  "base": "BNB",
                  "quote": "BTC",
                  "isBuyAllowed": true,
                  "isSellAllowed": true,
                  "isMarginTrade": true
                },
                {
                  "symbol": "TRXBTC",
                  "base": "TRX",
                  "quote": "BTC",
                  "isBuyAllowed": true,
                  "isSellAllowed": true,
                  "isMarginTrade": true,
                  "delistTime": 1704973040
                },
                {
                  "symbol": "XRPBTC",
                  "base": "XRP",
                  "quote": "BTC",
                  "isBuyAllowed": true,
                  "isSellAllowed": true,
                  "isMarginTrade": true
                },
                {
                  "symbol": "ETHBTC",
                  "base": "ETH",
                  "quote": "BTC",
                  "isBuyAllowed": true,
                  "isSellAllowed": true,
                  "isMarginTrade": true
                }
              ]
            }
            """;

    public static final String GET_USD_FUTURES_EXCHANGE_INFO = """
            {
              "exchangeFilters": [],
              "rateLimits": [
                {
                  "interval": "MINUTE",
                  "intervalNum": 1,
                  "limit": 2400,
                  "rateLimitType": "REQUEST_WEIGHT"
                },
                {
                  "interval": "MINUTE",
                  "intervalNum": 1,
                  "limit": 1200,
                  "rateLimitType": "ORDERS"
                }
              ],
              "serverTime": 1565613908500,
              "assets": [
                {
                  "asset": "BUSD",
                  "marginAvailable": true,
                  "autoAssetExchange": 0
                },
                {
                  "asset": "USDT",
                  "marginAvailable": true,
                  "autoAssetExchange": 0
                },
                {
                  "asset": "BNB",
                  "marginAvailable": false,
                  "autoAssetExchange": null
                }
              ],
              "symbols": [
                {
                  "symbol": "BLZUSDT",
                  "pair": "BLZUSDT",
                  "contractType": "PERPETUAL",
                  "deliveryDate": 4133404800000,
                  "onboardDate": 1598252400000,
                  "status": "TRADING",
                  "baseAsset": "BLZ",
                  "quoteAsset": "USDT",
                  "marginAsset": "USDT",
                  "pricePrecision": 5,
                  "quantityPrecision": 0,
                  "baseAssetPrecision": 8,
                  "quotePrecision": 8,
                  "filters": [
                    {
                      "filterType": "PRICE_FILTER",
                      "maxPrice": "300",
                      "minPrice": "0.0001",
                      "tickSize": "0.0001"
                    },
                    {
                      "filterType": "LOT_SIZE",
                      "maxQty": "10000000",
                      "minQty": "1",
                      "stepSize": "1"
                    },
                    {
                      "filterType": "MARKET_LOT_SIZE",
                      "maxQty": "590119",
                      "minQty": "1",
                      "stepSize": "1"
                    },
                    {
                      "filterType": "MAX_NUM_ORDERS",
                      "limit": 200
                    },
                    {
                      "filterType": "MAX_NUM_ALGO_ORDERS",
                      "limit": 10
                    },
                    {
                      "filterType": "MIN_NOTIONAL",
                      "notional": "5.0"
                    },
                    {
                      "filterType": "PERCENT_PRICE",
                      "multiplierUp": "1.1500",
                      "multiplierDown": "0.8500",
                      "multiplierDecimal": 4
                    }
                  ],
                  "OrderType": [
                    "LIMIT",
                    "MARKET",
                    "STOP",
                    "STOP_MARKET",
                    "TAKE_PROFIT",
                    "TAKE_PROFIT_MARKET",
                    "TRAILING_STOP_MARKET"
                  ],
                  "timeInForce": [
                    "GTC",
                    "IOC",
                    "FOK",
                    "GTX"
                  ],
                  "liquidationFee": "0.010000",
                  "marketTakeBound": "0.30"
                }
              ],
              "timezone": "UTC"
            }
            """;

    public static final String GET_COIN_FUTURES_EXCHANGE_INFO = """
            {
              "exchangeFilters": [],
              "rateLimits": [
                {
                  "interval": "MINUTE",
                  "intervalNum": 1,
                  "limit": 6000,
                  "rateLimitType": "REQUEST_WEIGHT"
                },
                {
                  "interval": "MINUTE",
                  "intervalNum": 1,
                  "limit": 6000,
                  "rateLimitType": "ORDERS"
                }
              ],
              "serverTime": 1565613908500,
              "symbols": [
                {
                  "symbol": "BTCUSD_200925",
                  "pair": "BTCUSD",
                  "contractType": "CURRENT_QUARTER",
                  "deliveryDate": 1601020800000,
                  "onboardDate": 1590739200000,
                  "status": "TRADING",
                  "baseAsset": "BTC",
                  "quoteAsset": "USD",
                  "marginAsset": "BTC",
                  "pricePrecision": 1,
                  "quantityPrecision": 0,
                  "baseAssetPrecision": 8,
                  "quotePrecision": 8,
                  "filters": [
                    {
                      "filterType": "PRICE_FILTER",
                      "maxPrice": "100000",
                      "minPrice": "0.1",
                      "tickSize": "0.1"
                    },
                    {
                      "filterType": "LOT_SIZE",
                      "maxQty": "100000",
                      "minQty": "1",
                      "stepSize": "1"
                    },
                    {
                      "filterType": "MARKET_LOT_SIZE",
                      "maxQty": "100000",
                      "minQty": "1",
                      "stepSize": "1"
                    },
                    {
                      "filterType": "MAX_NUM_ORDERS",
                      "limit": 200
                    },
                    {
                      "filterType": "PERCENT_PRICE",
                      "multiplierUp": "1.0500",
                      "multiplierDown": "0.9500",
                      "multiplierDecimal": 4
                    }
                  ],
                  "OrderType": [
                    "LIMIT",
                    "MARKET",
                    "STOP",
                    "TAKE_PROFIT",
                    "TRAILING_STOP_MARKET"
                  ],
                  "timeInForce": [
                    "GTC",
                    "IOC",
                    "FOK",
                    "GTX"
                  ],
                  "liquidationFee": "0.010000",
                  "marketTakeBound": "0.30"
                }
              ],
              "timezone": "UTC"
            }
            """;

    public static final String GET_FUTURE_USDM_BALANCES = """
            [
                 {
                     "accountAlias": "SgsR",
                     "asset": "USDT",
                     "balance": "122607.35137903",
                     "crossWalletBalance": "23.72469206",
                     "crossUnPnl": "0.00000000",
                     "availableBalance": "23.72469206",
                     "maxWithdrawAmount": "23.72469206",
                     "marginAvailable": true,
                     "updateTime": 1617939110373
                }
            ]
            """;

    public static final String GET_FUTURE_COINM_BALANCES = """
            [
                 {
                     "accountAlias": "SgsR",
                     "asset": "BTC",
                     "balance": "0.00250000",
                     "withdrawAvailable": "0.00250000",
                     "crossWalletBalance": "0.00241969",
                     "crossUnPnl": "0.00000000",
                     "availableBalance": "0.00241969",
                     "updateTime": 1592468353979
                }
            ]
            """;

    public static final String GET_SPOT_TRADES = """
        [
          {
            "symbol": "BNBBTC",
            "id": 28457,
            "orderId": 100234,
            "orderListId": -1,
            "price": "4.00000100",
            "qty": "12.00000000",
            "quoteQty": "48.000012",
            "commission": "10.10000000",
            "commissionAsset": "BNB",
            "time": 1499865549590,
            "isBuyer": true,
            "isMaker": false,
            "isBestMatch": true
          },
          {
            "symbol": "ETHBTC",
            "id": 28458,
            "orderId": 100235,
            "orderListId": -1,
            "price": "0.06789000",
            "qty": "50.00000000",
            "quoteQty": "3.394500",
            "commission": "0.05000000",
            "commissionAsset": "ETH",
            "time": 1499865549600,
            "isBuyer": false,
            "isMaker": true,
            "isBestMatch": true
          },
          {
            "symbol": "BTCUSDT",
            "id": 28459,
            "orderId": 100236,
            "orderListId": -1,
            "price": "25000.00000000",
            "qty": "0.50000000",
            "quoteQty": "12500.000000",
            "commission": "0.01000000",
            "commissionAsset": "BTC",
            "time": 1499865549700,
            "isBuyer": true,
            "isMaker": false,
            "isBestMatch": false
          }
        ]
        """;

    public static final String GET_MARGIN_TRADES = """
        [
            {
                "commission": "0.00006000",
                "commissionAsset": "BTC",
                "id": 34,
                "isBestMatch": true,
                "isBuyer": false,
                "isMaker": false,
                "orderId": 39324,
                "price": "0.02000000",
                "qty": "3.00000000",
                "symbol": "BNBBTC",
                "isIsolated": false,
                "time": 1561973357171
            },
            {
                "commission": "0.00005000",
                "commissionAsset": "ETH",
                "id": 35,
                "isBestMatch": true,
                "isBuyer": true,
                "isMaker": false,
                "orderId": 39325,
                "price": "0.03000000",
                "qty": "5.00000000",
                "symbol": "ETHBTC",
                "isIsolated": true,
                "time": 1561973357271
            },
            {
                "commission": "0.00007000",
                "commissionAsset": "BNB",
                "id": 36,
                "isBestMatch": true,
                "isBuyer": false,
                "isMaker": true,
                "orderId": 39326,
                "price": "0.05000000",
                "qty": "2.00000000",
                "symbol": "BNBBTC",
                "isIsolated": false,
                "time": 1561973357371
            },
            {
                "commission": "0.00004000",
                "commissionAsset": "USDT",
                "id": 37,
                "isBestMatch": true,
                "isBuyer": true,
                "isMaker": false,
                "orderId": 39327,
                "price": "0.01000000",
                "qty": "10.00000000",
                "symbol": "BTCUSDT",
                "isIsolated": true,
                "time": 1561973357471
            }
        ]
        """;

    public static final String GET_FUTURE_USDM_SUBACCOUNT_BALANCES = """
            {
                 "futureAccountResp": {
                 "email": "abc@test.com",
                 "assets":[
                     {
                           "asset": "USDT",
                            "initialMargin": "0.00000000",
                            "maintenanceMargin": "0.00000000",
                            "marginBalance": "0.88308000",
                            "maxWithdrawAmount": "0.88308000",
                            "openOrderInitialMargin": "0.00000000",
                            "positionInitialMargin": "0.00000000",
                            "unrealizedProfit": "0.00000000",
                            "walletBalance": "0.88308000"
                      }
                 ],
                 "canDeposit": true,
                 "canTrade": true,
                 "canWithdraw": true,
                 "feeTier": 2,
                 "maxWithdrawAmount": "0.88308000",
                 "totalInitialMargin": "0.00000000",
                 "totalMaintenanceMargin": "0.00000000",
                 "totalMarginBalance": "0.88308000",
                 "totalOpenOrderInitialMargin": "0.00000000",
                 "totalPositionInitialMargin": "0.00000000",
                 "totalUnrealizedProfit": "0.00000000",
                 "totalWalletBalance": "0.88308000",
                 "updateTime": 1576756674610
              }
            }
            """;

    public static final String GET_FUTURE_COINM_SUBACCOUNT_BALANCES = """
            {
                 "deliveryAccountResp": {
                     "email": "abc@test.com",
                     "assets":[
                         {
                             "asset": "BTC",
                             "initialMargin": "0.00000000",
                             "maintenanceMargin": "0.00000000",
                             "marginBalance": "0.88308000",
                             "maxWithdrawAmount": "0.88308000",
                             "openOrderInitialMargin": "0.00000000",
                             "positionInitialMargin": "0.00000000",
                             "unrealizedProfit": "0.00000000",
                             "walletBalance": "0.88308000"
                          }
                     ],
                     "canDeposit": true,
                     "canTrade": true,
                     "canWithdraw": true,
                     "feeTier": 2,
                     "updateTime": 1598959682001
                }
            }
            """;

    public static final String GET_FUTURE_COINM_TRADES = """
        [
            {
                "symbol": "BTCUSD_200626",
                "id": 6,
                "orderId": 28,
                "pair": "BTCUSD",
                "side": "SELL",
                "price": "8800",
                "qty": "1",
                "realizedPnl": "0",
                "marginAsset": "BTC",
                "baseQty": "0.01136364",
                "commission": "0.00000454",
                "commissionAsset": "BTC",
                "time": 1590743483586,
                "positionSide": "BOTH",
                "buyer": false,
                "maker": false
            },
            {
                "symbol": "ETHUSD_210626",
                "id": 7,
                "orderId": 29,
                "pair": "ETHUSD",
                "side": "BUY",
                "price": "2500",
                "qty": "2",
                "realizedPnl": "10",
                "marginAsset": "ETH",
                "baseQty": "0.00123456",
                "commission": "0.00000321",
                "commissionAsset": "ETH",
                "time": 1590743484000,
                "positionSide": "LONG",
                "buyer": true,
                "maker": true
            },
            {
                "symbol": "BTCUSD_210726",
                "id": 8,
                "orderId": 30,
                "pair": "BTCUSD",
                "side": "BUY",
                "price": "9000",
                "qty": "3",
                "realizedPnl": "5",
                "marginAsset": "BTC",
                "baseQty": "0.01100000",
                "commission": "0.00000654",
                "commissionAsset": "BTC",
                "time": 1590743493586,
                "positionSide": "SHORT",
                "buyer": true,
                "maker": false
            }
        ]
        """;

    public static final String GET_FUTURE_USDM_TRADES = """
        [
          {
            "buyer": false,
            "commission": "-0.07819010",
            "commissionAsset": "USDT",
            "id": 698759,
            "maker": false,
            "orderId": 25851813,
            "price": "7819.01",
            "qty": "0.002",
            "quoteQty": "15.63802",
            "realizedPnl": "-0.91539999",
            "side": "SELL",
            "positionSide": "SHORT",
            "symbol": "BTCUSDT",
            "time": 1569514978020
          },
          {
            "buyer": true,
            "commission": "-0.00219010",
            "commissionAsset": "BTC",
            "id": 698760,
            "maker": true,
            "orderId": 25851814,
            "price": "7800.00",
            "qty": "0.004",
            "quoteQty": "31.20000",
            "realizedPnl": "0.31539999",
            "side": "BUY",
            "positionSide": "LONG",
            "symbol": "ETHUSDT",
            "time": 1569514989020
          },
          {
            "buyer": false,
            "commission": "-0.05819010",
            "commissionAsset": "USDT",
            "id": 698761,
            "maker": false,
            "orderId": 25851815,
            "price": "9000.50",
            "qty": "0.003",
            "quoteQty": "27.00150",
            "realizedPnl": "-1.25039999",
            "side": "SELL",
            "positionSide": "SHORT",
            "symbol": "BNBUSDT",
            "time": 1569514990020
          }
        ]
        """;

}
