package com.example.patterns.structural.facade;

/**
 * Notification subsystem.
 * 
 * <p>This subsystem handles customer notifications.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class NotificationService {
    
    /**
     * Sends order confirmation email.
     *
     * @param email the customer email
     * @param orderId the order identifier
     */
    public void sendOrderConfirmation(String email, String orderId) {
        System.out.printf("[NOTIFICATION] Sending order confirmation to: %s%n", email);
        System.out.printf("[NOTIFICATION] Order ID: %s%n", orderId);
        System.out.println("[NOTIFICATION] Confirmation email sent");
    }
    
    /**
     * Sends payment receipt email.
     *
     * @param email the customer email
     * @param transactionId the transaction identifier
     * @param amount the payment amount
     */
    public void sendPaymentReceipt(String email, String transactionId, double amount) {
        System.out.printf("[NOTIFICATION] Sending payment receipt to: %s%n", email);
        System.out.printf("[NOTIFICATION] Transaction: %s, Amount: $%.2f%n", 
            transactionId, amount);
        System.out.println("[NOTIFICATION] Receipt email sent");
    }
    
    /**
     * Sends order cancellation notification.
     *
     * @param email the customer email
     * @param orderId the order identifier
     * @param reason the cancellation reason
     */
    public void sendCancellationNotice(String email, String orderId, String reason) {
        System.out.printf("[NOTIFICATION] Sending cancellation notice to: %s%n", email);
        System.out.printf("[NOTIFICATION] Order ID: %s, Reason: %s%n", orderId, reason);
        System.out.println("[NOTIFICATION] Cancellation notice sent");
    }
}
