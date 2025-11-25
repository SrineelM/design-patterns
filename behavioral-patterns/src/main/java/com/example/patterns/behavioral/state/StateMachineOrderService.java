package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * Service that uses Spring State Machine for order processing.
 * 
 * <p>This demonstrates how to use the state machine in your application code.</p>
 * 
 * <h3>Usage Example:</h3>
 * <pre>
 * orderService.processPayment();  // Sends PAY event
 * orderService.shipOrder();       // Sends SHIP event
 * orderService.deliverOrder();    // Sends DELIVER event
 * </pre>
 * 
 * <p>The state machine handles:</p>
 * <ul>
 *   <li>Valid state transitions</li>
 *   <li>Invalid transition prevention</li>
 *   <li>State change notifications</li>
 * </ul>
 * 
 * @since 1.0
 */
@Service
public class StateMachineOrderService {
    
    private static final Logger log = LoggerFactory.getLogger(StateMachineOrderService.class);
    
    private final StateMachine<StateMachineOrderStates, StateMachineOrderEvents> stateMachine;
    
    public StateMachineOrderService(StateMachine<StateMachineOrderStates, StateMachineOrderEvents> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.startReactively().subscribe();
        log.info("Order state machine initialized in state: {}", 
                 stateMachine.getState().getId());
    }
    
    /**
     * Processes payment for the order.
     * 
     * <p>Sends PAY event to the state machine.</p>
     * 
     * @return true if transition was successful
     */
    public boolean processPayment() {
        log.info("Processing payment...");
        boolean result = stateMachine.sendEvent(StateMachineOrderEvents.PAY);
        
        if (result) {
            log.info("Payment processed successfully");
        } else {
            log.warn("Payment processing failed - invalid state transition");
        }
        
        return result;
    }
    
    /**
     * Ships the order.
     * 
     * <p>Sends SHIP event to the state machine.</p>
     * 
     * @return true if transition was successful
     */
    public boolean shipOrder() {
        log.info("Shipping order...");
        boolean result = stateMachine.sendEvent(StateMachineOrderEvents.SHIP);
        
        if (result) {
            log.info("Order shipped successfully");
        } else {
            log.warn("Shipping failed - invalid state transition");
        }
        
        return result;
    }
    
    /**
     * Delivers the order.
     * 
     * <p>Sends DELIVER event to the state machine.</p>
     * 
     * @return true if transition was successful
     */
    public boolean deliverOrder() {
        log.info("Delivering order...");
        boolean result = stateMachine.sendEvent(StateMachineOrderEvents.DELIVER);
        
        if (result) {
            log.info("Order delivered successfully");
        } else {
            log.warn("Delivery failed - invalid state transition");
        }
        
        return result;
    }
    
    /**
     * Cancels the order.
     * 
     * <p>Sends CANCEL event to the state machine.</p>
     * 
     * @return true if transition was successful
     */
    public boolean cancelOrder() {
        log.info("Cancelling order...");
        boolean result = stateMachine.sendEvent(StateMachineOrderEvents.CANCEL);
        
        if (result) {
            log.info("Order cancelled successfully");
        } else {
            log.warn("Cancellation failed - invalid state transition");
        }
        
        return result;
    }
    
    /**
     * Gets the current state of the order.
     * 
     * @return The current state
     */
    public StateMachineOrderStates getCurrentState() {
        return stateMachine.getState().getId();
    }
    
    /**
     * Checks if the state machine is in a specific state.
     * 
     * @param state The state to check
     * @return true if in the specified state
     */
    public boolean isInState(StateMachineOrderStates state) {
        return getCurrentState() == state;
    }
}
