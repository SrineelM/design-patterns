package com.example.patterns.structural.bridge;

import com.example.patterns.common.DesignPattern;

/**
 * Abstract notification class (Abstraction).
 * 
 * <p>This is the Abstraction in the Bridge pattern. It defines high-level
 * notification operations and holds a reference to a MessageSender implementation.
 * The abstraction delegates the actual sending to the implementation.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Bridge",
    category = "Structural",
    description = "Decouples notification abstraction from message sender implementation"
)
public abstract class Notification {
    
    protected final MessageSender sender;
    
    /**
     * Creates a notification with the given sender implementation.
     *
     * @param sender the message sender implementation to use
     * @throws IllegalArgumentException if sender is null
     */
    protected Notification(MessageSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException("MessageSender cannot be null");
        }
        this.sender = sender;
    }
    
    /**
     * Sends the notification to the recipient.
     * 
     * <p>This is a template method that calls formatMessage() to prepare
     * the message content, then delegates to the sender implementation.
     *
     * @param recipient the recipient identifier
     * @return true if notification was sent successfully
     */
    public boolean notify(String recipient) {
        String message = formatMessage();
        System.out.printf("%n[Notification] Sending via %s%n", sender.getSenderType());
        return sender.send(recipient, message);
    }
    
    /**
     * Formats the message content for this notification type.
     * 
     * <p>Subclasses override this to provide specific message formatting.
     *
     * @return the formatted message
     */
    protected abstract String formatMessage();
    
    /**
     * Gets the sender implementation being used.
     *
     * @return the message sender
     */
    public MessageSender getSender() {
        return sender;
    }
}
