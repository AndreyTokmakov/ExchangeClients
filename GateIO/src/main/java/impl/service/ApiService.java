package impl.service;

import impl.client.GateIoAccountClient;
import impl.client.GateIoAuthClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ApiService
{
    @Autowired
    private GateIoAccountClient accountClient;

    public ApiService() {
        log.info("{} created!", this.getClass().getSimpleName());
    }

    public String getAccountBalance()
    {
        final String extInfo = accountClient.getAccountBalance("111222222");
        return extInfo;
    }
}