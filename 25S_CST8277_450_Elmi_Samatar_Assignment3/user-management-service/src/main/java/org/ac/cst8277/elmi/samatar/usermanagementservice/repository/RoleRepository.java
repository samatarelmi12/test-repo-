package org.ac.cst8277.elmi.samatar.usermanagementservice.repository;

import org.ac.cst8277.elmi.samatar.usermanagementservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link Role} entities.  Provides basic CRUD operations.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}