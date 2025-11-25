package com.example.patterns.behavioral.state;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for State pattern (Spring State Machine Implementation).
 */
@SpringBootTest(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
    "logging.level.root=WARN"
})
@DisplayName("State Pattern - Spring State Machine Implementation Tests")
@SuppressWarnings("deprecation")
class StateMachineStatePatternTest {
    
    @Autowired
    private StateMachine<StateMachineOrderStates, StateMachineOrderEvents> stateMachine;
    
    @Autowired
    private StateMachineOrderService orderService;
    
    @BeforeEach
    void setUp() {
        // Ensure state machine is in initial state
        if (!stateMachine.getState().getId().equals(StateMachineOrderStates.NEW)) {
            // Reset by recreating
            stateMachine.stop();
            stateMachine.startReactively().subscribe();
        }
    }
    
    @Nested
    @DisplayName("State Machine Initialization Tests")
    class StateMachineInitializationTests {
        
        @Test
        @DisplayName("Should initialize in NEW state")
        void shouldInitializeInNewState() {
            // Assert
            assertThat(stateMachine.getState().getId()).isEqualTo(StateMachineOrderStates.NEW);
        }
        
        @Test
        @DisplayName("Should be able to send events after initialization")
        void shouldBeSendEventsAfterInitialization() {
            // Act
            boolean result = stateMachine.sendEvent(StateMachineOrderEvents.PAY);
            
            // Assert
            assertThat(result).isTrue();
            assertThat(stateMachine.getState().getId()).isEqualTo(StateMachineOrderStates.PAID);
        }
    }
    
    @Nested
    @DisplayName("Valid State Transition Tests")
    class ValidStateTransitionTests {
        
        @Test
        @DisplayName("Should transition NEW → PAID on PAY event")
        void shouldTransitionNewToPaidOnPayEvent() {
            // Act
            var result = orderService.processPayment();
            
            // Assert
            assertThat(result).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.PAID);
        }
        
        @Test
        @DisplayName("Should transition PAID → SHIPPED on SHIP event")
        void shouldTransitionPaidToShippedOnShipEvent() {
            // Arrange
            orderService.processPayment();
            
            // Act
            var result = orderService.shipOrder();
            
            // Assert
            assertThat(result).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.SHIPPED);
        }
        
        @Test
        @DisplayName("Should transition SHIPPED → DELIVERED on DELIVER event")
        void shouldTransitionShippedToDeliveredOnDeliverEvent() {
            // Arrange
            orderService.processPayment();
            orderService.shipOrder();
            
            // Act
            var result = orderService.deliverOrder();
            
            // Assert
            assertThat(result).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.DELIVERED);
        }
        
        @Test
        @DisplayName("Should transition to CANCELLED from NEW state on CANCEL event")
        void shouldTransitionNewToCancelledOnCancelEvent() {
            // Act
            var result = orderService.cancelOrder();
            
            // Assert
            assertThat(result).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.CANCELLED);
        }
        
        @Test
        @DisplayName("Should transition to CANCELLED from PAID state on CANCEL event")
        void shouldTransitionPaidToCancelledOnCancelEvent() {
            // Arrange
            orderService.processPayment();
            
            // Act
            var result = orderService.cancelOrder();
            
            // Assert
            assertThat(result).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.CANCELLED);
        }
        
        @Test
        @DisplayName("Should transition to CANCELLED from SHIPPED state on CANCEL event")
        void shouldTransitionShippedToCancelledOnCancelEvent() {
            // Arrange
            orderService.processPayment();
            orderService.shipOrder();
            
            // Act
            var result = orderService.cancelOrder();
            
            // Assert
            assertThat(result).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.CANCELLED);
        }
    }
    
    @Nested
    @DisplayName("Invalid State Transition Tests")
    class InvalidStateTransitionTests {
        
        @Test
        @DisplayName("Should reject SHIP event in NEW state")
        void shouldRejectShipEventInNewState() {
            // Act
            var result = stateMachine.sendEvent(StateMachineOrderEvents.SHIP);
            
            // Assert
            assertThat(result).isFalse();
            assertThat(stateMachine.getState().getId()).isEqualTo(StateMachineOrderStates.NEW);
        }
        
        @Test
        @DisplayName("Should reject DELIVER event in PAID state")
        void shouldRejectDeliverEventInPaidState() {
            // Arrange
            orderService.processPayment();
            
            // Act
            var result = orderService.deliverOrder();
            
            // Assert
            assertThat(result).isFalse();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.PAID);
        }
        
        @Test
        @DisplayName("Should reject PAY event in PAID state")
        void shouldRejectPayEventInPaidState() {
            // Arrange
            orderService.processPayment();
            
            // Act
            var result = orderService.processPayment();
            
            // Assert
            assertThat(result).isFalse();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.PAID);
        }
        
        @Test
        @DisplayName("Should reject events on DELIVERED state")
        void shouldRejectEventsOnDeliveredState() {
            // Arrange
            orderService.processPayment();
            orderService.shipOrder();
            orderService.deliverOrder();
            
            // Act
            var payResult = orderService.processPayment();
            var shipResult = orderService.shipOrder();
            var cancelResult = orderService.cancelOrder();
            
            // Assert
            assertThat(payResult).isFalse();
            assertThat(shipResult).isFalse();
            assertThat(cancelResult).isFalse();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.DELIVERED);
        }
        
        @Test
        @DisplayName("Should reject events on CANCELLED state")
        void shouldRejectEventsOnCancelledState() {
            // Arrange
            orderService.cancelOrder();
            
            // Act
            var payResult = orderService.processPayment();
            var shipResult = orderService.shipOrder();
            var deliverResult = orderService.deliverOrder();
            
            // Assert
            assertThat(payResult).isFalse();
            assertThat(shipResult).isFalse();
            assertThat(deliverResult).isFalse();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.CANCELLED);
        }
    }
    
    @Nested
    @DisplayName("State Machine Service Tests")
    class StateMachineServiceTests {
        
        @Test
        @DisplayName("Should provide current state via service")
        void shouldProvideCurrentStateViaService() {
            // Assert
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.NEW);
        }
        
        @Test
        @DisplayName("Should check if in specific state")
        void shouldCheckIfInSpecificState() {
            // Assert
            assertThat(orderService.isInState(StateMachineOrderStates.NEW)).isTrue();
            assertThat(orderService.isInState(StateMachineOrderStates.PAID)).isFalse();
            
            // Arrange
            orderService.processPayment();
            
            // Assert
            assertThat(orderService.isInState(StateMachineOrderStates.PAID)).isTrue();
            assertThat(orderService.isInState(StateMachineOrderStates.NEW)).isFalse();
        }
    }
    
    @Nested
    @DisplayName("Complete Order Flow Tests")
    class CompleteOrderFlowTests {
        
        @Test
        @DisplayName("Should complete full order workflow: NEW → PAID → SHIPPED → DELIVERED")
        void shouldCompleteFullOrderWorkflow() {
            // Act & Assert
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.NEW);
            
            var payResult = orderService.processPayment();
            assertThat(payResult).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.PAID);
            
            var shipResult = orderService.shipOrder();
            assertThat(shipResult).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.SHIPPED);
            
            var deliverResult = orderService.deliverOrder();
            assertThat(deliverResult).isTrue();
            assertThat(orderService.getCurrentState()).isEqualTo(StateMachineOrderStates.DELIVERED);
        }
    }
    
    @Nested
    @DisplayName("Event Enum Tests")
    class EventEnumTests {
        
        @Test
        @DisplayName("Should have all required events")
        void shouldHaveAllRequiredEvents() {
            // Assert
            var events = StateMachineOrderEvents.values();
            assertThat(events).contains(
                StateMachineOrderEvents.PAY,
                StateMachineOrderEvents.SHIP,
                StateMachineOrderEvents.DELIVER,
                StateMachineOrderEvents.CANCEL
            );
        }
    }
}
