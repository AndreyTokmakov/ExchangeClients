package auth;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OAuth2AuthenticationClient
{
    private final static OkHttpClient client = new OkHttpClient();

    protected static void printHeaders(Response response)
    {
        final Headers responseHeaders = response.headers();
        System.out.println("Headers:  ");
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println("  " + responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
    }

    public void simpleRequest(String url)
    {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            System.out.println(result);
        } catch (final Exception exc) {
            System.err.println(exc.getMessage());
        }
    }

    public void requestAsync(String url)
    {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public void onResponse(@NotNull Call call, @NotNull Response response)
                    throws IOException {
                assert response.body() != null;
                String result = response.body().string();
                printHeaders(response);
                System.out.println(result);
            }

            public void onFailure(@NotNull Call call, @NotNull IOException exc) {
                System.err.println(exc);
            }
        });
    }

    public static void main(String[] args)
    {
        OAuth2AuthenticationClient client = new OAuth2AuthenticationClient();

        // client.simpleRequest("https://httpbin.org/get");
        client.simpleRequest("http://0.0.0.0:50001");
        // client.requestAsync("http://0.0.0.0:50001");
    }
}
