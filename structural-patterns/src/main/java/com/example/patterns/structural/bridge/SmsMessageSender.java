package com.example.patterns.structural.bridge;

/**
 * SMS implementation of MessageSender (ConcreteImplementor).
 * 
 * <p>This is a concrete implementation that sends messages via SMS.
 * In a real system, this would integrate with services like Twilio,
 * AWS SNS, or other SMS gateways.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class SmsMessageSender implements MessageSender {
    
    private final String apiKey;
    
    /**
     * Creates an SMS sender with the given API key.
     *
     * @param apiKey the API key for the SMS service
     */
    public SmsMessageSender(String apiKey) {
        this.apiKey = apiKey;
    }
    
    @Override
    public boolean send(String recipient, String message) {
        // Validate phone number format
        if (!recipient.matches("\\+?[0-9]{10,15}")) {
            System.out.println("[SMS] Invalid phone number format: " + recipient);
            return false;
        }
        
        // Simulate SMS sending with 160 character limit
        String truncatedMessage = message.length() > 160 
            ? message.substring(0, 157) + "..." 
            : message;
        
        System.out.println("[SMS] Using API Key: " + maskApiKey(apiKey));
        System.out.printf("[SMS] To: %s%n", recipient);
        System.out.printf("[SMS] Message: %s%n", truncatedMessage);
        System.out.println("[SMS] Message sent successfully via SMS!");
        return true;
    }
    
    @Override
    public String getSenderType() {
        return "SMS Gateway";
    }
    
    private String maskApiKey(String key) {
        if (key == null || key.length() < 8) {
            return "****";
        }
        return key.substring(0, 4) + "****" + key.substring(key.length() - 4);
    }
}
