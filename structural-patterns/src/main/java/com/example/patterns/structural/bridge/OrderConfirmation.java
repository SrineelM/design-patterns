package com.example.patterns.structural.bridge;

/**
 * Order confirmation notification (RefinedAbstraction).
 * 
 * <p>This refined abstraction sends order confirmation messages
 * with transactional formatting.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class OrderConfirmation extends Notification {
    
    private final String orderId;
    private final double amount;
    private final String deliveryDate;
    
    /**
     * Creates an order confirmation notification.
     *
     * @param sender the message sender to use
     * @param orderId the order identifier
     * @param amount the order total amount
     * @param deliveryDate the estimated delivery date
     */
    public OrderConfirmation(MessageSender sender, String orderId, 
                            double amount, String deliveryDate) {
        super(sender);
        this.orderId = orderId;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
    }
    
    @Override
    protected String formatMessage() {
        return String.format("""
            [v] Order Confirmed
            
            Order #: %s
            Total: $%.2f
            Estimated Delivery: %s
            
            Thank you for your purchase!
            Track your order at: https://example.com/track/%s
            """,
            orderId,
            amount,
            deliveryDate,
            orderId
        );
    }
}
