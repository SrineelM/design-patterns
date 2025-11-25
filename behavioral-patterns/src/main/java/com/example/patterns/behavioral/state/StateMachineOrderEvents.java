package com.example.patterns.behavioral.state;

/**
 * Order events that trigger state transitions (Spring State Machine).
 * 
 * <p>These events drive the state machine transitions.</p>
 * 
 * @since 1.0
 */
public enum StateMachineOrderEvents {
    PAY,
    SHIP,
    DELIVER,
    CANCEL
}
