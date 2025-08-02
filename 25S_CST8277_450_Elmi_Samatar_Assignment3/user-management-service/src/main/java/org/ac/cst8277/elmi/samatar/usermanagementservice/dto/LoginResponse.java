package org.ac.cst8277.elmi.samatar.usermanagementservice.dto;

/**
 * DTO representing a successful login response containing the issued token.
 */
public class LoginResponse {
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}