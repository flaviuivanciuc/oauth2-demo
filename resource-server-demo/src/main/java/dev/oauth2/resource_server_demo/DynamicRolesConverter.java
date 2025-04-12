package dev.oauth2.resource_server_demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;

public class DynamicRolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final MultiIssuerJwtDecoder jwtDecoder;

    public DynamicRolesConverter(MultiIssuerJwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        String issuer = jwt.getIssuer().toString();
        String rolesClaimName = jwtDecoder.getRolesClaim(issuer);

        List<String> roles = jwt.getClaimAsStringList(rolesClaimName);
        return roles.stream()
                .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(String.format("ROLE_%s", role)))
                .toList();
    }
}
