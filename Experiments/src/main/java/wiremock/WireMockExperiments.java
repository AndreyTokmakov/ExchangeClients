package wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
// import static org.junit.jupiter.api.Assertions.assertEquals;

public class WireMockExperiments
{
    private static final String path = "/testPath";

    private final static String host = "0.0.0.0";
    private final static int port = 52525;

    private static String convertResponseToString(HttpResponse response) throws IOException
    {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String stringResponse = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return stringResponse;
    }

    private static void Sleep(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args)
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
            String stringResponse = convertResponseToString(httpResponse);

            System.out.println("".repeat(180));
            System.out.println(stringResponse);
            System.out.println("".repeat(180));
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }

        wireMockServer.stop();
    }
}
