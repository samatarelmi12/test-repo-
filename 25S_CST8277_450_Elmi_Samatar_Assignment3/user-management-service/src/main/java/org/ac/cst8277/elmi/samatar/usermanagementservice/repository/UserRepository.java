package org.ac.cst8277.elmi.samatar.usermanagementservice.repository;

import org.ac.cst8277.elmi.samatar.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA repository for {@link User} entities.  Provides basic CRUD operations and
 * a method to find a user by username.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}