package com.example.patterns.structural.bridge;

/**
 * Message implementation interface (Implementor).
 * 
 * <p>This is the implementation interface in the Bridge pattern.
 * It defines the low-level operations for sending messages through
 * different channels.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface MessageSender {
    
    /**
     * Sends a message through the specific channel.
     *
     * @param recipient the recipient identifier (email, phone number, etc.)
     * @param message the message content
     * @return true if message was sent successfully, false otherwise
     */
    boolean send(String recipient, String message);
    
    /**
     * Gets the type of this message sender.
     *
     * @return the sender type description
     */
    String getSenderType();
}
