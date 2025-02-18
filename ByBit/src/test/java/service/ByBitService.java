package service;

import api.dto.ByBitUserWalletBalanceResponseDto;
import client.ByBitClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ByBitService
{
    @Autowired
    private ByBitClient byBitClient;

    public ByBitService() {
        log.info("{} created!", this.getClass().getSimpleName());
    }

    public String getUserWalletBalance()
    {
        log.info("ByBitService::getAccount()");
        final ByBitUserWalletBalanceResponseDto response = byBitClient.getUserWalletBalance("UNIFIED", "USDT");
        return response.toString();
    }
}