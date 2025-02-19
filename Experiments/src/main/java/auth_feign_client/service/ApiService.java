package auth_feign_client.service;

import auth_feign_client.client.TestServiceClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ApiService
{
    @Autowired
    private TestServiceClient testClient;

    public ApiService() {
        log.info("{} created!", this.getClass().getSimpleName());
    }

    public String getUserInfo()
    {
        log.info("ByBitService::getAccount()");
        final String extInfo = testClient.getUserInfo();
        return extInfo;
    }
}