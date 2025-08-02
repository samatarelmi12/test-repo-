package org.ac.cst8277.elmi.samatar.messagingservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity representing a message published by a producer.  The consumer of the
 * API should supply the producer ID in the request.  This ID is stored
 * directly rather than creating a foreign key relationship to avoid tight
 * coupling between services.
 */
@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Long producerId;
}