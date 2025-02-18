package demo;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.futures.client.FuturesClient;
import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DemoApplication
{
	private final static String apiKey = "IbG9hQF1HwJJZKCGK7ssllRwte52YYCHYw0zxOewooYtl8wK5xEZTwri3DYSNS2k";
	private final static String secretKey = "SXSUfmOqfduTUfQyy44NaufOE7n4mfnYmDfvlHZPtzwsZyq9QlpTuKUXIfxWzMVi";

	// private final static String baseUrl = "http://0.0.0.0:52525";
	private final static String baseUrl = "https://testnet.binance.vision";

	private final SpotClientImpl binanceClient;

	public DemoApplication()
	{
		this.binanceClient = new SpotClientImpl(apiKey, secretKey, baseUrl);
	}

	public void getBinanceSubAccounts()
	{
		Map<String,Object> params = new HashMap<String,Object>();
		final String jsonString = binanceClient.createSubAccount().subAccountList(params);

		//TypeReference<BinanceSubAccountsDto> typeReference = new TypeReference<>() {};
		// return objectMapper.readValue(subAcountsJsonString, typeReference);
		System.out.println(jsonString);
	}

	public void getActualBalance()
	{
		final Map<String,Object> params = new HashMap<String,Object>();
		params.put("symbol","BTCUSDT");

		final String jsonString = binanceClient.createTrade().account(params);
		// TypeReference<BinanceActualBalanceDto> typeReference = new TypeReference<>() {};
		// return objectMapper.readValue(balanceJsonString, typeReference);
		System.out.println(jsonString);
	}

	public void getTime()
	{
		final String jsonString = binanceClient.createMarket().time();
		System.out.println(jsonString);
	}

	public void getTrades()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("symbol", "BTCUSDT");

		final String jsonString = binanceClient.createTrade().myTrades(params);
		System.out.println(jsonString);
	}

	public void getExchangeInfo()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("symbol", "BTCUSDT");

		String jsonString = binanceClient.createMarket().exchangeInfo(params);
		System.out.println(jsonString);
	}

	public void getFuturesAccountV2()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("futuresType", 2);
		params.put("email","abc@test.com");

		String jsonString = binanceClient.createSubAccount().futuresAccountV2(params);
		System.out.println(jsonString);
	}

	public void getDepositHistory()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("symbol", "BTCUSDT");

		String jsonString = binanceClient.createWallet().depositHistory(params);
		System.out.println(jsonString);
	}

	public static void main(String[] args)
	{
		final DemoApplication testApp = new DemoApplication();

		// testApp.getTime();
		// testApp.getTrades();
		// testApp.getExchangeInfo();
		// testApp.getActualBalance();
		// testApp.getBinanceSubAccounts();
		// testApp.getFuturesAccountV2();
		testApp.getDepositHistory();
	}
}