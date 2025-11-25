package com.example.patterns.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Payment processor that uses different strategies (Context in Strategy pattern).
 * 
 * <p>This is the Context in the Strategy pattern. It:</p>
 * <ul>
 *   <li>Maintains a reference to a Strategy object</li>
 *   <li>Delegates algorithm execution to the strategy</li>
 *   <li>Can switch strategies at runtime</li>
 * </ul>
 * 
 * <h3>Spring Integration:</h3>
 * <p>Spring's dependency injection makes it easy to inject all available
 * strategies into a Map, allowing runtime strategy selection by name.</p>
 * 
 * @since 1.0
 */
@Service
public class PaymentProcessor {
    
    private static final Logger log = LoggerFactory.getLogger(PaymentProcessor.class);
    
    /**
     * Map of all available payment strategies, injected by Spring.
     * The map key is the bean name (e.g., "creditCard", "paypal").
     */
    private final Map<String, PaymentStrategy> strategies;
    
    /**
     * Constructor injection of all PaymentStrategy beans.
     * 
     * <p>Spring automatically creates a Map of all beans implementing
     * PaymentStrategy, keyed by their bean names.</p>
     */
    public PaymentProcessor(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
        log.info("Payment processor initialized with {} strategies: {}", 
                 strategies.size(), strategies.keySet());
    }
    
    /**
     * Processes a payment using the specified strategy.
     * 
     * <h3>Strategy Selection:</h3>
     * <p>The beauty of the Strategy pattern is that the client can choose
     * the algorithm at runtime without knowing the implementation details.</p>
     * 
     * @param strategyName The name of the strategy to use
     * @param amount The amount to pay
     * @return true if payment was successful
     */
    public boolean processPayment(String strategyName, BigDecimal amount) {
        PaymentStrategy strategy = strategies.get(strategyName);
        
        if (strategy == null) {
            log.error("Unknown payment strategy: {}", strategyName);
            log.info("Available strategies: {}", strategies.keySet());
            return false;
        }
        
        log.info("Selected payment strategy: {}", strategy.getStrategyName());
        
        if (!strategy.validate(amount)) {
            log.error("Payment validation failed for ${} using {}", 
                     amount, strategy.getStrategyName());
            return false;
        }
        
        return strategy.pay(amount);
    }
    
    /**
     * Gets all available strategy names.
     * 
     * @return Set of strategy names
     */
    public java.util.Set<String> getAvailableStrategies() {
        return strategies.keySet();
    }
}
