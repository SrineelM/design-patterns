package com.example.patterns.structural.facade;

/**
 * Order result record.
 * 
 * @param success whether the order was successful
 * @param orderId the order identifier (if successful)
 * @param message the result message
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public record OrderResult(
    boolean success,
    String orderId,
    String message
) {
    /**
     * Creates a successful order result.
     *
     * @param orderId the order identifier
     * @return successful order result
     */
    public static OrderResult success(String orderId) {
        return new OrderResult(true, orderId, "Order placed successfully");
    }
    
    /**
     * Creates a failed order result.
     *
     * @param message the failure message
     * @return failed order result
     */
    public static OrderResult failure(String message) {
        return new OrderResult(false, null, message);
    }
}
