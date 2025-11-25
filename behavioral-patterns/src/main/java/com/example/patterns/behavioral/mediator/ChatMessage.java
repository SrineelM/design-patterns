package com.example.patterns.behavioral.mediator;

import java.time.LocalDateTime;

/**
 * Represents a chat message in the system.
 * 
 * <p>This is a Spring application event that will be published through the
 * mediator (event publisher) and received by listeners (chat users).</p>
 * 
 * <p>Using Spring's event system as a mediator demonstrates how the pattern
 * can be implemented with framework support, providing:</p>
 * <ul>
 *   <li>Automatic event distribution</li>
 *   <li>Decoupling of sender and receivers</li>
 *   <li>Easy addition of new listeners</li>
 * </ul>
 * 
 * @param sender The user who sent the message
 * @param content The message content
 * @param timestamp When the message was sent
 * @param isPrivate Whether this is a private message
 * @param recipient The recipient for private messages (null for public)
 * 
 * @since 1.0
 */
public record ChatMessage(
    String sender,
    String content,
    LocalDateTime timestamp,
    boolean isPrivate,
    String recipient
) {
    /**
     * Creates a public chat message.
     */
    public static ChatMessage publicMessage(String sender, String content) {
        return new ChatMessage(sender, content, LocalDateTime.now(), false, null);
    }
    
    /**
     * Creates a private chat message.
     */
    public static ChatMessage privateMessage(String sender, String recipient, String content) {
        return new ChatMessage(sender, content, LocalDateTime.now(), true, recipient);
    }
}
