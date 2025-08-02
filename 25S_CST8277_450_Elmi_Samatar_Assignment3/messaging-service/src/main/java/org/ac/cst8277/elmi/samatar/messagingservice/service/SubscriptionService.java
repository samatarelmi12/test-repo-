package org.ac.cst8277.elmi.samatar.messagingservice.service;

import org.ac.cst8277.elmi.samatar.messagingservice.model.Subscription;
import org.ac.cst8277.elmi.samatar.messagingservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service encapsulating business logic for subscriptions.  Handles creation and
 * retrieval of subscription entries.
 */
@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription create(Subscription sub) {
        sub.setCreatedAt(LocalDateTime.now());
        return subscriptionRepository.save(sub);
    }
}