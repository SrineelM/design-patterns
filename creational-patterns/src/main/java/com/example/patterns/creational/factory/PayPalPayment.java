package com.example.patterns.creational.factory;

import com.example.patterns.common.DesignPattern;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * PayPalPayment implements the Payment interface for PayPal transactions.
 * 
 * This class handles payment processing through PayPal's payment gateway.
 * PayPal accounts are more flexible than credit cards and include buyer protection features.
 * This implementation demonstrates integration with the PayPal REST API.
 * 
 * <h3>Payment Processing Flow:</h3>
 * <ol>
 *     <li>Validate payment amount and order ID</li>
 *     <li>Verify PayPal account status</li>
 *     <li>Check amount against PayPal transaction limits</li>
 *     <li>Submit to PayPal gateway via REST API</li>
 *     <li>Handle buyer authentication</li>
 *     <li>Return transaction result</li>
 * </ol>
 * 
 * <h3>Features:</h3>
 * <ul>
 *     <li>Buyer protection (buyer can dispute unauthorized charges)</li>
 *     <li>No credit card required</li>
 *     <li>Lower fraud rates due to PayPal's authentication</li>
 *     <li>Global payment support</li>
 *     <li>Multi-currency transactions</li>
 * </ul>
 * 
 * <h3>Transaction Limits:</h3>
 * <ul>
 *     <li>Minimum: $0.01</li>
 *     <li>Maximum: $20,000.00</li>
 * </ul>
 * 
 * Example:
 * <pre>
 * Payment paypal = new PayPalPayment();
 * 
 * // Validate amount before processing
 * if (paypal.validate(new BigDecimal("149.99"))) {
 *     PaymentResult result = paypal.process(new BigDecimal("149.99"), "ORDER-67890");
 *     if (result.isSuccessful()) {
 *         System.out.println("PayPal Transaction ID: " + result.getTransactionId());
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
    description = "PayPalPayment concrete implementation created by PaymentFactory"
)
public class PayPalPayment implements Payment {
    
    /** Minimum transaction amount (one cent) */
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    
    /** Maximum transaction amount (twenty thousand dollars) */
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("20000.00");
    
    /** PayPal API endpoint for processing payments */
    private static final String PAYPAL_API_ENDPOINT = "https://api.paypal.com/v2/checkout/orders";
    
    /**
     * Processes a PayPal payment transaction.
     * 
     * This method:
     * <ol>
     *     <li>Validates the payment amount and order ID</li>
     *     <li>Authenticates with PayPal API</li>
     *     <li>Creates and captures PayPal order</li>
     *     <li>Returns the transaction result</li>
     * </ol>
     * 
     * PayPal transactions typically include:
     * <ul>
     *     <li>Order creation with amount and currency</li>
     *     <li>Buyer authentication or redirect</li>
     *     <li>Order capture/finalization</li>
     * </ul>
     * 
     * @param amount the payment amount in dollars
     * @param orderId the unique order identifier
     * @return PaymentResult with transaction details
     * @throws IllegalArgumentException if amount or orderId is invalid
     * 
     * Example:
     * <pre>
     * Payment payment = new PayPalPayment();
     * PaymentResult result = payment.process(new BigDecimal("149.99"), "ORDER-67890");
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
                "PayPal transaction amount must be between %s and %s",
                MIN_AMOUNT, MAX_AMOUNT
            );
            return PaymentResult.failure(amount, errorMsg);
        }
        
        try {
            // Create PayPal order
            String paypalOrderId = createPayPalOrder(amount, orderId);
            
            // Capture PayPal order
            capturePayPalOrder(paypalOrderId, amount);
            
            // Return successful result
            return PaymentResult.success(amount);
            
        } catch (Exception e) {
            // Handle PayPal processing errors
            String errorMsg = String.format(
                "PayPal processing failed for order %s: %s",
                orderId, e.getMessage()
            );
            return PaymentResult.failure(amount, errorMsg);
        }
    }
    
    /**
     * Validates whether the payment amount is within acceptable limits for PayPal.
     * 
     * PayPal generally allows larger transactions than credit cards due to
     * the security measures in place.
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
     * @return "PayPal"
     */
    @Override
    public String getPaymentMethodName() {
        return "PayPal";
    }
    
    /**
     * Refunds a PayPal transaction.
     * 
     * PayPal refunds are typically processed immediately and appear in the
     * buyer's account within seconds to minutes.
     * 
     * @param transactionId the ID of the PayPal transaction to refund
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
            // Request refund from PayPal
            submitPayPalRefund(transactionId, amount);
            
            // Return successful refund result
            return PaymentResult.success(amount);
            
        } catch (Exception e) {
            String errorMsg = String.format(
                "PayPal refund failed for transaction %s: %s",
                transactionId, e.getMessage()
            );
            return PaymentResult.failure(amount, errorMsg);
        }
    }
    
    /**
     * Creates a PayPal order via the REST API.
     * 
     * In a real implementation, this would:
     * <ul>
     *     <li>Authenticate with PayPal using OAuth 2.0</li>
     *     <li>Submit POST request to /v2/checkout/orders</li>
     *     <li>Include payment intent, amount, and currency</li>
     *     <li>Parse and return PayPal order ID</li>
     * </ul>
     * 
     * @param amount the payment amount
     * @param orderId the merchant order ID
     * @return PayPal order ID
     * @throws PaymentProcessingException if order creation fails
     */
    private String createPayPalOrder(BigDecimal amount, String orderId) {
        try {
            // In a real implementation, this would make an HTTP POST request:
            // POST /v2/checkout/orders
            // Headers: Authorization: Bearer <access_token>
            // Body: {
            //   "intent": "CAPTURE",
            //   "purchase_units": [{
            //     "amount": {"currency_code": "USD", "value": amount},
            //     "reference_id": orderId
            //   }]
            // }
            
            // Simulate delay
            Thread.sleep(150);
            
            // Simulate PayPal order creation
            String paypalOrderId = "PP-" + System.nanoTime();
            String logMessage = String.format(
                "[PAYPAL_API] Order created: PayPalOrderID=%s, MerchantOrder=%s, Amount=%s",
                paypalOrderId, orderId, amount
            );
            System.out.println(logMessage);
            
            return paypalOrderId;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                "PayPal order creation interrupted: " + e.getMessage(), e
            );
        }
    }
    
    /**
     * Captures (finalizes) a PayPal order that was previously created.
     * 
     * In a real implementation, this would:
     * <ul>
     *     <li>Make HTTP POST request to /v2/checkout/orders/{id}/capture</li>
     *     <li>Include payment intent and amount</li>
     *     <li>Return capture confirmation with transaction ID</li>
     * </ul>
     * 
     * @param paypalOrderId the PayPal order ID to capture
     * @param amount the amount being captured
     * @throws PaymentProcessingException if capture fails
     */
    private void capturePayPalOrder(String paypalOrderId, BigDecimal amount) {
        try {
            // Simulate delay for API call
            Thread.sleep(150);
            
            // Simulate PayPal order capture
            String logMessage = String.format(
                "[PAYPAL_API] Order captured: PayPalOrderID=%s, Amount=%s, Status=COMPLETED",
                paypalOrderId, amount
            );
            System.out.println(logMessage);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                "PayPal order capture interrupted: " + e.getMessage(), e
            );
        }
    }
    
    /**
     * Submits a refund request to PayPal.
     * 
     * In a real implementation, this would:
     * <ul>
     *     <li>Make HTTP POST request to /v2/payments/captures/{id}/refund</li>
     *     <li>Include refund amount</li>
     *     <li>Return refund confirmation</li>
     * </ul>
     * 
     * @param transactionId the PayPal transaction ID to refund
     * @param amount the refund amount
     * @throws PaymentProcessingException if refund request fails
     */
    private void submitPayPalRefund(String transactionId, BigDecimal amount) {
        try {
            // Simulate delay
            Thread.sleep(100);
            
            // Simulate PayPal refund
            String logMessage = String.format(
                "[PAYPAL_API] Refund submitted: TransactionID=%s, Amount=%s, Status=PENDING",
                transactionId, amount
            );
            System.out.println(logMessage);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                "PayPal refund interrupted: " + e.getMessage(), e
            );
        }
    }
}
