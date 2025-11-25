package com.example.patterns.structural.bridge;

/**
 * Email implementation of MessageSender (ConcreteImplementor).
 * 
 * <p>This is a concrete implementation that sends messages via email.
 * In a real system, this would integrate with an email service like
 * SendGrid, AWS SES, or SMTP servers.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class EmailMessageSender implements MessageSender {
    
    private final String smtpServer;
    private final int port;
    
    /**
     * Creates an email sender with default configuration.
     */
    public EmailMessageSender() {
        this("smtp.example.com", 587);
    }
    
    /**
     * Creates an email sender with custom SMTP configuration.
     *
     * @param smtpServer the SMTP server address
     * @param port the SMTP port number
     */
    public EmailMessageSender(String smtpServer, int port) {
        this.smtpServer = smtpServer;
        this.port = port;
    }
    
    @Override
    public boolean send(String recipient, String message) {
        // Simulate email sending
        System.out.printf("[EMAIL] Connecting to %s:%d%n", smtpServer, port);
        System.out.printf("[EMAIL] To: %s%n", recipient);
        System.out.printf("[EMAIL] Content:%n%s%n", message);
        System.out.println("[EMAIL] Message sent successfully via email!");
        return true;
    }
    
    @Override
    public String getSenderType() {
        return "Email (SMTP)";
    }
}
