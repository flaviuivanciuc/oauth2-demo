package dev.oauth2.client_demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    public HomeController(@Autowired OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser user) {
        if (user != null) {
            model.addAttribute("name", user.getFullName());
            model.addAttribute("email", user.getEmail());
        }
        return "home";
    }

    @GetMapping("/secured")
    public String secured(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("name", user.getFullName());
        return "secured";
    }

    @GetMapping("/call-api")
    public String callApi(Model model,
                          @AuthenticationPrincipal OidcUser oidcUser,
                          OAuth2AuthenticationToken authToken) {

        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authToken.getAuthorizedClientRegistrationId(), authToken.getName());

        String accessToken = client.getAccessToken().getTokenValue();
        String userEmail = oidcUser.getEmail();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Email", userEmail);
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8081/api/private",
                HttpMethod.GET,
                entity,
                String.class);

        model.addAttribute("apiResponse", response.getBody());
        model.addAttribute("name", oidcUser.getFullName());

        return "secured";
    }
}
