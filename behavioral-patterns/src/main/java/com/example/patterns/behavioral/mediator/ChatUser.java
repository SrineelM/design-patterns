package com.example.patterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the chat room.
 * 
 * <p>This is a Colleague in the Mediator pattern. Key characteristics:</p>
 * <ul>
 *   <li>Knows about the mediator, but not about other users</li>
 *   <li>Sends messages through the mediator</li>
 *   <li>Receives messages via Spring's @EventListener</li>
 *   <li>Doesn't need references to other ChatUser instances</li>
 * </ul>
 * 
 * <h3>Decoupling Benefit:</h3>
 * <p>If we add a new user, existing users don't need any code changes.
 * If we change how messages are delivered, users don't need to change.</p>
 * 
 * @since 1.0
 */
@Component
public class ChatUser {
    
    private static final Logger log = LoggerFactory.getLogger(ChatUser.class);
    
    private String username;
    private final ChatRoomMediator mediator;
    private final List<ChatMessage> messageHistory = new ArrayList<>();
    
    /**
     * Creates a chat user.
     * 
     * <p>Spring injects the mediator automatically. The user doesn't need to
     * know about other users - only the mediator.</p>
     * 
     * @param mediator The chat room mediator
     */
    public ChatUser(ChatRoomMediator mediator) {
        this.mediator = mediator;
    }
    
    /**
     * Joins the chat room with a username.
     * 
     * @param username The desired username
     * @return true if join was successful
     */
    public boolean join(String username) {
        if (mediator.registerUser(username)) {
            this.username = username;
            log.info("I ('{}') have joined the chat room", username);
            return true;
        }
        return false;
    }
    
    /**
     * Leaves the chat room.
     */
    public void leave() {
        if (username != null) {
            mediator.unregisterUser(username);
            log.info("I ('{}') have left the chat room", username);
            username = null;
        }
    }
    
    /**
     * Sends a public message to all users.
     * 
     * <p>Note that the user doesn't send directly to other users.
     * Instead, it goes through the mediator.</p>
     * 
     * @param message The message to send
     */
    public void sendPublicMessage(String message) {
        if (username == null) {
            log.warn("Cannot send message - not joined to chat room");
            return;
        }
        
        mediator.sendPublicMessage(username, message);
    }
    
    /**
     * Sends a private message to a specific user.
     * 
     * @param recipient The recipient's username
     * @param message The message to send
     */
    public void sendPrivateMessage(String recipient, String message) {
        if (username == null) {
            log.warn("Cannot send message - not joined to chat room");
            return;
        }
        
        mediator.sendPrivateMessage(username, recipient, message);
    }
    
    /**
     * Receives messages via Spring's event system.
     * 
     * <p>This method is called automatically by Spring when a ChatMessage
     * event is published. This demonstrates how Spring's event mechanism
     * acts as a mediator.</p>
     * 
     * <p>The @EventListener annotation makes this user a listener without
     * requiring explicit registration or dependencies on other users.</p>
     * 
     * @param message The received message
     */
    @EventListener
    public void onMessageReceived(ChatMessage message) {
        // Don't show our own messages twice
        if (message.sender().equals(username)) {
            return;
        }
        
        // Handle private messages
        if (message.isPrivate()) {
            if (message.recipient().equals(username)) {
                log.info("[{}] Received PRIVATE from {}: {}", 
                         username, message.sender(), message.content());
                messageHistory.add(message);
            }
            // Ignore private messages not meant for us
            return;
        }
        
        // Handle public messages
        log.info("[{}] Received PUBLIC from {}: {}", 
                 username, message.sender(), message.content());
        messageHistory.add(message);
    }
    
    /**
     * Gets the message history for this user.
     * 
     * @return List of received messages
     */
    public List<ChatMessage> getMessageHistory() {
        return new ArrayList<>(messageHistory);
    }
    
    /**
     * Gets this user's username.
     * 
     * @return The username, or null if not joined
     */
    public String getUsername() {
        return username;
    }
}
