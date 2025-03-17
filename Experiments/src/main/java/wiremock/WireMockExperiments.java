package wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
// import static org.junit.jupiter.api.Assertions.assertEquals;

public class WireMockExperiments
{
    private static final String path = "/testPath";

    private final static String host = "0.0.0.0";
    private final static int port = 52525;

    public static String convertResponseToString(HttpResponse response)
    {
        try {
            InputStream responseStream = response.getEntity().getContent();
            Scanner scanner = new Scanner(responseStream, StandardCharsets.UTF_8);
            String responseString = scanner.useDelimiter("\\Z").next();
            scanner.close();
            return responseString;
        }
        catch (final UnsupportedOperationException | IOException exc) {
            exc.printStackTrace();
        }
        return "";
    }

    private static void Sleep(final long milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {}
    }

    protected static void SimpleRequest() throws InterruptedException, IOException
    {
        Options options = new WireMockConfiguration().port(port).bindAddress(host);
        WireMockServer wireMockServer = new WireMockServer(options);
        wireMockServer.start();

        try (CloseableHttpClient httpClient = HttpClients.createDefault())
        {
            HttpGet request = new HttpGet("http://" + host + ":" + port);
            HttpResponse httpResponse = httpClient.execute(request);
            final String response = convertResponseToString(httpResponse);

            System.out.println("=".repeat(180) + "\n" + response + "\n" + "=".repeat(180));
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }

        wireMockServer.stop();
    }

    protected static void SimpleRequest_1() throws InterruptedException, IOException
    {
        WireMockServer wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();

        configureFor(host, port);
        stubFor(get(urlEqualTo(path))
                .willReturn(aResponse().withBody("Welcome to test server")));

        try (CloseableHttpClient httpClient = HttpClients.createDefault())
        {
            HttpGet request = new HttpGet("http://" + host + ":" + port + "/" + path);
            HttpResponse httpResponse = httpClient.execute(request);
            String response = convertResponseToString(httpResponse);

            System.out.println("=".repeat(180) + "\n" + response + "\n" + "=".repeat(180));
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }

        wireMockServer.stop();
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        SimpleRequest();
        // SimpleRequest_1();
    }
}
