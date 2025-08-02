package org.ac.cst8277.elmi.samatar.messagingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Messaging microservice.
 *
 * <p>This service exposes endpoints to publish and consume messages as well as
 * manage subscriptions between producers and subscribers.  Token-based
 * authentication is enforced by delegating validation to the user-management
 * service.</p>
 */
@SpringBootApplication
public class MessagingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingServiceApplication.class, args);
    }
}