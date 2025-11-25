package com.example.patterns.structural.bridge;

/**
 * Push notification implementation of MessageSender (ConcreteImplementor).
 * 
 * <p>This is a concrete implementation that sends messages via push notifications.
 * In a real system, this would integrate with Firebase Cloud Messaging (FCM),
 * Apple Push Notification Service (APNS), or similar services.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class PushNotificationSender implements MessageSender {
    
    private final String platform;
    
    /**
     * Creates a push notification sender for the specified platform.
     *
     * @param platform the platform (iOS, Android, Web)
     */
    public PushNotificationSender(String platform) {
        this.platform = platform;
    }
    
    @Override
    public boolean send(String recipient, String message) {
        // Recipient is a device token for push notifications
        System.out.printf("[PUSH] Platform: %s%n", platform);
        System.out.printf("[PUSH] Device Token: %s...%n", 
            recipient.length() > 20 ? recipient.substring(0, 20) : recipient);
        System.out.printf("[PUSH] Notification: %s%n", message);
        System.out.println("[PUSH] Push notification sent successfully!");
        return true;
    }
    
    @Override
    public String getSenderType() {
        return "Push Notification (" + platform + ")";
    }
}
