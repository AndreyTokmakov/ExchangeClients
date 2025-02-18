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
//import io.gate.gateapi.api.AccountApi;
import io.gate.gateapi.api.WalletApi;

public class DemoApplication
{
	private final static String apiKey = "bbce3dbaeec150f889732db966ed60af";
	private final static String apiSecret = "2a5c77290337277d78451c0d9ac117f692c9cb05fa962847da061e37b8834c28";

	public static void test() throws ApiException
	{
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		// defaultClient.setBasePath("https://api.gateio.ws/api/v4");
		defaultClient.setBasePath("https://fx-api-testnet.gateio.ws/api/v4");
		defaultClient.setApiKeySecret(apiKey, apiSecret);

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
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		// defaultClient.setBasePath("https://api.gateio.ws/api/v4");
		defaultClient.setBasePath("https://fx-api-testnet.gateio.ws/api/v4");
		defaultClient.setApiKeySecret(apiKey, apiSecret);

		WalletApi apiInstance = new WalletApi(defaultClient);
		String currency = "\"USDT\""; // String | Currency unit used to calculate the balance amount. BTC, CNY,
		// USD and USDT are allowed. USDT is the default.
		try {
			TotalBalance result = apiInstance.getTotalBalance()
					.currency(currency)
					.execute();
			System.out.println(result);
		} catch (GateApiException e) {
			System.err.println(String.format("Gate api exception, label: %s, message: %s", e.getErrorLabel(), e.getMessage()));
			e.printStackTrace();
		} catch (ApiException e) {
			System.err.println("Exception when calling WalletApi#getTotalBalance");
			System.err.println("Status code: " + e.getCode());
			System.err.println("Response headers: " + e.getResponseHeaders());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ApiException
	{

		balance();
	}
}