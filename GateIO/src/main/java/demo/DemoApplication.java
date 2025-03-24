package demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Import classes:
import io.gate.gateapi.ApiClient;
import io.gate.gateapi.ApiException;
import io.gate.gateapi.Configuration;
import io.gate.gateapi.GateApiException;
import io.gate.gateapi.api.SpotApi;
import io.gate.gateapi.auth.*;
import io.gate.gateapi.models.*;
import io.gate.gateapi.api.WalletApi;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum Environment
{
	Real("https://api.gateio.ws/api/v4",
			"",
			""),

	TestNet("https://fx-api-testnet.gateio.ws/api/v4",
			"",
			"");

	private final String host;
	private final String apiKey;
	private final String apiSecret;
}

public class DemoApplication
{
	public static ApiClient getApiClient(Environment environment)
	{
		final ApiClient apiClient = Configuration.getDefaultApiClient();
		apiClient.setBasePath(environment.getHost());
		apiClient.setApiKeySecret(environment.getApiKey(), environment.getApiSecret());

		return apiClient;
	}

	public static void test() throws ApiException
	{
		final ApiClient defaultClient = getApiClient(Environment.Real);

		String currencyPair = "ETH_BTC";
		String currency = currencyPair.split("_")[1];

		SpotApi spotApi = new SpotApi(defaultClient);
		CurrencyPair pair = spotApi.getCurrencyPair(currencyPair);
		System.out.println("testing against currency pair: " + currencyPair);
		String minAmount = pair.getMinBaseAmount();

		System.out.println("minAmount = " + minAmount);

		// make sure balance is enough
		BigDecimal orderAmount = new BigDecimal(minAmount).multiply(new BigDecimal("2"));
		List<SpotAccount> accounts = spotApi.listSpotAccounts().currency(currency).execute();
		assert accounts.size() == 1;
		BigDecimal available = new BigDecimal(accounts.get(0).getAvailable());
		System.out.printf("Account available: %s %s\n", available.toPlainString(), currency);
		if (available.compareTo(orderAmount) < 0) {
			System.err.println("Account balance not enough");
			return;
		}
	}

	public static void balance()
	{
		final ApiClient defaultClient = getApiClient(Environment.Real);

		WalletApi apiInstance = new WalletApi(defaultClient);
		final String currency = "USDT"; // String | Currency unit used to calculate the balance amount. BTC, CNY,
									    // USD and USDT are allowed. USDT is the default.
		try
		{
			final TotalBalance result = apiInstance.getTotalBalance().currency(currency).execute();
			AccountBalance totalBalance = result.getTotal();
			Map<String, AccountBalance> details = result.getDetails();

			System.out.println("Total:");
			System.out.println("\t amount  :" + totalBalance.getAmount());
			System.out.println("\t currency:" + totalBalance.getCurrency());

			System.out.println("Details:");
            assert details != null;
            for (var entry: details.entrySet()) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}

		} catch (GateApiException exc)
		{
			System.err.printf("Gate api exception, label: %s, message: %s%n", exc.getErrorLabel(), exc.getMessage());
		} catch (ApiException exc)
		{
			System.err.println("Exception when calling WalletApi#getTotalBalance");
			System.err.println("Status code: " + exc.getCode());
			System.err.println("Response headers: " + exc.getResponseHeaders());
			System.err.println(exc.getMessage());
		}
	}

	// https://github.com/gateio/gateapi-java
	public static void main(String[] args) throws ApiException
	{
		// test();
		balance();
	}
}