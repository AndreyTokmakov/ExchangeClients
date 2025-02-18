package demo;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class HeadersUtils
{
    public static Map<String, Object> createBinanceRequestParams(Long startTime)
    {
        return Map.ofEntries(
                Map.entry("startTime", startTime),
                Map.entry("endTime", Instant.now().toEpochMilli()),
                Map.entry("timestamp", Instant.now().toEpochMilli()),
                Map.entry("recvWindow", 60000L)
        );
    }

    public static Map<String, Object> createBinanceRequestParams()
    {
        return Map.ofEntries(
                Map.entry("timestamp", Instant.now().toEpochMilli()),
                Map.entry("recvWindow", 60000L)
        );
    }

    public static HashMap<String, Object> createBinanceRequestParams(Map<String, String> additionalParameters)
    {
        HashMap<String, Object> params = new HashMap<>();
        params.put("timestamp", Instant.now().toEpochMilli());
        params.put("recvWindow", 60000L);
        params.putAll(additionalParameters);
        return params;
    }

    public static HashMap<String, Object> addEmailToRequestParams(HashMap<String, Object> params, String email)
    {
        params.put("email", email);
        return params;
    }
}
