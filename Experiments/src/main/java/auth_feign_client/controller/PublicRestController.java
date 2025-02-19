package auth_feign_client.controller;

import auth_feign_client.service.ApiService;
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

    public PublicRestController() {
        log.info("{} created!", this.getClass().getSimpleName());
    }

    @GetMapping("/info")
    public @ResponseBody String getUserWalletBalance()
    {
        log.info("PublicRestController::getAccount()");
        final String infoString = apiService.getUserInfo();
        return infoString;
    }
}
