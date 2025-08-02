package org.ac.cst8277.elmi.samatar.usermanagementservice.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service responsible for issuing and validating API tokens.
 *
 * <p>The current implementation stores tokens in-memory using a concurrent
 * map.  In a production environment this should be replaced with a more
 * durable store such as Redis or a database.  Tokens are represented as
 * random UUID strings.</p>
 */
@Service
public class TokenService {
    // Map of token string to userId
    private final Map<String, Long> tokenStore = new ConcurrentHashMap<>();

    /**
     * Generates a new token for the given user ID and stores it in the map.
     *
     * @param userId the ID of the user the token is issued for
     * @return the generated token string
     */
    public String generateToken(Long userId) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, userId);
        return token;
    }

    /**
     * Validates the given token and returns the associated user ID if found.
     *
     * @param token the token to validate
     * @return the user ID or null if the token is invalid
     */
    public Long validateToken(String token) {
        return tokenStore.get(token);
    }
}