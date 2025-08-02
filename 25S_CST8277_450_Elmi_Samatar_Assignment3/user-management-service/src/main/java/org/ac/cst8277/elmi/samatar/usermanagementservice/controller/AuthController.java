package org.ac.cst8277.elmi.samatar.usermanagementservice.controller;

import org.ac.cst8277.elmi.samatar.usermanagementservice.dto.LoginRequest;
import org.ac.cst8277.elmi.samatar.usermanagementservice.dto.LoginResponse;
import org.ac.cst8277.elmi.samatar.usermanagementservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller exposing authentication endpoints.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint for user login.  Accepts a username and password and returns
     * a token if the credentials are valid.
     *
     * @param loginRequest the login request body
     * @return {@code Mono<ResponseEntity<LoginResponse>>}
     */
    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        return Mono.fromSupplier(() -> {
            String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            if (token != null) {
                return ResponseEntity.ok(new LoginResponse(token));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        });
    }

    /**
     * Endpoint to validate a token.  Returns the user ID associated with the
     * token if valid.
     *
     * @param token the token to validate
     * @return ResponseEntity with userId or 401
     */
    @GetMapping("/validate")
    public Mono<ResponseEntity<Long>> validate(@RequestParam String token) {
        return Mono.fromSupplier(() -> {
            Long userId = authService.validateToken(token);
            if (userId != null) {
                return ResponseEntity.ok(userId);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        });
    }
}