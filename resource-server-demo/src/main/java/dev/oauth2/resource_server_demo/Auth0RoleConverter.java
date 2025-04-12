package dev.oauth2.resource_server_demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Auth0RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public static final String ROLES = "https://resource-server-demo-api-v1/claims/roles";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> claims = jwt.getClaims();
        List<String> roles = (List<String>) claims.getOrDefault(ROLES, new ArrayList());

        if (roles != null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }

        return authorities;
    }
}
