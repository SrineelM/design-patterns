package com.example.patterns.behavioral.state;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for State pattern (Vanilla Implementation).
 */
@DisplayName("State Pattern - Vanilla Implementation Tests")
class VanillaStatePatternTest {
    
    private VanillaOrderContext orderContext;
    
    @BeforeEach
    void setUp() {
        orderContext = new VanillaOrderContext("TEST-ORDER-001");
    }
    
    @Nested
    @DisplayName("Order Context Initialization Tests")
    class OrderContextInitializationTests {
        
        @Test
        @DisplayName("Should initialize order in NEW state")
        void shouldInitializeOrderInNewState() {
            // Assert
            assertThat(orderContext.getCurrentStateName()).isEqualTo("NEW");
        }
        
        @Test
        @DisplayName("Should create order with unique ID")
        void shouldCreateOrderWithUniqueId() {
            // Act
            var order1 = new VanillaOrderContext();
            var order2 = new VanillaOrderContext();
            
            // Assert
            assertThat(order1.getOrderId()).isNotEqualTo(order2.getOrderId());
        }
    }
    
    @Nested
    @DisplayName("State Transition Tests")
    class StateTransitionTests {
        
        @Test
        @DisplayName("Should transition from NEW to PAID on pay()")
        void shouldTransitionFromNewToPaid() {
            // Act
            orderContext.pay();
            
            // Assert
            assertThat(orderContext.getCurrentStateName()).isEqualTo("PAID");
        }
        
        @Test
        @DisplayName("Should transition from PAID to SHIPPED on ship()")
        void shouldTransitionFromPaidToShipped() {
            // Arrange
            orderContext.pay();
            
            // Act
            orderContext.ship();
            
            // Assert
            assertThat(orderContext.getCurrentStateName()).isEqualTo("SHIPPED");
        }
        
        @Test
        @DisplayName("Should transition from SHIPPED to DELIVERED on deliver()")
        void shouldTransitionFromShippedToDelivered() {
            // Arrange
            orderContext.pay();
            orderContext.ship();
            
            // Act
            orderContext.deliver();
            
            // Assert
            assertThat(orderContext.getCurrentStateName()).isEqualTo("DELIVERED");
        }
        
        @Test
        @DisplayName("Should transition to CANCELLED from any state")
        void shouldTransitionToCancelledFromAnyState() {
            // Assert: NEW -> CANCELLED
            VanillaOrderContext newOrder = new VanillaOrderContext();
            newOrder.cancel();
            assertThat(newOrder.getCurrentStateName()).isEqualTo("CANCELLED");
            
            // Assert: PAID -> CANCELLED
            VanillaOrderContext paidOrder = new VanillaOrderContext();
            paidOrder.pay();
            paidOrder.cancel();
            assertThat(paidOrder.getCurrentStateName()).isEqualTo("CANCELLED");
            
            // Assert: SHIPPED -> CANCELLED
            VanillaOrderContext shippedOrder = new VanillaOrderContext();
            shippedOrder.pay();
            shippedOrder.ship();
            shippedOrder.cancel();
            assertThat(shippedOrder.getCurrentStateName()).isEqualTo("CANCELLED");
        }
    }
    
    @Nested
    @DisplayName("Invalid State Transition Tests")
    class InvalidStateTransitionTests {
        
        @Test
        @DisplayName("Should not ship order in NEW state")
        void shouldNotShipOrderInNewState() {
            // Arrange & Act
            orderContext.ship();
            
            // Assert - should still be in NEW state
            assertThat(orderContext.getCurrentStateName()).isEqualTo("NEW");
        }
        
        @Test
        @DisplayName("Should not deliver order in PAID state")
        void shouldNotDeliverOrderInPaidState() {
            // Arrange
            orderContext.pay();
            
            // Act
            orderContext.deliver();
            
            // Assert - should still be in PAID state
            assertThat(orderContext.getCurrentStateName()).isEqualTo("PAID");
        }
        
        @Test
        @DisplayName("Should not pay already paid order")
        void shouldNotPayAlreadyPaidOrder() {
            // Arrange
            orderContext.pay();
            
            // Act
            orderContext.pay();
            
            // Assert - should still be in PAID state
            assertThat(orderContext.getCurrentStateName()).isEqualTo("PAID");
        }
        
        @Test
        @DisplayName("Should not allow actions on DELIVERED order")
        void shouldNotAllowActionsOnDeliveredOrder() {
            // Arrange
            orderContext.pay();
            orderContext.ship();
            orderContext.deliver();
            
            // Act & Assert - try operations that should be ignored
            assertThatCode(() -> {
                orderContext.pay();
                orderContext.ship();
                orderContext.deliver();
                orderContext.cancel();
            }).doesNotThrowAnyException();
            
            // Should still be DELIVERED
            assertThat(orderContext.getCurrentStateName()).isEqualTo("DELIVERED");
        }
        
        @Test
        @DisplayName("Should not allow operations on CANCELLED order")
        void shouldNotAllowOperationsOnCancelledOrder() {
            // Arrange
            orderContext.cancel();
            
            // Act & Assert - try operations that should be ignored
            assertThatCode(() -> {
                orderContext.pay();
                orderContext.ship();
                orderContext.deliver();
            }).doesNotThrowAnyException();
            
            // Should still be CANCELLED
            assertThat(orderContext.getCurrentStateName()).isEqualTo("CANCELLED");
        }
    }
    
    @Nested
    @DisplayName("Complete Order Flow Tests")
    class CompleteOrderFlowTests {
        
        @Test
        @DisplayName("Should complete full order workflow: NEW → PAID → SHIPPED → DELIVERED")
        void shouldCompleteFullOrderWorkflow() {
            // Act & Assert each step
            assertThat(orderContext.getCurrentStateName()).isEqualTo("NEW");
            
            orderContext.pay();
            assertThat(orderContext.getCurrentStateName()).isEqualTo("PAID");
            
            orderContext.ship();
            assertThat(orderContext.getCurrentStateName()).isEqualTo("SHIPPED");
            
            orderContext.deliver();
            assertThat(orderContext.getCurrentStateName()).isEqualTo("DELIVERED");
        }
    }
    
    @Nested
    @DisplayName("State Enum Tests")
    class StateEnumTests {
        
        @Test
        @DisplayName("Should have all required states")
        void shouldHaveAllRequiredStates() {
            // Assert
            var states = VanillaOrderStateEnum.values();
            var stateNames = new java.util.HashSet<String>();
            for (var state : states) {
                stateNames.add(state.getStateName());
            }
            
            assertThat(stateNames).contains("NEW", "PAID", "SHIPPED", "DELIVERED", "CANCELLED");
        }
        
        @Test
        @DisplayName("Should implement VanillaOrderState interface")
        void shouldImplementOrderStateInterface() {
            // Assert
            for (var state : VanillaOrderStateEnum.values()) {
                assertThat(state).isInstanceOf(VanillaOrderState.class);
            }
        }
    }
}
