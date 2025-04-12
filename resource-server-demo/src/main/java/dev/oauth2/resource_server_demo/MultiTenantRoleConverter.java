package dev.oauth2.resource_server_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MultiTenantRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final Map<String, String> issuerRolesClaimMap;

    @Autowired
    public MultiTenantRoleConverter(JwtIssuerProperties jwtIssuerProperties) {
        this.issuerRolesClaimMap = jwtIssuerProperties.getIssuers().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getValue().issuerUri(),
                        entry -> entry.getValue().rolesClaim()
                ));
    }


    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        String issuer = Optional.ofNullable(jwt.getIssuer())
                .map(Object::toString)
                .orElse("");

        String rolesClaim = issuerRolesClaimMap.getOrDefault(issuer, "roles");

        return Optional.ofNullable(jwt.getClaimAsStringList(rolesClaim)).stream()
                .flatMap(Collection::stream)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
