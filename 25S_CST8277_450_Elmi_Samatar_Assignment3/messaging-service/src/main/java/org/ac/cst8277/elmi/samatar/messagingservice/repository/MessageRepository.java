package org.ac.cst8277.elmi.samatar.messagingservice.repository;

import org.ac.cst8277.elmi.samatar.messagingservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for {@link Message} entities.  Provides methods to retrieve
 * messages by producer ID.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByProducerId(Long producerId);
}