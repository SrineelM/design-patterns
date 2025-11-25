package com.example.patterns.creational.factory;

/**
 * PaymentMethod enum defines the different payment methods available in the system.
 * 
 * This enum serves as a parameter for the PaymentFactory, allowing clients to specify
 * which type of payment they want to create. Using an enum provides type safety and
 * prevents invalid payment method values from being passed to the factory.
 * 
 * <h3>Benefits of using an Enum:</h3>
 * <ul>
 *     <li>Type-safe - compiler ensures only valid values are used</li>
 *     <li>Prevents null values - enum instances are guaranteed to exist</li>
 *     <li>Clear intent - code is self-documenting</li>
 *     <li>Easy to switch on - matches all cases at compile time</li>
 * </ul>
 * 
 * Example:
 * <pre>
 * // Type-safe way to create payments
 * Payment creditCardPayment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
 * Payment paypalPayment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_PAYPAL);
 * Payment googlePayPayment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_GOOGLE_PAY);
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 */
public enum PaymentMethod {
    
    /**
     * Credit Card payment method - processes payments using credit card information.
     * Supports all major credit cards (Visa, MasterCard, American Express, Discover).
     */
    PAY_WITH_CREDIT_CARD("Credit Card", "credit-card-processor"),
    
    /**
     * PayPal payment method - processes payments through PayPal's payment gateway.
     * Suitable for users who prefer PayPal's payment processing.
     */
    PAY_WITH_PAYPAL("PayPal", "paypal-gateway"),
    
    /**
     * Google Pay payment method - processes payments using Google Pay digital wallet.
     * Convenient for users who store payment information in their Google account.
     */
    PAY_WITH_GOOGLE_PAY("Google Pay", "google-pay-service");
    
    /**
     * Human-readable display name for the payment method.
     */
    private final String displayName;
    
    /**
     * Payment processor identifier used for routing payment requests.
     */
    private final String processorId;
    
    /**
     * Constructor for PaymentMethod enum.
     * 
     * @param displayName the human-readable name of the payment method
     * @param processorId the identifier for the payment processor
     */
    PaymentMethod(String displayName, String processorId) {
        this.displayName = displayName;
        this.processorId = processorId;
    }
    
    /**
     * Gets the display name of the payment method.
     * 
     * @return the human-readable name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Gets the processor identifier for this payment method.
     * 
     * @return the processor ID used for routing
     */
    public String getProcessorId() {
        return processorId;
    }
}
