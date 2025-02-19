package auth_feign_client;


import auth_feign_client.service.ApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableFeignClients(basePackages = "auth_feign_client.client")
public class AppMain
{
    public static void callApi()
    {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppMain.class);
        ApiService apiService = applicationContext.getBean(ApiService.class);
        try {
            apiService.getUserInfo();
        }  catch (final Exception exc) {
            System.err.println(exc.getMessage());
        }
        applicationContext.close();
    }

    public static void runAsRestService(String[] args)
    {
        SpringApplication.run(AppMain.class, args);
    }

    public static void main(String[] args)
    {
        callApi();
        // runAsRestService(args);
    }
}
