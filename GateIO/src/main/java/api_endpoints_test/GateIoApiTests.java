package api_endpoints_test;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

public class GateIoApiTests
{
    private final static String propFilePath =
            "/home/andtokm/Projects/M2/ExchangeClients/GateIO/src/main/resources/credentials.properties";

    public record ApiKeys(String key, String secret){
    }

    @Getter
    @AllArgsConstructor
    enum Environment
    {
        Real("real", "https://api.gateio.ws/api/v4"),
        TestNet("testnet", "https://fx-api-testnet.gateio.ws/api/v4");

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

    public static void main(String[] args) throws IOException
    {
        Optional<ApiKeys> keys = getProperties(Environment.TestNet);
        keys.ifPresent(System.out::println);

        keys = getProperties(Environment.Real);
        keys.ifPresent(System.out::println);
    }
}
