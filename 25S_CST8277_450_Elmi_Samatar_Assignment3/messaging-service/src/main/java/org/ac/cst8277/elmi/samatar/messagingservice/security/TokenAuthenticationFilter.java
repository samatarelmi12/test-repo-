package org.ac.cst8277.elmi.samatar.messagingservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * A reactive filter that intercepts all incoming requests and validates the
 * bearer token contained in the Authorization header by delegating to the
 * user-management service.  If the token is valid the request proceeds
 * downstream; otherwise an HTTP 401 Unauthorized response is returned.
 */
@Component
public class TokenAuthenticationFilter implements WebFilter {

    private final WebClient userManagementWebClient;

    @Autowired
    public TokenAuthenticationFilter(WebClient userManagementWebClient) {
        this.userManagementWebClient = userManagementWebClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        // Permit certain paths without authentication (e.g. actuator or root)
        if (path.startsWith("/actuator") || path.equals("/error")) {
            return chain.filter(exchange);
        }
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String token = authHeader.substring(7);
        // Call user-management-service validate endpoint
        return userManagementWebClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("token", token).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status != HttpStatus.OK, clientResponse -> {
                    // If validation fails, propagate unauthorized
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                })
                .bodyToMono(Long.class)
                .flatMap(userId -> {
                    // Store the user ID as a request attribute for use downstream
                    exchange.getAttributes().put("authenticatedUserId", userId);
                    return chain.filter(exchange);
                });
    }
}