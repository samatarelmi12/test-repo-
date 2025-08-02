package org.ac.cst8277.elmi.samatar.messagingservice.config;

import org.ac.cst8277.elmi.samatar.messagingservice.model.Message;
import org.ac.cst8277.elmi.samatar.messagingservice.model.Subscription;
import org.ac.cst8277.elmi.samatar.messagingservice.repository.MessageRepository;
import org.ac.cst8277.elmi.samatar.messagingservice.repository.SubscriptionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * Bootstrap sample data on startup.  This is purely for testing and should
 * be removed or modified in production deployments.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(MessageRepository messageRepository, SubscriptionRepository subscriptionRepository) {
        return args -> {
            if (messageRepository.count() == 0) {
                // create a couple of sample messages
                Message m1 = new Message();
                m1.setContent("Hello from Alice");
                m1.setProducerId(1L);
                m1.setCreatedAt(LocalDateTime.now());
                messageRepository.save(m1);

                Message m2 = new Message();
                m2.setContent("Welcome from Bob");
                m2.setProducerId(2L);
                m2.setCreatedAt(LocalDateTime.now());
                messageRepository.save(m2);
            }
            if (subscriptionRepository.count() == 0) {
                // subscriber 2 subscribes to producer 1
                Subscription s = new Subscription();
                s.setProducerId(1L);
                s.setSubscriberId(2L);
                s.setCreatedAt(LocalDateTime.now());
                subscriptionRepository.save(s);
            }
        };
    }
}