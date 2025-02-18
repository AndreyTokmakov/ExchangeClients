package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "client")
public class ApplicationMain
{
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
