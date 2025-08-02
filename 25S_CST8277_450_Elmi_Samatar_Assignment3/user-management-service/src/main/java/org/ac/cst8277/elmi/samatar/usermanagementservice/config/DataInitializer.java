package org.ac.cst8277.elmi.samatar.usermanagementservice.config;

import org.ac.cst8277.elmi.samatar.usermanagementservice.model.Role;
import org.ac.cst8277.elmi.samatar.usermanagementservice.model.User;
import org.ac.cst8277.elmi.samatar.usermanagementservice.repository.RoleRepository;
import org.ac.cst8277.elmi.samatar.usermanagementservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that bootstraps some sample data on startup.  This makes
 * it easier to test the service without needing to manually insert users and
 * roles into the database.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role producer = new Role();
                producer.setName("PRODUCER");
                roleRepository.save(producer);

                Role subscriber = new Role();
                subscriber.setName("SUBSCRIBER");
                roleRepository.save(subscriber);

                // Create a few users
                User user1 = new User();
                user1.setUsername("alice");
                user1.setPassword("password");
                user1.getRoles().add(producer);
                user1.getRoles().add(subscriber);
                userRepository.save(user1);

                User user2 = new User();
                user2.setUsername("bob");
                user2.setPassword("password");
                user2.getRoles().add(subscriber);
                userRepository.save(user2);
            }
        };
    }
}