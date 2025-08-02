package org.ac.cst8277.elmi.samatar.usermanagementservice.service;

import org.ac.cst8277.elmi.samatar.usermanagementservice.model.User;
import org.ac.cst8277.elmi.samatar.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service providing user authentication and token issuance.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public AuthService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    /**
     * Authenticates the user with the given username and password.  If the
     * credentials are valid, a new token is issued and returned.  Otherwise
     * returns null.
     *
     * @param username the username
     * @param password the password
     * @return a token if authentication succeeds, otherwise null
     */
    public String authenticate(String username, String password) {
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isPresent()) {
            User user = opt.get();
            if (user.getPassword().equals(password)) {
                return tokenService.generateToken(user.getId());
            }
        }
        return null;
    }

    /**
     * Validates the given token and returns the associated user ID, or null if
     * invalid.
     *
     * @param token the token to validate
     * @return the user ID if valid, otherwise null
     */
    public Long validateToken(String token) {
        return tokenService.validateToken(token);
    }
}