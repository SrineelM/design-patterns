package com.example.patterns.creational.factory;

import com.example.patterns.common.DesignPattern;
import java.util.Objects;

/**
 * PaymentFactory provides a centralized factory method for creating Payment instances.
 * 
 * This factory class is the core of the Factory Method design pattern implementation.
 * Instead of client code directly instantiating payment classes, all object creation
 * is delegated to this factory. This provides several key benefits:
 * 
 * <h3>Key Benefits:</h3>
 * <ul>
 *     <li><b>Loose Coupling:</b> Client code doesn't depend on concrete payment classes</li>
 *     <li><b>Centralized Creation:</b> All payment creation logic is in one place</li>
 *     <li><b>Easy to Extend:</b> Adding new payment methods only requires changes here</li>
 *     <li><b>Single Responsibility:</b> Object creation logic is separated from business logic</li>
 *     <li><b>Consistency:</b> Ensures all payments are created uniformly</li>
 * </ul>
 * 
 * <h3>How It Works:</h3>
 * <ol>
 *     <li>Client calls {@link #createPayment(PaymentMethod)}</li>
 *     <li>Factory examines the {@link PaymentMethod} parameter</li>
 *     <li>Factory instantiates and returns the appropriate Payment implementation</li>
 *     <li>Client works with Payment interface, not concrete classes</li>
 * </ol>
 * 
 * <h3>Adding New Payment Methods:</h3>
 * To add support for a new payment method (e.g., Apple Pay):
 * <ol>
 *     <li>Add new enum value to {@link PaymentMethod}</li>
 *     <li>Create new class implementing {@link Payment} (e.g., ApplePayPayment)</li>
 *     <li>Add new case in {@link #createPayment(PaymentMethod)}</li>
 * </ol>
 * 
 * That's all! No changes needed in client code.
 * 
 * <h3>Example Usage:</h3>
 * <pre>
 * // Client code is simple and payment-method agnostic
 * Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
 * PaymentResult result = payment.process(new BigDecimal("99.99"), "ORDER-12345");
 * 
 * // Easy to switch payment methods without changing logic
 * Payment paypal = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_PAYPAL);
 * PaymentResult result2 = paypal.process(new BigDecimal("99.99"), "ORDER-12345");
 * </pre>
 * 
 * <h3>Real-World Application:</h3>
 * In a real e-commerce application:
 * <pre>
 * public class OrderCheckoutService {
 *     public void checkout(Order order, PaymentMethod method) {
 *         // Factory Method pattern in action
 *         Payment payment = PaymentFactory.createPayment(method);
 *         
 *         // Process payment without knowing concrete implementation
 *         PaymentResult result = payment.process(
 *             order.getTotal(),
 *             order.getId()
 *         );
 *         
 *         if (result.isSuccessful()) {
 *             order.markAsPaymentProcessed(result.getTransactionId());
 *         } else {
 *             order.markAsPaymentFailed(result.getErrorMessage());
 *         }
 *     }
 * }
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 * @see Payment
 * @see PaymentMethod
 * @see CreditCardPayment
 * @see PayPalPayment
 * @see GooglePayPayment
 */
@DesignPattern(
    name = "Factory Method",
    category = "Creational",
    description = "Provides a centralized factory method for creating Payment instances based on PaymentMethod"
)
public final class PaymentFactory {
    
    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * This factory only provides static factory methods and should never be instantiated.
     */
    private PaymentFactory() {
        throw new AssertionError("Cannot instantiate PaymentFactory");
    }
    
    /**
     * Factory method that creates and returns a Payment instance based on the specified PaymentMethod.
     * 
     * This is the core of the Factory Method pattern. Client code calls this method
     * instead of directly instantiating payment classes.
     * 
     * <h3>Implementation Details:</h3>
     * <ul>
     *     <li>Uses switch expression (Java 17 feature) for cleaner code</li>
     *     <li>Validates input parameter</li>
     *     <li>Throws descriptive exception for unsupported payment methods</li>
     *     <li>Guarantees non-null return value</li>
     * </ul>
     * 
     * <h3>Supported Payment Methods:</h3>
     * <ul>
     *     <li>{@link PaymentMethod#PAY_WITH_CREDIT_CARD} → {@link CreditCardPayment}</li>
     *     <li>{@link PaymentMethod#PAY_WITH_PAYPAL} → {@link PayPalPayment}</li>
     *     <li>{@link PaymentMethod#PAY_WITH_GOOGLE_PAY} → {@link GooglePayPayment}</li>
     * </ul>
     * 
     * @param method the desired payment method (must not be null)
     * @return a Payment implementation for the specified method (never null)
     * @throws IllegalArgumentException if method is null
     * @throws UnsupportedOperationException if method is not supported
     * 
     * Example:
     * <pre>
     * // Create credit card payment
     * Payment creditCard = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
     * // Returns: CreditCardPayment instance
     * 
     * // Create PayPal payment
     * Payment paypal = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_PAYPAL);
     * // Returns: PayPalPayment instance
     * 
     * // Create Google Pay payment
     * Payment googlePay = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_GOOGLE_PAY);
     * // Returns: GooglePayPayment instance
     * 
     * // Null parameter throws exception
     * Payment invalid = PaymentFactory.createPayment(null);
     * // Throws: IllegalArgumentException
     * </pre>
     */
    public static Payment createPayment(PaymentMethod method) {
        // Validate input parameter
        if (method == null) {
            throw new IllegalArgumentException(
                "PaymentMethod cannot be null. Supported methods are: " +
                "PAY_WITH_CREDIT_CARD, PAY_WITH_PAYPAL, PAY_WITH_GOOGLE_PAY"
            );
        }
        
        // Use Java 17 switch expression to create appropriate payment instance
        return switch (method) {
            case PAY_WITH_CREDIT_CARD -> {
                // Log factory decision (in real app, would use proper logging framework)
                System.out.println("[PaymentFactory] Creating CreditCardPayment instance");
                yield new CreditCardPayment();
            }
            
            case PAY_WITH_PAYPAL -> {
                // Log factory decision
                System.out.println("[PaymentFactory] Creating PayPalPayment instance");
                yield new PayPalPayment();
            }
            
            case PAY_WITH_GOOGLE_PAY -> {
                // Log factory decision
                System.out.println("[PaymentFactory] Creating GooglePayPayment instance");
                yield new GooglePayPayment();
            }
        };
    }
    
    /**
     * Alternative factory method that creates a payment based on a string payment method name.
     * 
     * This method provides flexibility for scenarios where payment method comes from
     * external sources (configuration files, user input, API requests, etc.).
     * 
     * <h3>Supported String Values (case-insensitive):</h3>
     * <ul>
     *     <li>"CREDIT_CARD" or "credit-card" → {@link PaymentMethod#PAY_WITH_CREDIT_CARD}</li>
     *     <li>"PAYPAL" or "paypal" → {@link PaymentMethod#PAY_WITH_PAYPAL}</li>
     *     <li>"GOOGLE_PAY" or "google-pay" → {@link PaymentMethod#PAY_WITH_GOOGLE_PAY}</li>
     * </ul>
     * 
     * @param methodName the payment method name as a string (must not be null or empty)
     * @return a Payment implementation for the specified method
     * @throws IllegalArgumentException if methodName is null, empty, or unknown
     * 
     * Example:
     * <pre>
     * // From configuration file
     * String configuredMethod = config.getProperty("payment.method");
     * Payment payment = PaymentFactory.createPaymentByName(configuredMethod);
     * 
     * // From HTTP request
     * String methodParam = request.getParameter("paymentMethod");
     * Payment payment = PaymentFactory.createPaymentByName(methodParam);
     * </pre>
     */
    public static Payment createPaymentByName(String methodName) {
        // Validate input
        if (methodName == null || methodName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Payment method name cannot be null or empty"
            );
        }
        
        try {
            // Convert string to enum and use main factory method
            PaymentMethod method = PaymentMethod.valueOf(
                methodName.trim().toUpperCase().replace("-", "_")
            );
            return createPayment(method);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Unknown payment method: " + methodName + 
                ". Supported methods are: credit-card, paypal, google-pay",
                e
            );
        }
    }
}
