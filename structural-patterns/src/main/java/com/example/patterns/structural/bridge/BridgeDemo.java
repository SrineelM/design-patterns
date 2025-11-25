package com.example.patterns.structural.bridge;

/**
 * Demonstration of the Bridge pattern.
 * 
 * <p>This demo shows how the Bridge pattern separates the abstraction (Notification types)
 * from the implementation (MessageSender types), allowing them to vary independently.
 * 
 * <p><b>Key Takeaways:</b>
 * <ul>
 *   <li>Abstractions and implementations can be extended independently</li>
 *   <li>You can switch implementations at runtime</li>
 *   <li>Avoids a proliferation of classes (no need for EmailSystemAlert, SmsSystemAlert, etc.)</li>
 *   <li>Follows Single Responsibility and Open/Closed principles</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class BridgeDemo {
    
    /**
     * Runs the bridge pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Bridge Pattern Demonstration ===\n");
        
        // Create different message sender implementations
        MessageSender emailSender = new EmailMessageSender();
        MessageSender smsSender = new SmsMessageSender("API-KEY-12345678-ABCD");
        MessageSender pushSender = new PushNotificationSender("Android");
        
        System.out.println("Scenario 1: System Alert via Different Channels");
        System.out.println("=".repeat(50));
        
        // Same notification type with different implementations
        Notification emailAlert = new SystemAlert(
            emailSender, 
            "CRITICAL", 
            "Payment Service",
            "Database connection pool exhausted"
        );
        emailAlert.notify("admin@example.com");
        
        Notification smsAlert = new SystemAlert(
            smsSender,
            "CRITICAL",
            "Payment Service", 
            "Database connection pool exhausted"
        );
        smsAlert.notify("+15551234567");
        
        System.out.println("\nScenario 2: Different Notifications via Email");
        System.out.println("=".repeat(50));
        
        // Different notification types with same implementation
        Notification promoEmail = new PromotionalNotification(
            emailSender,
            "Premium Subscription",
            25,
            "SAVE25"
        );
        promoEmail.notify("customer@example.com");
        
        Notification orderEmail = new OrderConfirmation(
            emailSender,
            "ORD-2024-001234",
            149.99,
            "Dec 5, 2024"
        );
        orderEmail.notify("customer@example.com");
        
        System.out.println("\nScenario 3: Order Confirmation via Multiple Channels");
        System.out.println("=".repeat(50));
        
        // Same notification via multiple implementations
        Notification orderSms = new OrderConfirmation(
            smsSender,
            "ORD-2024-005678",
            299.99,
            "Dec 3, 2024"
        );
        orderSms.notify("+15559876543");
        
        Notification orderPush = new OrderConfirmation(
            pushSender,
            "ORD-2024-005678",
            299.99,
            "Dec 3, 2024"
        );
        orderPush.notify("fcm_device_token_abc123xyz789");
        
        System.out.println("\n=== Key Benefits ===");
        System.out.println("✓ 3 notification types × 3 sender types = 9 combinations");
        System.out.println("✓ Only 6 classes needed (3 + 3), not 9 separate classes");
        System.out.println("✓ Can add new notification types without changing senders");
        System.out.println("✓ Can add new sender types without changing notifications");
        System.out.println("✓ Can switch sender implementation at runtime");
    }
}
