package org.ac.cst8277.elmi.samatar.messagingservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity representing a subscription relationship between a subscriber and a
 * producer.  Subscribers can subscribe to multiple producers.  The service
 * uses the IDs of the producer and subscriber rather than foreign keys.
 */
@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long producerId;

    @Column(nullable = false)
    private Long subscriberId;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}