package org.ac.cst8277.elmi.samatar.usermanagementservice.dto;

/**
 * DTO representing a login request body containing the username and password.
 */
public class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}