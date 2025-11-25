package com.example.patterns.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * PayPal payment strategy.
 * 
 * @since 1.0
 */
@Component("paypal")
public class PayPalPaymentStrategy implements PaymentStrategy {
    
    private static final Logger log = LoggerFactory.getLogger(PayPalPaymentStrategy.class);
    
    @Override
    public boolean pay(BigDecimal amount) {
        log.info("Processing PayPal payment of ${}", amount);
        log.info("Redirecting to PayPal...");
        log.info("User authenticated with PayPal...");
        log.info("Payment confirmed by PayPal...");
        log.info("✓ PayPal payment successful!");
        return true;
    }
    
    @Override
    public boolean validate(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
    
    @Override
    public String getStrategyName() {
        return "PayPal";
    }
}
