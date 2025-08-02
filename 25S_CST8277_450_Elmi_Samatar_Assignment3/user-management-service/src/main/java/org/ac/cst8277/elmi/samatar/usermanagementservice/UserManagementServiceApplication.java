package org.ac.cst8277.elmi.samatar.usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the User Management microservice.
 *
 * <p>This service is responsible for user persistence and authentication.  It exposes
 * a REST API for user login and token validation.  Spring Boot WebFlux is used
 * to provide non-blocking HTTP endpoints running on Netty.</p>
 */
@SpringBootApplication
public class UserManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementServiceApplication.class, args);
    }
}