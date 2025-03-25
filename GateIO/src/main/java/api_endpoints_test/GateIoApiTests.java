package api_endpoints_test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.SubAccountBalanceDto;
import dto.SubAccountMarginBalanceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import okhttp3.*;

public class GateIoApiTests
{
    private final static String propFilePath =
            "/home/andtokm/Projects/M2/ExchangeClients/GateIO/src/main/resources/credentials.properties";

    public record ApiKeys(String key, String secret){
    }

    private final static OkHttpClient httpClient = new OkHttpClient();

    private final static ObjectMapper mapper = new ObjectMapper();

    @Getter
    @AllArgsConstructor
    enum Environment
    {
        Real("real", "https://api.gateio.ws/api/v4"),
        TestNet("testnet", "https://fx-api-testnet.gateio.ws/api/v4"),
        Mock("testnet", "http://127.0.0.1:50002/api/v4");

        private final String name;
        private final String url;
    }

    public static Optional<ApiKeys> getProperties(Environment env) throws IOException
    {
        try (final FileInputStream stream = new FileInputStream(propFilePath))
        {
            final Properties properties = new Properties();
            properties.load(stream);
            return Optional.of(new ApiKeys(properties.getProperty(String.format("gate_io.%s.api_key", env.name)),
                    properties.getProperty(String.format("gate_io.%s.api_secret", env.name))));
        } catch(final IOException exc) {
            System.err.println(exc.getMessage());
            return Optional.empty();
        }
    }

    public static String getRequest(String url) throws IOException
    {
        Request request = new Request.Builder().url(url).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void getTotalBalances() throws IOException
    {
        String response = getRequest(Environment.Mock.getUrl() + "/wallet/total_balance");
    }

    public static <T> Optional<T> readValue(String data,
                                            com.fasterxml.jackson.core.type.TypeReference<T> typeReference) {
        try {
            final T result = mapper.readValue(data, typeReference);
            return Optional.of(result);
        } catch (final Exception exc) {
            System.err.println(exc.getMessage());
            return Optional.empty();
        }
    }

    public static void getSubAccountBalances() throws IOException
    {
        // https://www.gate.io/docs/developers/apiv4/#retrieve-sub-account-balances
        // TODO: Support 'sub_uid' parameter

        final String response = getRequest(Environment.Mock.getUrl() + "/wallet/sub_account_balances");
        System.out.println(response);

        Optional<List<SubAccountBalanceDto>> result = readValue(response,
                new TypeReference<List<SubAccountBalanceDto>>() {});
        result.ifPresent(System.out::println);
    }

    public static void getSubAccountMarginBalances() throws IOException
    {
        // https://www.gate.io/docs/developers/apiv4/#query-sub-accounts-margin-balances
        // TODO: Support 'sub_uid' parameter

        final String response = getRequest(Environment.Mock.getUrl() + "/wallet/sub_account_margin_balances");
        System.out.println(response);

        final Optional<List<SubAccountMarginBalanceDto>> result = readValue(response,
                new TypeReference<List<SubAccountMarginBalanceDto>>() {});
        if (result.isPresent())
        {
            var balancesList = result.get();
            System.out.println(balancesList.get(0));
        }
    }

    public static void main(String[] args) throws IOException
    {
        // Optional<ApiKeys> keys = getProperties(Environment.TestNet);
        // keys.ifPresent(System.out::println);

        // getTotalBalances();
        // getSubAccountBalances();
        getSubAccountMarginBalances();
    }
}
