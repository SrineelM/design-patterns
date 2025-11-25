package com.example.patterns.structural.facade;

import com.example.patterns.common.DesignPattern;

/**
 * Order processing facade (Facade).
 * 
 * <p>This is the Facade in the Facade pattern. It provides a simplified
 * interface to the complex subsystems of order processing (inventory, payment,
 * shipping, and notifications).
 * 
 * <p><b>Benefits:</b>
 * <ul>
 *   <li>Simplifies client code - one method call instead of many</li>
 *   <li>Hides subsystem complexity</li>
 *   <li>Reduces dependencies between client and subsystems</li>
 *   <li>Provides a single point for orchestrating complex workflows</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Facade",
    category = "Structural",
    description = "Simplifies order processing by coordinating inventory, payment, and shipping subsystems"
)
public class OrderFacade {
    
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final NotificationService notificationService;
    
    /**
     * Creates an order facade with default subsystem implementations.
     */
    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
    }
    
    /**
     * Creates an order facade with custom subsystem implementations.
     * 
     * <p>This constructor allows dependency injection for testing or
     * using alternative implementations.
     *
     * @param inventoryService the inventory service
     * @param paymentService the payment service
     * @param shippingService the shipping service
     * @param notificationService the notification service
     */
    public OrderFacade(InventoryService inventoryService,
                      PaymentService paymentService,
                      ShippingService shippingService,
                      NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
        this.notificationService = notificationService;
    }
    
    /**
     * Places an order with a single simplified method call.
     * 
     * <p>This method orchestrates all the complex subsystem interactions:
     * <ol>
     *   <li>Validates payment information</li>
     *   <li>Checks inventory availability</li>
     *   <li>Validates shipping address</li>
     *   <li>Reserves inventory</li>
     *   <li>Authorizes payment</li>
     *   <li>Captures payment</li>
     *   <li>Updates inventory</li>
     *   <li>Schedules shipment</li>
     *   <li>Sends notifications</li>
     * </ol>
     * 
     * <p>If any step fails, the method rolls back previous operations.
     *
     * @param customerId the customer identifier
     * @param customerEmail the customer email
     * @param productId the product identifier
     * @param quantity the order quantity
     * @param cardNumber the credit card number
     * @param cvv the card CVV
     * @param expiryDate the card expiry date
     * @param shippingAddress the shipping address
     * @param amount the order total amount
     * @return order result with success status and details
     */
    public OrderResult placeOrder(String customerId, String customerEmail,
                                 String productId, int quantity,
                                 String cardNumber, String cvv, String expiryDate,
                                 String shippingAddress, double amount) {
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PROCESSING ORDER");
        System.out.println("=".repeat(60));
        
        String orderId = "ORD-" + System.currentTimeMillis();
        String reservationId = null;
        String authCode = null;
        
        try {
            // Step 1: Validate payment information
            if (!paymentService.validatePaymentInfo(cardNumber, cvv, expiryDate)) {
                return OrderResult.failure("Invalid payment information");
            }
            
            // Step 2: Check inventory availability
            if (!inventoryService.checkAvailability(productId, quantity)) {
                return OrderResult.failure("Product not available");
            }
            
            // Step 3: Validate shipping address
            if (!shippingService.validateAddress(shippingAddress)) {
                return OrderResult.failure("Invalid shipping address");
            }
            
            // Step 4: Reserve inventory
            reservationId = inventoryService.reserveInventory(productId, quantity);
            if (reservationId == null) {
                return OrderResult.failure("Failed to reserve inventory");
            }
            
            // Step 5: Authorize payment
            authCode = paymentService.authorizePayment(amount);
            if (authCode == null) {
                inventoryService.releaseReservation(reservationId);
                return OrderResult.failure("Payment authorization failed");
            }
            
            // Step 6: Capture payment
            String transactionId = paymentService.capturePayment(authCode);
            if (transactionId == null) {
                paymentService.voidPayment(authCode);
                inventoryService.releaseReservation(reservationId);
                return OrderResult.failure("Payment capture failed");
            }
            
            // Step 7: Update inventory
            inventoryService.updateInventory(reservationId);
            
            // Step 8: Schedule shipment
            String trackingNumber = shippingService.scheduleShipment(orderId, shippingAddress);
            
            // Step 9: Send notifications
            notificationService.sendOrderConfirmation(customerEmail, orderId);
            notificationService.sendPaymentReceipt(customerEmail, transactionId, amount);
            shippingService.sendShippingNotification(customerEmail, trackingNumber);
            
            System.out.println("=".repeat(60));
            System.out.println("ORDER COMPLETED SUCCESSFULLY");
            System.out.println("=".repeat(60));
            
            return OrderResult.success(orderId);
            
        } catch (Exception e) {
            // Rollback on any error
            System.err.println("[ERROR] Order processing failed: " + e.getMessage());
            
            if (authCode != null) {
                paymentService.voidPayment(authCode);
            }
            if (reservationId != null) {
                inventoryService.releaseReservation(reservationId);
            }
            
            notificationService.sendCancellationNotice(
                customerEmail, 
                orderId, 
                "Order processing error: " + e.getMessage()
            );
            
            return OrderResult.failure("Order processing error: " + e.getMessage());
        }
    }
    
    /**
     * Gets quick order status.
     * 
     * <p>This is another simplified facade method that would coordinate
     * checking order status across multiple subsystems.
     *
     * @param orderId the order identifier
     * @return order status information
     */
    public String getOrderStatus(String orderId) {
        // In a real system, this would query multiple subsystems
        return String.format("""
            Order Status for %s:
            - Inventory: Fulfilled
            - Payment: Completed
            - Shipping: In Transit
            """, orderId);
    }
}
