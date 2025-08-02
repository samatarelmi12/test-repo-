package org.ac.cst8277.elmi.samatar.messagingservice.repository;

import org.ac.cst8277.elmi.samatar.messagingservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for {@link Subscription} entities.  Provides methods to find
 * subscriptions by subscriber or producer.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllBySubscriberId(Long subscriberId);

    List<Subscription> findAllByProducerId(Long producerId);
}