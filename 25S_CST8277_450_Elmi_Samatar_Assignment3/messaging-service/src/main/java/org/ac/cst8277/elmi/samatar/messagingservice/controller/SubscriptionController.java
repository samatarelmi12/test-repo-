package org.ac.cst8277.elmi.samatar.messagingservice.controller;

import org.ac.cst8277.elmi.samatar.messagingservice.model.Subscription;
import org.ac.cst8277.elmi.samatar.messagingservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * REST controller exposing endpoints for managing subscriptions.
 */
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Returns all subscriptions in the system.
     */
    @GetMapping
    public Mono<ResponseEntity<List<Subscription>>> getAll() {
        return Mono.fromSupplier(() -> ResponseEntity.ok(subscriptionService.findAll()));
    }

    /**
     * Creates a new subscription.  The request body should include the
     * producerId and subscriberId.  The createdAt timestamp is set by the
     * service.
     */
    @PostMapping
    public Mono<ResponseEntity<Subscription>> create(@RequestBody Subscription sub) {
        return Mono.fromSupplier(() -> ResponseEntity.ok(subscriptionService.create(sub)));
    }
}