package dev.oauth2.resource_server_demo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtIssuerProperties.class, ServiceProperties.class})
public class AppConfig {
}
