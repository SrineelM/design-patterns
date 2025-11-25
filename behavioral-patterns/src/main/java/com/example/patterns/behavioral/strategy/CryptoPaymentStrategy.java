package com.example.patterns.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Cryptocurrency payment strategy.
 * 
 * @since 1.0
 */
@Component("crypto")
public class CryptoPaymentStrategy implements PaymentStrategy {
    
    private static final Logger log = LoggerFactory.getLogger(CryptoPaymentStrategy.class);
    private static final BigDecimal MIN_AMOUNT = BigDecimal.valueOf(10);
    
    @Override
    public boolean pay(BigDecimal amount) {
        log.info("Processing cryptocurrency payment of ${}", amount);
        log.info("Generating wallet address...");
        log.info("Waiting for blockchain confirmation...");
        log.info("Transaction confirmed in blockchain...");
        log.info("✓ Cryptocurrency payment successful!");
        return true;
    }
    
    @Override
    public boolean validate(BigDecimal amount) {
        if (amount.compareTo(MIN_AMOUNT) < 0) {
            log.warn("Cryptocurrency payments must be at least ${}", MIN_AMOUNT);
            return false;
        }
        return true;
    }
    
    @Override
    public String getStrategyName() {
        return "Cryptocurrency";
    }
}
