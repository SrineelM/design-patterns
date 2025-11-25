package com.example.patterns.behavioral.state;

import com.example.patterns.common.DesignPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 * Spring State Machine configuration for order processing.
 * 
 * <p>This configuration defines:</p>
 * <ul>
 *   <li>All possible states</li>
 *   <li>Initial and end states</li>
 *   <li>Valid transitions between states</li>
 *   <li>Events that trigger transitions</li>
 * </ul>
 * 
 * <h3>Configuration Structure:</h3>
 * <pre>
 * States: NEW, PAID, SHIPPED, DELIVERED, CANCELLED
 * Initial: NEW
 * Transitions:
 *   NEW + PAY → PAID
 *   PAID + SHIP → SHIPPED
 *   SHIPPED + DELIVER → DELIVERED
 *   ANY + CANCEL → CANCELLED (except DELIVERED, CANCELLED)
 * </pre>
 * 
 * @since 1.0
 */
@Configuration
@EnableStateMachine
@DesignPattern(
    name = "State (Spring State Machine)",
    category = "Behavioral",
    description = "Framework-managed state machine with transitions and guards"
)
public class StateMachineOrderConfig 
        extends EnumStateMachineConfigurerAdapter<StateMachineOrderStates, StateMachineOrderEvents> {
    
    private static final Logger log = LoggerFactory.getLogger(StateMachineOrderConfig.class);
    
    /**
     * Configures the states in the state machine.
     * 
     * <p>Defines initial state and all possible states.</p>
     */
    @Override
    public void configure(StateMachineStateConfigurer<StateMachineOrderStates, StateMachineOrderEvents> states)
            throws Exception {
        states
            .withStates()
                .initial(StateMachineOrderStates.NEW)
                .end(StateMachineOrderStates.DELIVERED)
                .end(StateMachineOrderStates.CANCELLED)
                .states(java.util.EnumSet.allOf(StateMachineOrderStates.class));
    }
    
    /**
     * Configures the transitions between states.
     * 
     * <p>Defines which events cause which state transitions.</p>
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<StateMachineOrderStates, StateMachineOrderEvents> transitions)
            throws Exception {
        transitions
            // NEW → PAID on PAY event
            .withExternal()
                .source(StateMachineOrderStates.NEW)
                .target(StateMachineOrderStates.PAID)
                .event(StateMachineOrderEvents.PAY)
                .and()
            
            // PAID → SHIPPED on SHIP event
            .withExternal()
                .source(StateMachineOrderStates.PAID)
                .target(StateMachineOrderStates.SHIPPED)
                .event(StateMachineOrderEvents.SHIP)
                .and()
            
            // SHIPPED → DELIVERED on DELIVER event
            .withExternal()
                .source(StateMachineOrderStates.SHIPPED)
                .target(StateMachineOrderStates.DELIVERED)
                .event(StateMachineOrderEvents.DELIVER)
                .and()
            
            // NEW → CANCELLED on CANCEL event
            .withExternal()
                .source(StateMachineOrderStates.NEW)
                .target(StateMachineOrderStates.CANCELLED)
                .event(StateMachineOrderEvents.CANCEL)
                .and()
            
            // PAID → CANCELLED on CANCEL event
            .withExternal()
                .source(StateMachineOrderStates.PAID)
                .target(StateMachineOrderStates.CANCELLED)
                .event(StateMachineOrderEvents.CANCEL)
                .and()
            
            // SHIPPED → CANCELLED on CANCEL event (in real system, might not allow this)
            .withExternal()
                .source(StateMachineOrderStates.SHIPPED)
                .target(StateMachineOrderStates.CANCELLED)
                .event(StateMachineOrderEvents.CANCEL);
    }
    
    /**
     * Configures global state machine settings.
     * 
     * <p>Adds a listener to log all state transitions.</p>
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<StateMachineOrderStates, StateMachineOrderEvents> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(stateMachineListener());
    }
    
    /**
     * Creates a listener bean that logs state transitions.
     * 
     * @return The state machine listener
     */
    @Bean
    public StateMachineListenerAdapter<StateMachineOrderStates, StateMachineOrderEvents> stateMachineListener() {
        return new StateMachineListenerAdapter<StateMachineOrderStates, StateMachineOrderEvents>() {
            @Override
            public void stateChanged(State<StateMachineOrderStates, StateMachineOrderEvents> from, 
                                    State<StateMachineOrderStates, StateMachineOrderEvents> to) {
                if (from != null) {
                    log.info("State transition: {} → {}", 
                             from.getId(), to.getId());
                } else {
                    log.info("Initial state: {}", to.getId());
                }
            }
        };
    }
}
