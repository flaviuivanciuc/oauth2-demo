package dev.oauth2.resource_server_demo;

import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MultiIssuerJwtDecoder implements JwtDecoder {

    private final Map<String, JwtDecoder> jwtDecoders = new HashMap<>();
    private final Map<String, String> rolesClaim = new HashMap<>();

    public MultiIssuerJwtDecoder(JwtIssuerProperties jwtIssuerProperties) {
        for (Map.Entry<String, JwtIssuerProperties.Issuer> entry : jwtIssuerProperties.getIssuers().entrySet()) {
            JwtIssuerProperties.Issuer issuer = entry.getValue();

            NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(issuer.issuerUri() + ".well-known/jwks.json").build();
            jwtDecoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(issuer.issuerUri()));

            jwtDecoders.put(issuer.issuerUri(), jwtDecoder);
            rolesClaim.put(issuer.issuerUri(), issuer.rolesClaim());
        }
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
        return rolesClaim.get(issuer);
    }
}
