package com.example.patterns.creational.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payment interface defines the contract for all payment implementations.
 * 
 * This interface represents the common behavior that all payment methods must support.
 * By programming to this interface, client code remains independent of concrete payment
 * implementations, following the Dependency Inversion Principle.
 * 
 * <h3>Key Responsibilities:</h3>
 * <ul>
 *     <li>Processing payment transactions</li>
 *     <li>Validating payment information</li>
 *     <li>Returning payment status and transaction details</li>
 * </ul>
 * 
 * <h3>Design Principles:</h3>
 * <ul>
 *     <li><b>Abstraction:</b> Hides concrete implementation details</li>
 *     <li><b>Polymorphism:</b> Allows different payment methods to be used interchangeably</li>
 *     <li><b>Extensibility:</b> New payment methods can be added by implementing this interface</li>
 * </ul>
 * 
 * Example:
 * <pre>
 * // Client code works with any Payment implementation
 * Payment payment = PaymentFactory.createPayment(paymentMethod);
 * PaymentResult result = payment.process(amount, orderId);
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 */
public interface Payment {
    
    /**
     * Processes a payment transaction with the specified amount and order information.
     * 
     * Implementations should:
     * <ul>
     *     <li>Validate the payment amount (must be positive)</li>
     *     <li>Validate the order ID (must not be null or empty)</li>
     *     <li>Communicate with the appropriate payment gateway</li>
     *     <li>Handle errors gracefully with meaningful messages</li>
     *     <li>Return the transaction result with status and transaction ID</li>
     * </ul>
     * 
     * @param amount the payment amount in dollars (must be positive)
     * @param orderId the unique identifier for the order (must not be null or empty)
     * @return a PaymentResult containing the transaction status and details
     * @throws IllegalArgumentException if amount is negative or orderId is invalid
     * @throws PaymentProcessingException if payment processing fails
     * 
     * Example:
     * <pre>
     * Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
     * PaymentResult result = payment.process(99.99, "ORDER-12345");
     * if (result.isSuccessful()) {
     *     System.out.println("Transaction ID: " + result.getTransactionId());
     * }
     * </pre>
     */
    PaymentResult process(BigDecimal amount, String orderId);
    
    /**
     * Validates whether this payment method can process the specified amount.
     * 
     * Some payment methods may have limits or restrictions:
     * <ul>
     *     <li>Minimum transaction amount</li>
     *     <li>Maximum transaction amount</li>
     *     <li>Daily transaction limits</li>
     * </ul>
     * 
     * @param amount the payment amount to validate
     * @return true if the amount can be processed, false otherwise
     * 
     * Example:
     * <pre>
     * Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
     * if (payment.validate(amount)) {
     *     PaymentResult result = payment.process(amount, orderId);
     * }
     * </pre>
     */
    boolean validate(BigDecimal amount);
    
    /**
     * Gets the display name of this payment method.
     * 
     * @return the human-readable name (e.g., "Credit Card", "PayPal")
     */
    String getPaymentMethodName();
    
    /**
     * Refunds a previously processed payment transaction.
     * 
     * This method should:
     * <ul>
     *     <li>Verify the transaction ID exists and is valid</li>
     *     <li>Check that the transaction hasn't already been refunded</li>
     *     <li>Communicate with the payment gateway to process the refund</li>
     *     <li>Return the refund status</li>
     * </ul>
     * 
     * @param transactionId the ID of the transaction to refund
     * @param amount the amount to refund (must not exceed original transaction amount)
     * @return a PaymentResult containing the refund status
     * @throws IllegalArgumentException if transactionId is invalid or amount is invalid
     * 
     * Example:
     * <pre>
     * Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
     * PaymentResult refund = payment.refund("TXN-789456", new BigDecimal("99.99"));
     * </pre>
     */
    PaymentResult refund(String transactionId, BigDecimal amount);
}
