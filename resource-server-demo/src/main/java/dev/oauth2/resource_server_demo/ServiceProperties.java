package dev.oauth2.resource_server_demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "service")
public record ServiceProperties(Map<String, String> apiKeys) {
}
