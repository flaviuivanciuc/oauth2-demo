package dev.oauth2.resource_server_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final MultiIssuerJwtDecoder jwtDecoder;
    private final MultiTenantRoleConverter multiTenantRoleConverter;
    private final ServiceProperties serviceProperties;

    public SecurityConfig(MultiIssuerJwtDecoder jwtDecoder, MultiTenantRoleConverter multiTenantRoleConverter, ServiceProperties serviceProperties) {
        this.jwtDecoder = jwtDecoder;
        this.multiTenantRoleConverter = multiTenantRoleConverter;
        this.serviceProperties = serviceProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(new ApiKeyAuthFilter(serviceProperties.apiKeys()), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/private")
                        .hasAnyRole("ADMIN", "user", "SERVICE_A", "SERVICE_B")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .decoder(jwtDecoder)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(multiTenantRoleConverter);
        return converter;
    }
}
