package com.example.patterns.creational.factory;

/**
 * Custom exception for payment processing errors.
 * 
 * This exception is thrown when payment processing encounters an error condition
 * that prevents the transaction from completing. It provides detailed error
 * information to help with debugging and user communication.
 * 
 * Example:
 * <pre>
 * try {
 *     PaymentResult result = payment.process(amount, orderId);
 * } catch (PaymentProcessingException e) {
 *     System.out.println("Payment failed: " + e.getMessage());
 * }
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 */
public class PaymentProcessingException extends RuntimeException {
    
    /**
     * Constructs a PaymentProcessingException with a detail message.
     * 
     * @param message the detail message explaining the error
     */
    public PaymentProcessingException(String message) {
        super(message);
    }
    
    /**
     * Constructs a PaymentProcessingException with a detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
