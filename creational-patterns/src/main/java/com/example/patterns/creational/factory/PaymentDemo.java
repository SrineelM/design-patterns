package com.example.patterns.creational.factory;

import java.math.BigDecimal;

/**
 * Demo application showing the Factory Method pattern in action.
 * 
 * This class provides a main method that demonstrates how to use the Factory Method
 * pattern to create and process payments with different payment methods.
 * 
 * <h3>Learning Objectives:</h3>
 * <ul>
 *     <li>Understand loose coupling between client and concrete classes</li>
 *     <li>See how factory method eliminates if-else chains</li>
 *     <li>Observe how new payment methods can be added without changing client code</li>
 *     <li>Learn real-world e-commerce payment processing patterns</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 */
public class PaymentDemo {
    
    /**
     * Main method demonstrating the Factory Method pattern with different payment scenarios.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Factory Method Pattern - Payment System Demo");
        System.out.println("========================================\n");
        
        // Demonstrate different payment scenarios
        demonstrateCreditCardPayment();
        System.out.println();
        
        demonstratePayPalPayment();
        System.out.println();
        
        demonstrateGooglePayPayment();
        System.out.println();
        
        demonstrateOrderProcessing();
        System.out.println();
        
        demonstrateFactoryFlexibility();
        System.out.println();
        
        demonstrateErrorHandling();
    }
    
    /**
     * Demonstrates credit card payment processing.
     */
    private static void demonstrateCreditCardPayment() {
        System.out.println("[CREDIT CARD PAYMENT]");
        System.out.println("Creating payment using factory method...");
        
        // Factory Method in action - client doesn't know concrete class
        Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
        
        // Validate and process payment
        BigDecimal amount = new BigDecimal("99.99");
        if (payment.validate(amount)) {
            System.out.println("Validation: PASSED - Amount is acceptable");
            
            PaymentResult result = payment.process(amount, "ORDER-CC-001");
            
            System.out.println("Result: " + result);
            System.out.println("Transaction ID: " + result.getTransactionId());
        }
    }
    
    /**
     * Demonstrates PayPal payment processing.
     */
    private static void demonstratePayPalPayment() {
        System.out.println("[PAYPAL PAYMENT]");
        System.out.println("Creating payment using factory method...");
        
        // Same factory method, different payment type
        Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_PAYPAL);
        
        // Validate and process payment
        BigDecimal amount = new BigDecimal("149.99");
        if (payment.validate(amount)) {
            System.out.println("Validation: PASSED - Amount is acceptable");
            
            PaymentResult result = payment.process(amount, "ORDER-PP-001");
            
            System.out.println("Result: " + result);
            System.out.println("Transaction ID: " + result.getTransactionId());
        }
    }
    
    /**
     * Demonstrates Google Pay payment processing.
     */
    private static void demonstrateGooglePayPayment() {
        System.out.println("[GOOGLE PAY PAYMENT]");
        System.out.println("Creating payment using factory method...");
        
        // Factory method handles all payment creation
        Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_GOOGLE_PAY);
        
        // Validate and process payment
        BigDecimal amount = new BigDecimal("49.99");
        if (payment.validate(amount)) {
            System.out.println("Validation: PASSED - Amount is acceptable");
            
            PaymentResult result = payment.process(amount, "ORDER-GP-001");
            
            System.out.println("Result: " + result);
            System.out.println("Transaction ID: " + result.getTransactionId());
        }
    }
    
    /**
     * Demonstrates order processing with the factory method.
     */
    private static void demonstrateOrderProcessing() {
        System.out.println("[ORDER PROCESSING - REAL-WORLD SCENARIO]");
        
        OrderProcessor processor = new OrderProcessor();
        
        // Create an order
        OrderProcessor.Order order = new OrderProcessor.Order("ORDER-2025-001", new BigDecimal("250.00"));
        System.out.println("Processing order: " + order.getId() + ", Amount: $" + order.getAmount());
        
        // Process with different payment methods using the factory
        PaymentMethod[] methods = {
            PaymentMethod.PAY_WITH_CREDIT_CARD,
            PaymentMethod.PAY_WITH_PAYPAL,
            PaymentMethod.PAY_WITH_GOOGLE_PAY
        };
        
        for (PaymentMethod method : methods) {
            System.out.println("\nAttempting payment with: " + method.getDisplayName());
            OrderProcessor.OrderResult result = processor.processPayment(order, method);
            
            if (result.isSuccessful()) {
                System.out.println("✓ Payment successful");
                System.out.println("  Transaction ID: " + result.getTransactionId());
            } else {
                System.out.println("✗ Payment failed: " + result.getErrorMessage());
            }
        }
    }
    
    /**
     * Demonstrates the flexibility of the factory method.
     * Shows how easily different payment methods can be used.
     */
    private static void demonstrateFactoryFlexibility() {
        System.out.println("[FACTORY FLEXIBILITY]");
        System.out.println("Demonstrating how factory enables flexible payment handling...");
        
        // Simulate different user selections
        PaymentMethod[] userSelections = {
            PaymentMethod.PAY_WITH_PAYPAL,
            PaymentMethod.PAY_WITH_CREDIT_CARD,
            PaymentMethod.PAY_WITH_GOOGLE_PAY
        };
        
        BigDecimal amount = new BigDecimal("75.50");
        String orderId = "ORDER-FLEX-001";
        
        for (PaymentMethod selection : userSelections) {
            System.out.println("\nProcessing with: " + selection.getDisplayName());
            
            // Factory creates appropriate payment handler
            Payment payment = PaymentFactory.createPayment(selection);
            
            // Client code doesn't change - same logic for all payment methods
            if (payment.validate(amount)) {
                PaymentResult result = payment.process(amount, orderId);
                System.out.println("Status: " + (result.isSuccessful() ? "SUCCESS" : "FAILED"));
            } else {
                System.out.println("Status: VALIDATION_FAILED");
            }
        }
    }
    
    /**
     * Demonstrates error handling capabilities.
     */
    private static void demonstrateErrorHandling() {
        System.out.println("[ERROR HANDLING]");
        
        // Test with invalid amounts
        Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
        
        System.out.println("\nTesting validation with amounts outside limits:");
        System.out.println("Min for Credit Card: $0.01, Max: $10,000.00");
        
        BigDecimal[] testAmounts = {
            new BigDecimal("0.00"),        // Invalid - zero
            new BigDecimal("-50.00"),      // Invalid - negative
            new BigDecimal("0.01"),        // Valid - minimum
            new BigDecimal("10000.00"),    // Valid - maximum
            new BigDecimal("10000.01")     // Invalid - exceeds maximum
        };
        
        for (BigDecimal amount : testAmounts) {
            boolean isValid = payment.validate(amount);
            System.out.println("Amount $" + amount + ": " + (isValid ? "VALID" : "INVALID"));
        }
        
        // Test null handling
        System.out.println("\nTesting null parameter handling:");
        try {
            PaymentFactory.createPayment(null);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught null: " + e.getMessage());
        }
    }
}
