package dev.oauth2.resource_server_demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "spring.security.oauth2.resource-server.jwt")
public record JwtIssuerProperties(Map<String, Issuer> issuers) {
    public record Issuer(String issuerUri, String rolesClaim) {
    }
}
