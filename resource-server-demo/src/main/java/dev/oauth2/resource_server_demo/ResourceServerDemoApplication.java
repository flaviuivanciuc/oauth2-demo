package dev.oauth2.resource_server_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JwtIssuerProperties.class)
@SpringBootApplication
public class ResourceServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerDemoApplication.class, args);
    }

}
