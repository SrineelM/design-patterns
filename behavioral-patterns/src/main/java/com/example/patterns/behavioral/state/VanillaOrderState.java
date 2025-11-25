package com.example.patterns.behavioral.state;

import com.example.patterns.common.DesignPattern;

/**
 * State interface for order processing (Vanilla Implementation).
 * 
 * <p>Each state implements this interface to define behavior specific to that state.</p>
 * 
 * @since 1.0
 */
@DesignPattern(
    name = "State",
    category = "Behavioral",
    description = "Changes object behavior when internal state changes"
)
public interface VanillaOrderState {
    
    /**
     * Handles payment for the order.
     * 
     * @param context The order context
     */
    void pay(VanillaOrderContext context);
    
    /**
     * Ships the order.
     * 
     * @param context The order context
     */
    void ship(VanillaOrderContext context);
    
    /**
     * Delivers the order.
     * 
     * @param context The order context
     */
    void deliver(VanillaOrderContext context);
    
    /**
     * Cancels the order.
     * 
     * @param context The order context
     */
    void cancel(VanillaOrderContext context);
    
    /**
     * Gets the state name.
     * 
     * @return The state name
     */
    String getStateName();
}
