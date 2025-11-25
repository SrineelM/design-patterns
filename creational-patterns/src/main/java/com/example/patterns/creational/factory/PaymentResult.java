package com.example.patterns.creational.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * PaymentResult class encapsulates the result of a payment transaction.
 * 
 * This immutable class provides all relevant information about a payment operation,
 * including success status, transaction ID, timestamp, and error messages if applicable.
 * 
 * <h3>Key Features:</h3>
 * <ul>
 *     <li>Immutable design - thread-safe and predictable</li>
 *     <li>Comprehensive status information</li>
 *     <li>Error details for failed transactions</li>
 *     <li>Timestamp for audit trails</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 */
public final class PaymentResult {
    
    private final boolean successful;
    private final String transactionId;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final String message;
    private final String errorMessage;
    
    /**
     * Creates a successful payment result.
     * 
     * @param amount the payment amount processed
     * @return a PaymentResult representing a successful transaction
     */
    public static PaymentResult success(BigDecimal amount) {
        return new PaymentResult(
            true,
            UUID.randomUUID().toString(),
            amount,
            LocalDateTime.now(),
            "Payment processed successfully",
            null
        );
    }
    
    /**
     * Creates a failed payment result with an error message.
     * 
     * @param amount the payment amount that failed to process
     * @param errorMessage description of the failure
     * @return a PaymentResult representing a failed transaction
     */
    public static PaymentResult failure(BigDecimal amount, String errorMessage) {
        return new PaymentResult(
            false,
            null,
            amount,
            LocalDateTime.now(),
            "Payment failed",
            errorMessage
        );
    }
    
    /**
     * Private constructor - use factory methods instead.
     */
    private PaymentResult(
            boolean successful,
            String transactionId,
            BigDecimal amount,
            LocalDateTime timestamp,
            String message,
            String errorMessage) {
        this.successful = successful;
        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.message = message;
        this.errorMessage = errorMessage;
    }
    
    /**
     * Checks if the payment transaction was successful.
     * 
     * @return true if payment was processed successfully, false otherwise
     */
    public boolean isSuccessful() {
        return successful;
    }
    
    /**
     * Gets the unique transaction ID for this payment.
     * 
     * @return the transaction ID (null if transaction was not successful)
     */
    public String getTransactionId() {
        return transactionId;
    }
    
    /**
     * Gets the payment amount.
     * 
     * @return the amount that was processed or attempted to process
     */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /**
     * Gets the timestamp of when this payment was processed.
     * 
     * @return the processing timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Gets the status message for this transaction.
     * 
     * @return a message describing the transaction status
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Gets the error message if the transaction failed.
     * 
     * @return the error message (null if transaction was successful)
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    
    /**
     * Returns a formatted string representation of the payment result.
     * 
     * @return a detailed string including status, amount, and timestamp
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        if (successful) {
            return String.format(
                "PaymentResult{status=SUCCESS, amount=%s, transactionId=%s, timestamp=%s}",
                amount, transactionId, timestamp.format(formatter)
            );
        } else {
            return String.format(
                "PaymentResult{status=FAILED, amount=%s, error=%s, timestamp=%s}",
                amount, errorMessage, timestamp.format(formatter)
            );
        }
    }
}
