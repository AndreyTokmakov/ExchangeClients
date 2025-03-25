package data_mapping;

public class TestData
{
    public static String TOTAL_BALANCE = """
                {
                    "details": {
                        "cross_margin": {
                            "amount": "0",
                            "currency": "USDT"
                        },
                        "spot": {
                            "currency": "USDT",
                            "amount": "42264489969935775.5160259954878034182418"
                        },
                        "finance": {
                            "amount": "662714381.70310327810191647181",
                            "currency": "USDT"
                        },
                        "margin": {
                            "amount": "1259175.664137668554329559",
                            "currency": "USDT",
                            "borrowed": "0.00"
                        },
                        "quant": {
                            "amount": "591702859674467879.6488202650892478553852",
                            "currency": "USDT"
                        },
                        "futures": {
                            "amount": "2384175.5606114082065",
                            "currency": "USDT",
                            "unrealised_pnl": "0.00"
                        },
                        "delivery": {
                            "currency": "USDT",
                            "amount": "1519804.9756702",
                            "unrealised_pnl": "0.00"
                        },
                        "warrant": {
                            "amount": "0",
                            "currency": "USDT"
                        },
                        "cbbc": {
                            "currency": "USDT",
                            "amount": "0"
                        }
                    },
                    "total": {
                        "currency": "USDT",
                        "amount": "633967350312281193.068368815439797304437",
                        "borrowed": "0.00"
                    }
                }""";

    public static final String SUB_ACCOUNT_MARGIN_BALANCES = """
            [
              {
                "uid": "10000",
                "available": [
                  {
                    "locked": false,
                    "currency_pair": "BTC_USDT",
                    "risk": "9999.99",
                    "base": {
                      "available": "0.1",
                      "borrowed": "0",
                      "interest": "0",
                      "currency": "BTC",
                      "locked": "0"
                    },
                    "quote": {
                      "available": "0",
                      "borrowed": "0",
                      "interest": "0",
                      "currency": "USDT",
                      "locked": "0"
                    }
                  },
                  {
                    "locked": false,
                    "currency_pair": "ETH_USDT",
                    "risk": "9999.99",
                    "base": {
                      "available": "2.1",
                      "borrowed": "0",
                      "interest": "0",
                      "currency": "ETH",
                      "locked": "0"
                    },
                    "quote": {
                      "available": "0",
                      "borrowed": "0",
                      "interest": "0",
                      "currency": "USDT",
                      "locked": "0"
                    }
                  }
                ]
              }
            ]
                }""";

    public static final String SUB_ACCOUNT_CROSS_MARGIN_BALANCES = """
        [
            {
                "uid": "100000",
                "available":
                {
                    "user_id": 100003,
                    "locked": false,
                    "total": "20.000000",
                    "borrowed": "0.000000",
                    "interest": "0",
                    "borrowed_net": "0",
                    "net": "20",
                    "leverage": "3",
                    "risk": "9999.99",
                    "total_initial_margin": "0.00",
                    "total_margin_balance": "20.00",
                    "total_maintenance_margin": "0.00",
                    "total_initial_margin_rate": "9999.9900",
                    "total_maintenance_margin_rate": "9999.9900",
                    "total_available_margin": "20.00",
                    "balances":
                    {
                        "USDT":
                        {
                            "available": "20.000000",
                            "freeze": "0.000000",
                            "borrowed": "0.000000",
                            "interest": "0.000000"
                        }
                    }
                }
            }
        ]
    """;


    public static final String SUB_ACCOUNT_BALANCES = """
            [
              {
                "uid": "10001",
                "available": {
                  "BTC": "0.1",
                  "GT": "2000",
                  "USDT": "10"
                }
              },
              {
                "uid": "10002",
                "available": {
                  "BTC": "0.1",
                  "GT": "2000",
                  "USDT": "10"
                }
              }
            ]
    """;

    public static final String SUB_ACCOUNT_FUTURE_BALANCES = """
        [
            {
                "uid": "10001",
                "available":
                {
                    "BTC": "0.1",
                    "GT": "1000",
                    "USDT": "10"
                }
            },
            {
                "uid": "10002",
                "available":
                {
                    "BTC": "0.2",
                    "GT": "2000",
                    "USDT": "20"
                }
            },
            {
                "uid": "10003",
                "available":
                {
                    "BTC": "0.3",
                    "GT": "3000",
                    "USDT": "30"
                }
            }
        ]
    """;

    public static void main(String[] args) {
        System.out.println(TOTAL_BALANCE);
    }
}
