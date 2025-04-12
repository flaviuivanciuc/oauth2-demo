package dev.oauth2.resource_server_demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/private")
    @PreAuthorize("hasAnyRole('ADMIN', 'user)")
    public Map<String, String> securedEndpoint(@AuthenticationPrincipal Jwt jwt, @RequestHeader("X-User-Email") String email) {
        return Map.of(
                "message", "Hello, " + email + "! This is a protected API."
        );
    }
}
