package dev.oauth2.resource_server_demo;

import org.slf4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api")
public class ApiController {

    Logger logger = getLogger(ApiController.class);

    @GetMapping("/private")
    @PreAuthorize("hasAnyRole('ADMIN', 'user', 'SERVICE_A', 'SERVICE_B')")
    public Map<String, String> securedEndpoint(@AuthenticationPrincipal Jwt jwt,
                                               @RequestHeader(value = "X-User-Email", required = false) String email,
                                               Authentication authentication) {
        String serviceName = (authentication != null) ? authentication.getName() : "unknown";
        String caller = email != null ? email : serviceName;

        logger.info("Caller: {}", caller);

        return Map.of(
                "message", "Hello, " + caller + "! This is a protected API."
        );
    }
}
