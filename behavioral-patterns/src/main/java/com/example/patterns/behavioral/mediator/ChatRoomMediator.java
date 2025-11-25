package com.example.patterns.behavioral.mediator;

import com.example.patterns.common.DesignPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Chat room mediator that coordinates communication between users.
 * 
 * <p>This is the Mediator in the pattern. It:</p>
 * <ul>
 *   <li>Manages user registration</li>
 *   <li>Distributes messages to appropriate recipients</li>
 *   <li>Enforces chat rules and policies</li>
 *   <li>Prevents users from directly referencing each other</li>
 * </ul>
 * 
 * <h3>Without Mediator:</h3>
 * <pre>
 * User1 → User2
 *   ↓       ↑
 * User3 <- User4
 * (Every user needs references to every other user - messy!)
 * </pre>
 * 
 * <h3>With Mediator:</h3>
 * <pre>
 *     [ChatRoom Mediator]
 *    /    |    |    \
 * User1 User2 User3 User4
 * (Users only know about the mediator - clean!)
 * </pre>
 * 
 * <p>This implementation uses Spring's {@link ApplicationEventPublisher} as
 * the underlying message distribution mechanism, demonstrating how design
 * patterns integrate with modern frameworks.</p>
 * 
 * @since 1.0
 */
@Service
@DesignPattern(
    name = "Mediator",
    category = "Behavioral",
    description = "Coordinates communication between objects through a central hub"
)
public class ChatRoomMediator {
    
    private static final Logger log = LoggerFactory.getLogger(ChatRoomMediator.class);
    
    /**
     * Spring's event publisher acts as our message distribution mechanism.
     * This is the "mediator" that ensures users don't need to know about each other.
     */
    private final ApplicationEventPublisher eventPublisher;
    
    /**
     * Registered users. Using ConcurrentHashMap for thread safety.
     */
    private final Set<String> registeredUsers = ConcurrentHashMap.newKeySet();
    
    public ChatRoomMediator(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    /**
     * Registers a user in the chat room.
     * 
     * <p>The mediator maintains the list of participants. Users don't need to
     * know about each other - they only register with the mediator.</p>
     * 
     * @param username The username to register
     * @return true if registration was successful, false if username already exists
     */
    public boolean registerUser(String username) {
        if (registeredUsers.add(username)) {
            log.info("User '{}' joined the chat room", username);
            
            // Announce to everyone
            var announcement = ChatMessage.publicMessage(
                "System",
                username + " has joined the chat"
            );
            eventPublisher.publishEvent(announcement);
            
            return true;
        }
        
        log.warn("Username '{}' is already taken", username);
        return false;
    }
    
    /**
     * Unregisters a user from the chat room.
     * 
     * @param username The username to unregister
     */
    public void unregisterUser(String username) {
        if (registeredUsers.remove(username)) {
            log.info("User '{}' left the chat room", username);
            
            var announcement = ChatMessage.publicMessage(
                "System",
                username + " has left the chat"
            );
            eventPublisher.publishEvent(announcement);
        }
    }
    
    /**
     * Sends a public message to all users in the chat room.
     * 
     * <p>The mediator handles message distribution - the sender doesn't need
     * to know who the recipients are or how to send to them.</p>
     * 
     * @param sender The username of the sender
     * @param content The message content
     */
    public void sendPublicMessage(String sender, String content) {
        if (!registeredUsers.contains(sender)) {
            log.warn("User '{}' is not registered, cannot send message", sender);
            return;
        }
        
        log.info("Public message from '{}': {}", sender, content);
        
        var message = ChatMessage.publicMessage(sender, content);
        eventPublisher.publishEvent(message);
    }
    
    /**
     * Sends a private message to a specific user.
     * 
     * <p>The mediator validates that both users exist and delivers the message
     * only to the intended recipient.</p>
     * 
     * @param sender The username of the sender
     * @param recipient The username of the recipient
     * @param content The message content
     */
    public void sendPrivateMessage(String sender, String recipient, String content) {
        if (!registeredUsers.contains(sender)) {
            log.warn("Sender '{}' is not registered", sender);
            return;
        }
        
        if (!registeredUsers.contains(recipient)) {
            log.warn("Recipient '{}' is not registered", recipient);
            return;
        }
        
        log.info("Private message from '{}' to '{}': {}", sender, recipient, content);
        
        var message = ChatMessage.privateMessage(sender, recipient, content);
        eventPublisher.publishEvent(message);
    }
    
    /**
     * Gets the number of users currently in the chat room.
     * 
     * @return The user count
     */
    public int getUserCount() {
        return registeredUsers.size();
    }
    
    /**
     * Checks if a username is registered.
     * 
     * @param username The username to check
     * @return true if the user is registered
     */
    public boolean isUserRegistered(String username) {
        return registeredUsers.contains(username);
    }
}
