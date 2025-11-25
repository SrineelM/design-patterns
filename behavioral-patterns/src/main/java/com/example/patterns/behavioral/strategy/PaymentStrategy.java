package com.example.patterns.behavioral.strategy;

import com.example.patterns.common.DesignPattern;

import java.math.BigDecimal;

/**
 * Strategy interface for payment processing.
 * 
 * <p>This is the Strategy interface in the pattern. It defines the common
 * interface that all concrete strategies must implement.</p>
 * 
 * <h3>Without Strategy Pattern:</h3>
 * <pre>
 * if (paymentType.equals("credit_card")) {
 *     // credit card logic
 * } else if (paymentType.equals("paypal")) {
 *     // PayPal logic
 * } else if (paymentType.equals("crypto")) {
 *     // crypto logic
 * }
 * </pre>
 * 
 * <h3>With Strategy Pattern:</h3>
 * <pre>
 * PaymentStrategy strategy = getStrategy(paymentType);
 * strategy.pay(amount);
 * </pre>
 * 
 * @since 1.0
 */
@DesignPattern(
    name = "Strategy",
    category = "Behavioral",
    description = "Encapsulates interchangeable algorithms in separate classes"
)
public interface PaymentStrategy {
    
    /**
     * Processes a payment using this strategy.
     * 
     * @param amount The amount to pay
     * @return true if payment was successful
     */
    boolean pay(BigDecimal amount);
    
    /**
     * Validates if this strategy can process the payment.
     * 
     * @param amount The amount to validate
     * @return true if this strategy can handle the payment
     */
    boolean validate(BigDecimal amount);
    
    /**
     * Gets the name of this payment strategy.
     * 
     * @return The strategy name
     */
    String getStrategyName();
}
