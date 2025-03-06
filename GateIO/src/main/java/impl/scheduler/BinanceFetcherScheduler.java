package impl.scheduler;


import impl.service.ApiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Component
public class BinanceFetcherScheduler
{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ApiService apiService;

    public BinanceFetcherScheduler() {
        log.info("{} created!", this.getClass().getSimpleName());
    }

    @Scheduled(fixedRate = 1000)
    public void fetchBalances()
    {
        log.info("fetchBalances: The time is now {}", dateFormat.format(new Date()));
        final String balanceResponse = apiService.getAccountBalance();
        log.info("Balance now: {}", balanceResponse);
    }
}
