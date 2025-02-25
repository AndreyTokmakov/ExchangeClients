package impl.controller;

import impl.service.ApiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping("public")
public class PublicRestController
{
    @Autowired
    private ApiService apiService;

    @Autowired
    public PublicRestController() {
        log.info("{} created!", this.getClass().getSimpleName());
    }

    @GetMapping("/account_balance")
    public @ResponseBody String getAccountBalance()
    {
        log.info("PublicRestController::getAccountBalance()");
        final String balanceResponse = apiService.getAccountBalance();
        return balanceResponse;
    }
}