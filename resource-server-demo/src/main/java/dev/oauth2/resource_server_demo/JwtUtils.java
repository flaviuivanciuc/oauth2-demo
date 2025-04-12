package dev.oauth2.resource_server_demo;

import com.nimbusds.jwt.SignedJWT;

public class JwtUtils {

    private JwtUtils() {
        // Utility class
    }

    public static String extractIssuer(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            return jwt.getJWTClaimsSet().getIssuer();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract issuer from token", e);
        }
    }
}
