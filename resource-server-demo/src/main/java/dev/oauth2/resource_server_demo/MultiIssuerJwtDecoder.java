package dev.oauth2.resource_server_demo;

import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MultiIssuerJwtDecoder implements JwtDecoder {

    private final Map<String, JwtDecoder> jwtDecoders = new HashMap<>();
    private final Map<String, String> rolesClaimMap = new HashMap<>();

    public MultiIssuerJwtDecoder(JwtIssuerProperties jwtIssuerProperties) {
        jwtIssuerProperties.getIssuers().forEach((key, issuer) -> {
            JwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuer.issuerUri());
            ((NimbusJwtDecoder) decoder).setJwtValidator(JwtValidators.createDefaultWithIssuer(issuer.issuerUri()));

            jwtDecoders.put(issuer.issuerUri(), decoder);
            rolesClaimMap.put(issuer.issuerUri(), issuer.rolesClaim());
        });
    }

    @Override
    public Jwt decode(String token) {
        String issuer = JwtUtils.extractIssuer(token);
        JwtDecoder jwtDecoder = jwtDecoders.get(issuer);
        if (jwtDecoder == null) {
            throw new JwtException("Unrecognized issuer: " + issuer);
        }
        return jwtDecoder.decode(token);
    }

    public String getRolesClaim(String issuer) {
        return rolesClaimMap.get(issuer);
    }
}
