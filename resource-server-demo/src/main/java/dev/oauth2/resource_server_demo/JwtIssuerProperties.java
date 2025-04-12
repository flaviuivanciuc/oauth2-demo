package dev.oauth2.resource_server_demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.resource-server.jwt")
public class JwtIssuerProperties {

    private Map<String, Issuer> issuers = Map.of();

    public Map<String, Issuer> getIssuers() {
        return issuers;
    }

    public void setIssuers(Map<String, Issuer> issuers) {
        this.issuers = issuers;
    }

    public record Issuer(String issuerUri, String rolesClaim) {
    }
}
