package com.example.patterns.creational.factory;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * OrderProcessor demonstrates a real-world use case of the Factory Method pattern
 * in processing orders with different payment methods.
 * 
 * This class shows how client code should use the PaymentFactory to process payments
 * without knowing about concrete payment implementations.
 * 
 * <h3>Usage Example:</h3>
 * <pre>
 * OrderProcessor processor = new OrderProcessor();
 * Order order = new Order("ORDER-12345", new BigDecimal("99.99"));
 * 
 * // Process with credit card
 * OrderResult result = processor.processPayment(order, PaymentMethod.PAY_WITH_CREDIT_CARD);
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 */
public class OrderProcessor {
    
    /**
     * Processes a payment for an order using the specified payment method.
     * 
     * This method demonstrates the Factory Method pattern in action:
     * <ol>
     *     <li>Uses PaymentFactory to create payment (no knowledge of concrete classes)</li>
     *     <li>Validates the payment</li>
     *     <li>Processes the transaction</li>
     *     <li>Handles success/failure scenarios</li>
     * </ol>
     * 
     * @param order the order to process
     * @param paymentMethod the payment method to use
     * @return an OrderResult with processing status
     * @throws IllegalArgumentException if order or payment method is invalid
     * 
     * Example:
     * <pre>
     * OrderProcessor processor = new OrderProcessor();
     * Order order = new Order("ORD-001", new BigDecimal("150.00"));
     * OrderResult result = processor.processPayment(order, PaymentMethod.PAY_WITH_PAYPAL);
     * 
     * if (result.isSuccessful()) {
     *     System.out.println("Order confirmed with transaction: " + result.getTransactionId());
     * } else {
     *     System.out.println("Payment failed: " + result.getErrorMessage());
     * }
     * </pre>
     */
    public OrderResult processPayment(Order order, PaymentMethod paymentMethod) {
        Objects.requireNonNull(order, "Order cannot be null");
        Objects.requireNonNull(paymentMethod, "PaymentMethod cannot be null");
        
        try {
            // Factory Method in action - create payment without knowing concrete class
            Payment payment = PaymentFactory.createPayment(paymentMethod);
            
            // Validate payment method supports this amount
            if (!payment.validate(order.getAmount())) {
                String errorMsg = String.format(
                    "Payment method %s cannot process amount %s",
                    payment.getPaymentMethodName(), order.getAmount()
                );
                return OrderResult.failure(order.getId(), errorMsg);
            }
            
            // Process the payment
            PaymentResult paymentResult = payment.process(
                order.getAmount(),
                order.getId()
            );
            
            // Return order result based on payment result
            if (paymentResult.isSuccessful()) {
                return OrderResult.success(
                    order.getId(),
                    paymentResult.getTransactionId(),
                    paymentResult.getAmount()
                );
            } else {
                return OrderResult.failure(order.getId(), paymentResult.getErrorMessage());
            }
            
        } catch (IllegalArgumentException e) {
            String errorMsg = "Invalid payment method: " + e.getMessage();
            return OrderResult.failure(order.getId(), errorMsg);
        } catch (Exception e) {
            String errorMsg = "Unexpected error processing payment: " + e.getMessage();
            return OrderResult.failure(order.getId(), errorMsg);
        }
    }
    
    /**
     * Simple Order class for demonstration purposes.
     */
    public static class Order {
        private final String id;
        private final BigDecimal amount;
        private String status;
        private String transactionId;
        
        public Order(String id, BigDecimal amount) {
            this.id = Objects.requireNonNull(id);
            this.amount = Objects.requireNonNull(amount);
            this.status = "PENDING";
        }
        
        public String getId() { return id; }
        public BigDecimal getAmount() { return amount; }
        public String getStatus() { return status; }
        public String getTransactionId() { return transactionId; }
        
        public void markAsProcessed(String transactionId) {
            this.transactionId = transactionId;
            this.status = "PROCESSED";
        }
        
        public void markAsFailed() {
            this.status = "FAILED";
        }
    }
    
    /**
     * Result class for order processing operations.
     */
    public static class OrderResult {
        private final boolean successful;
        private final String orderId;
        private final String transactionId;
        private final BigDecimal amount;
        private final String message;
        
        private OrderResult(
                boolean successful,
                String orderId,
                String transactionId,
                BigDecimal amount,
                String message) {
            this.successful = successful;
            this.orderId = orderId;
            this.transactionId = transactionId;
            this.amount = amount;
            this.message = message;
        }
        
        public static OrderResult success(String orderId, String transactionId, BigDecimal amount) {
            return new OrderResult(true, orderId, transactionId, amount, "Order processed successfully");
        }
        
        public static OrderResult failure(String orderId, String message) {
            return new OrderResult(false, orderId, null, null, message);
        }
        
        public boolean isSuccessful() { return successful; }
        public String getOrderId() { return orderId; }
        public String getTransactionId() { return transactionId; }
        public BigDecimal getAmount() { return amount; }
        public String getMessage() { return message; }
        public String getErrorMessage() { return successful ? null : message; }
    }
}
