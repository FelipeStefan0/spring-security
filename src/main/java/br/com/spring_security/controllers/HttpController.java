package br.com.spring_security.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HttpController {

    @GetMapping("/public")
    public String publicRoute() {
        return "<h2>Public Route, anyone can access!</h2>";
    }

    @GetMapping("/private")
    public String privateRoute() {
        return "<h2>Private Route, only authorized people!</h2>";
    }

    @GetMapping("/cookie")
    public String cookie(@AuthenticationPrincipal OidcUser principal) {
        return String.format(
                """
                <h2>Oauth2</h2>
                <h3>Principal: %s</h3>
                <h3>Email attribute: %s</h3>
                <h3>Authorities: %s</h3>
                <h3>JWT: %s</h3>
                """, principal, principal.getAttribute("email"), principal.getAuthorities(), principal.getIdToken().getTokenValue()
        );
    }

    @GetMapping("/jwt")
    public String jwt(@AuthenticationPrincipal Jwt jwt) {
        return String.format(
                """
                <h2>JWT</h2>
                <h3>Principal: %s</h3>
                <h3>Email attribute: %s</h3>
                <h3>JWT: %s</h3>
                """, jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue()
        );
    }
}
