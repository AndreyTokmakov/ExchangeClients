package impl;

import impl.service.ApiService;
import impl.service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients(basePackages = "impl.client")
@PropertySource(name = "GateIOParams", value = "application.yml")
public class AppMain
{
    public static void callAuthenticateEndpoint()
    {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppMain.class);
        ApiService apiService = applicationContext.getBean(ApiService.class);
        // AuthService authService = applicationContext.getBean(AuthService.class);
        try {
            // System.out.println(authService.authenticate());
            System.out.println(apiService.getAccountBalance());
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
        // callAuthenticateEndpoint();
        runAsRestService(args);
    }
}
