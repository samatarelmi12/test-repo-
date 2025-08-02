package org.ac.cst8277.elmi.samatar.messagingservice.controller;

import org.ac.cst8277.elmi.samatar.messagingservice.model.Message;
import org.ac.cst8277.elmi.samatar.messagingservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * REST controller exposing endpoints for managing messages.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Returns all messages in the system.
     */
    @GetMapping
    public Mono<ResponseEntity<List<Message>>> getAllMessages() {
        return Mono.fromSupplier(() -> ResponseEntity.ok(messageService.findAll()));
    }

    /**
     * Returns all messages published by a specific producer.
     */
    @GetMapping("/producer/{producerId}")
    public Mono<ResponseEntity<List<Message>>> getMessagesByProducer(@PathVariable Long producerId) {
        return Mono.fromSupplier(() -> ResponseEntity.ok(messageService.findByProducerId(producerId)));
    }

    /**
     * Returns all messages visible to a specific subscriber (i.e. messages
     * produced by all producers the subscriber is subscribed to).
     */
    @GetMapping("/subscriber/{subscriberId}")
    public Mono<ResponseEntity<List<Message>>> getMessagesForSubscriber(@PathVariable Long subscriberId) {
        return Mono.fromSupplier(() -> ResponseEntity.ok(messageService.findForSubscriberId(subscriberId)));
    }

    /**
     * Creates a new message.  The request body should include the content and
     * the producerId.  The createdAt timestamp is filled in by the service.
     */
    @PostMapping
    public Mono<ResponseEntity<Message>> createMessage(@RequestBody Message message) {
        return Mono.fromSupplier(() -> ResponseEntity.status(201).body(messageService.create(message)));
    }
}