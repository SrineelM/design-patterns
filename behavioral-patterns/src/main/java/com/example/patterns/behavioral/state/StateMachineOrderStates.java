package com.example.patterns.behavioral.state;

/**
 * Order states for Spring State Machine.
 * 
 * <p>These are the states in our state machine.</p>
 * 
 * @since 1.0
 */
public enum StateMachineOrderStates {
    NEW,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
