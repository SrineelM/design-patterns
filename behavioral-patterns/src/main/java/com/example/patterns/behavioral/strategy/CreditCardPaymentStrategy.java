package com.example.patterns.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Credit card payment strategy.
 * 
 * <p>This is a Concrete Strategy that implements the payment processing
 * algorithm for credit cards.</p>
 * 
 * @since 1.0
 */
@Component("creditCard")
public class CreditCardPaymentStrategy implements PaymentStrategy {
    
    private static final Logger log = LoggerFactory.getLogger(CreditCardPaymentStrategy.class);
    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(10000);
    
    @Override
    public boolean pay(BigDecimal amount) {
        log.info("Processing credit card payment of ${}", amount);
        log.info("Contacting payment gateway...");
        log.info("Validating card details...");
        log.info("Authorizing transaction...");
        log.info("✓ Credit card payment successful!");
        return true;
    }
    
    @Override
    public boolean validate(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.warn("Invalid amount: ${}", amount);
            return false;
        }
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            log.warn("Amount exceeds credit card limit: ${}", amount);
            return false;
        }
        return true;
    }
    
    @Override
    public String getStrategyName() {
        return "Credit Card";
    }
}
