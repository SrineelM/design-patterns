package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Order context that maintains current state (Vanilla Implementation).
 * 
 * <p>This is the Context in the State pattern. It:</p>
 * <ul>
 *   <li>Maintains a reference to the current state</li>
 *   <li>Delegates behavior to the current state</li>
 *   <li>Allows state transitions</li>
 * </ul>
 * 
 * <h3>How State Pattern Works:</h3>
 * <pre>
 * Client calls: order.pay()
 *     ↓
 * Context delegates to: currentState.pay(this)
 *     ↓
 * State executes behavior and may change context's state
 * </pre>
 * 
 * @since 1.0
 */
@Component
public class VanillaOrderContext {
    
    private static final Logger log = LoggerFactory.getLogger(VanillaOrderContext.class);
    
    private VanillaOrderState currentState;
    private final String orderId;
    
    /**
     * Creates a new order context.
     * 
     * @param orderId The order ID
     */
    public VanillaOrderContext(String orderId) {
        this.orderId = orderId;
        this.currentState = VanillaOrderStateEnum.NEW;
        log.info("Created new order #{} in state: {}", orderId, currentState.getStateName());
    }
    
    /**
     * Default constructor for Spring.
     */
    public VanillaOrderContext() {
        this("ORDER-" + System.currentTimeMillis() % 10000);
    }
    
    /**
     * Processes payment for this order.
     * Delegates to current state.
     */
    public void pay() {
        log.info("[{}] Processing pay() request in state: {}", orderId, currentState.getStateName());
        currentState.pay(this);
    }
    
    /**
     * Ships this order.
     * Delegates to current state.
     */
    public void ship() {
        log.info("[{}] Processing ship() request in state: {}", orderId, currentState.getStateName());
        currentState.ship(this);
    }
    
    /**
     * Delivers this order.
     * Delegates to current state.
     */
    public void deliver() {
        log.info("[{}] Processing deliver() request in state: {}", orderId, currentState.getStateName());
        currentState.deliver(this);
    }
    
    /**
     * Cancels this order.
     * Delegates to current state.
     */
    public void cancel() {
        log.info("[{}] Processing cancel() request in state: {}", orderId, currentState.getStateName());
        currentState.cancel(this);
    }
    
    /**
     * Sets the current state.
     * 
     * <p>Package-private so only states can change the state.</p>
     * 
     * @param state The new state
     */
    void setState(VanillaOrderState state) {
        VanillaOrderState oldState = this.currentState;
        this.currentState = state;
        log.info("[{}] State transition: {} → {}", orderId, oldState.getStateName(), state.getStateName());
    }
    
    /**
     * Gets the current state name.
     * 
     * @return The current state name
     */
    public String getCurrentStateName() {
        return currentState.getStateName();
    }
    
    /**
     * Gets the order ID.
     * 
     * @return The order ID
     */
    public String getOrderId() {
        return orderId;
    }
}
