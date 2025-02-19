package auth_feign_client.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(url = "0.0.0.0:50001",  name = "ByBitClient")
public interface TestServiceClient
{
    @GetMapping(value = "/users/me", produces = "application/json")
    public String getUserInfo();
}
