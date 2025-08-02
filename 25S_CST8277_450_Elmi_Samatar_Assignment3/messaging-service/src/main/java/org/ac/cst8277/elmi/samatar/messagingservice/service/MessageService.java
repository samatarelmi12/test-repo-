package org.ac.cst8277.elmi.samatar.messagingservice.service;

import org.ac.cst8277.elmi.samatar.messagingservice.model.Message;
import org.ac.cst8277.elmi.samatar.messagingservice.model.Subscription;
import org.ac.cst8277.elmi.samatar.messagingservice.repository.MessageRepository;
import org.ac.cst8277.elmi.samatar.messagingservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service encapsulating business logic for messages.  It delegates
 * persistence to the corresponding repository and resolves messages for
 * subscribers by looking up their subscriptions first.
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, SubscriptionRepository subscriptionRepository) {
        this.messageRepository = messageRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message create(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> findByProducerId(Long producerId) {
        return messageRepository.findAllByProducerId(producerId);
    }

    public List<Message> findForSubscriberId(Long subscriberId) {
        // Find all subscriptions for the subscriber
        List<Subscription> subs = subscriptionRepository.findAllBySubscriberId(subscriberId);
        List<Message> result = new ArrayList<>();
        for (Subscription sub : subs) {
            result.addAll(messageRepository.findAllByProducerId(sub.getProducerId()));
        }
        return result;
    }
}