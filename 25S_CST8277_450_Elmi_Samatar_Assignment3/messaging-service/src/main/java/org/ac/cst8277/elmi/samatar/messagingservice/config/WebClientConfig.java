package org.ac.cst8277.elmi.samatar.messagingservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class that provides a {@link WebClient} bean used to
 * communicate with the user-management service for token validation.
 */
@Configuration
public class WebClientConfig {

    @Value("${user-management.validate-url}")
    private String validateUrl;

    @Bean
    public WebClient userManagementWebClient() {
        return WebClient.builder()
                .baseUrl(validateUrl)
                .build();
    }
}