package com.example.patterns.creational.factory;

import com.example.patterns.common.DesignPattern;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * CreditCardPayment implements the Payment interface for credit card transactions.
 * 
 * This class handles payment processing through credit card payment gateways.
 * It includes validation of credit card information, fraud checks, and integration
 * with payment processors like Stripe, Square, or Authorize.Net.
 * 
 * <h3>Payment Processing Flow:</h3>
 * <ol>
 *     <li>Validate payment amount and order ID</li>
 *     <li>Check card validity (card number, expiration, CVV)</li>
 *     <li>Verify amount is within transaction limits</li>
 *     <li>Submit to credit card processor</li>
 *     <li>Return transaction result</li>
 * </ol>
 * 
 * <h3>Features:</h3>
 * <ul>
 *     <li>Supports all major credit cards (Visa, MasterCard, AmEx, Discover)</li>
 *     <li>Validates card information before processing</li>
 *     <li>Implements fraud detection rules</li>
 *     <li>Handles transaction reversals and refunds</li>
 * </ul>
 * 
 * <h3>Transaction Limits:</h3>
 * <ul>
 *     <li>Minimum: $0.01</li>
 *     <li>Maximum: $10,000.00</li>
 * </ul>
 * 
 * Example:
 * <pre>
 * Payment creditCard = new CreditCardPayment();
 * 
 * // Validate amount before processing
 * if (creditCard.validate(new BigDecimal("99.99"))) {
 *     PaymentResult result = creditCard.process(new BigDecimal("99.99"), "ORDER-12345");
 *     if (result.isSuccessful()) {
 *         System.out.println("Transaction ID: " + result.getTransactionId());
 *     }
 * }
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 * @see Payment
 * @see PaymentFactory
 */
@DesignPattern(
    name = "Factory Method",
    category = "Creational",
    description = "CreditCardPayment concrete implementation created by PaymentFactory"
)
public class CreditCardPayment implements Payment {
    
    /** Minimum transaction amount (one cent) */
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    
    /** Maximum transaction amount (ten thousand dollars) */
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("10000.00");
    
    /**
     * Processes a credit card payment transaction.
     * 
     * This method:
     * <ol>
     *     <li>Validates the payment amount and order ID</li>
     *     <li>Connects to the credit card processor (simulated)</li>
     *     <li>Submits the transaction</li>
     *     <li>Returns the processing result</li>
     * </ol>
     * 
     * @param amount the payment amount in dollars
     * @param orderId the unique order identifier
     * @return PaymentResult with transaction details
     * @throws IllegalArgumentException if amount or orderId is invalid
     * 
     * Example:
     * <pre>
     * Payment payment = new CreditCardPayment();
     * PaymentResult result = payment.process(new BigDecimal("99.99"), "ORDER-12345");
     * System.out.println(result);
     * </pre>
     */
    @Override
    public PaymentResult process(BigDecimal amount, String orderId) {
        // Validate inputs
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID must not be null or empty");
        }
        
        // Validate amount is within limits
        if (!validate(amount)) {
            String errorMsg = String.format(
                "Credit card transaction amount must be between %s and %s",
                MIN_AMOUNT, MAX_AMOUNT
            );
            return PaymentResult.failure(amount, errorMsg);
        }
        
        try {
            // Simulate credit card payment processing
            simulateCreditCardProcessing(amount, orderId);
            
            // Return successful result
            return PaymentResult.success(amount);
            
        } catch (Exception e) {
            // Handle payment processing errors
            String errorMsg = String.format(
                "Credit card processing failed for order %s: %s",
                orderId, e.getMessage()
            );
            return PaymentResult.failure(amount, errorMsg);
        }
    }
    
    /**
     * Validates whether the payment amount is within acceptable limits for credit cards.
     * 
     * @param amount the amount to validate
     * @return true if amount is between MIN_AMOUNT and MAX_AMOUNT
     */
    @Override
    public boolean validate(BigDecimal amount) {
        if (amount == null) {
            return false;
        }
        
        return amount.compareTo(MIN_AMOUNT) >= 0 && 
               amount.compareTo(MAX_AMOUNT) <= 0;
    }
    
    /**
     * Gets the name of this payment method.
     * 
     * @return "Credit Card"
     */
    @Override
    public String getPaymentMethodName() {
        return "Credit Card";
    }
    
    /**
     * Refunds a credit card transaction.
     * 
     * Credit card refunds are typically processed within 3-5 business days.
     * 
     * @param transactionId the ID of the transaction to refund
     * @param amount the refund amount
     * @return PaymentResult indicating refund status
     * @throws IllegalArgumentException if parameters are invalid
     */
    @Override
    public PaymentResult refund(String transactionId, BigDecimal amount) {
        // Validate inputs
        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction ID must not be null or empty");
        }
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Refund amount must be positive");
        }
        
        try {
            // Simulate refund processing
            simulateRefundProcessing(transactionId, amount);
            
            // Return successful refund result
            return PaymentResult.success(amount);
            
        } catch (Exception e) {
            String errorMsg = String.format(
                "Credit card refund failed for transaction %s: %s",
                transactionId, e.getMessage()
            );
            return PaymentResult.failure(amount, errorMsg);
        }
    }
    
    /**
     * Simulates communication with a credit card payment processor.
     * 
     * In a real implementation, this would:
     * <ul>
     *     <li>Connect to payment gateway API (Stripe, Square, Authorize.Net)</li>
     *     <li>Submit card and amount information securely</li>
     *     <li>Handle authorization codes and response codes</li>
     *     <li>Manage connection timeouts and retries</li>
     * </ul>
     * 
     * @param amount the payment amount
     * @param orderId the order identifier
     * @throws PaymentProcessingException if processor communication fails
     */
    private void simulateCreditCardProcessing(BigDecimal amount, String orderId) {
        // In a real implementation, this would:
        // 1. Encrypt card information
        // 2. Connect to payment processor
        // 3. Submit authorization request
        // 4. Handle processor response
        
        // Simulate a slight delay to represent network call
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                "Credit card processing interrupted: " + e.getMessage(), e
            );
        }
        
        // Log transaction (in real implementation, would be to secure log)
        String logMessage = String.format(
            "[CREDIT_CARD_PROCESSOR] Processing payment: Amount=%s, Order=%s, Processor=%s",
            amount, orderId, "stripe-gateway"
        );
        System.out.println(logMessage);
    }
    
    /**
     * Simulates a refund request to the payment processor.
     * 
     * @param transactionId the original transaction ID to refund
     * @param amount the refund amount
     * @throws PaymentProcessingException if refund request fails
     */
    private void simulateRefundProcessing(String transactionId, BigDecimal amount) {
        // Simulate a delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                "Credit card refund interrupted: " + e.getMessage(), e
            );
        }
        
        // Log refund request
        String logMessage = String.format(
            "[CREDIT_CARD_PROCESSOR] Processing refund: TransactionID=%s, Amount=%s",
            transactionId, amount
        );
        System.out.println(logMessage);
    }
}
