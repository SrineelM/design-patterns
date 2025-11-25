package com.example.patterns.behavioral.strategy;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.math.BigDecimal;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Strategy pattern implementation.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Strategy Pattern Tests")
class PaymentProcessorTest {
    
    @Mock
    private PaymentStrategy mockStrategy;
    
    @Nested
    @DisplayName("Strategy Selection Tests")
    class StrategySelectionTests {
        
        @Test
        @DisplayName("Should process payment with valid strategy")
        void shouldProcessPayment_withValidStrategy() {
            // Arrange
            when(mockStrategy.validate(any(BigDecimal.class))).thenReturn(true);
            when(mockStrategy.pay(any(BigDecimal.class))).thenReturn(true);
            when(mockStrategy.getStrategyName()).thenReturn("Mock Strategy");
            
            Map<String, PaymentStrategy> strategies = Map.of("mock", mockStrategy);
            PaymentProcessor processor = new PaymentProcessor(strategies);
            BigDecimal amount = BigDecimal.valueOf(100.00);
            
            // Act
            boolean result = processor.processPayment("mock", amount);
            
            // Assert
            assertThat(result).isTrue();
            verify(mockStrategy).validate(amount);
            verify(mockStrategy).pay(amount);
        }
        
        @Test
        @DisplayName("Should return false for unknown strategy")
        void shouldReturnFalse_forUnknownStrategy() {
            // Arrange
            Map<String, PaymentStrategy> strategies = Map.of("mock", mockStrategy);
            PaymentProcessor processor = new PaymentProcessor(strategies);
            
            // Act
            boolean result = processor.processPayment("unknown", BigDecimal.valueOf(100.00));
            
            // Assert
            assertThat(result).isFalse();
            verifyNoInteractions(mockStrategy);
        }
        
        @Test
        @DisplayName("Should return false when validation fails")
        void shouldReturnFalse_whenValidationFails() {
            // Arrange
            when(mockStrategy.validate(any(BigDecimal.class))).thenReturn(false);
            when(mockStrategy.getStrategyName()).thenReturn("Mock Strategy");
            
            Map<String, PaymentStrategy> strategies = Map.of("mock", mockStrategy);
            PaymentProcessor processor = new PaymentProcessor(strategies);
            BigDecimal amount = BigDecimal.valueOf(-100.00);
            
            // Act
            boolean result = processor.processPayment("mock", amount);
            
            // Assert
            assertThat(result).isFalse();
            verify(mockStrategy).validate(amount);
            verify(mockStrategy, never()).pay(any(BigDecimal.class));
        }
    }
    
    @Nested
    @DisplayName("Available Strategies Tests")
    class AvailableStrategiesTests {
        
        @Test
        @DisplayName("Should return all available strategy names")
        void shouldReturnAllAvailableStrategies() {
            // Arrange
            PaymentStrategy strategy1 = mock(PaymentStrategy.class);
            PaymentStrategy strategy2 = mock(PaymentStrategy.class);
            Map<String, PaymentStrategy> strategies = Map.of(
                "creditCard", strategy1,
                "paypal", strategy2
            );
            PaymentProcessor processor = new PaymentProcessor(strategies);
            
            // Act
            var availableStrategies = processor.getAvailableStrategies();
            
            // Assert
            assertThat(availableStrategies)
                .hasSize(2)
                .containsExactlyInAnyOrder("creditCard", "paypal");
        }
    }
    
    @Nested
    @DisplayName("Concrete Strategy Tests")
    class ConcreteStrategyTests {
        
        @Test
        @DisplayName("Credit card strategy should process valid payment")
        void creditCardStrategy_shouldProcessValidPayment() {
            // Arrange
            PaymentStrategy strategy = new CreditCardPaymentStrategy();
            BigDecimal amount = BigDecimal.valueOf(100.00);
            
            // Act
            boolean isValid = strategy.validate(amount);
            boolean result = strategy.pay(amount);
            
            // Assert
            assertThat(isValid).isTrue();
            assertThat(result).isTrue();
            assertThat(strategy.getStrategyName()).isEqualTo("Credit Card");
        }
        
        @Test
        @DisplayName("Credit card strategy should reject negative amount")
        void creditCardStrategy_shouldRejectNegativeAmount() {
            // Arrange
            PaymentStrategy strategy = new CreditCardPaymentStrategy();
            BigDecimal amount = BigDecimal.valueOf(-100.00);
            
            // Act
            boolean isValid = strategy.validate(amount);
            
            // Assert
            assertThat(isValid).isFalse();
        }
        
        @Test
        @DisplayName("PayPal strategy should process valid payment")
        void payPalStrategy_shouldProcessValidPayment() {
            // Arrange
            PaymentStrategy strategy = new PayPalPaymentStrategy();
            BigDecimal amount = BigDecimal.valueOf(100.00);
            
            // Act
            boolean isValid = strategy.validate(amount);
            boolean result = strategy.pay(amount);
            
            // Assert
            assertThat(isValid).isTrue();
            assertThat(result).isTrue();
            assertThat(strategy.getStrategyName()).isEqualTo("PayPal");
        }
    }
}
