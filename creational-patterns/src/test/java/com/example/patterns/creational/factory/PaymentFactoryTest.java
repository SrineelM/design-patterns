package com.example.patterns.creational.factory;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for PaymentFactory and Payment implementations.
 * 
 * Tests the Factory Method pattern by verifying:
 * - Factory creates correct payment types
 * - Each payment type processes correctly
 * - Invalid inputs are handled properly
 * 
 * @author Design Patterns Team
 */
@DisplayName("Factory Method Pattern - Payment Factory Tests")
class PaymentFactoryTest {
    
    @Nested
    @DisplayName("Factory Creation Tests")
    class FactoryCreationTests {
        
        @ParameterizedTest
        @EnumSource(PaymentMethod.class)
        @DisplayName("Should create correct payment type for each payment method")
        void shouldCreateCorrectPaymentType_forEachPaymentMethod(PaymentMethod method) {
            // Act
            Payment payment = PaymentFactory.createPayment(method);
            
            // Assert
            assertThat(payment).isNotNull();
            assertThat(payment.getPaymentMethodName()).isNotEmpty();
        }
        
        @Test
        @DisplayName("Should throw exception when payment method is null")
        void shouldThrowException_whenPaymentMethodIsNull() {
            // Act & Assert
            assertThatThrownBy(() -> PaymentFactory.createPayment(null))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Should create credit card payment")
        void shouldCreateCreditCardPayment() {
            // Act
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
            
            // Assert
            assertThat(payment).isInstanceOf(CreditCardPayment.class);
        }
        
        @Test
        @DisplayName("Should create PayPal payment")
        void shouldCreatePayPalPayment() {
            // Act
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_PAYPAL);
            
            // Assert
            assertThat(payment).isInstanceOf(PayPalPayment.class);
        }
        
        @Test
        @DisplayName("Should create Google Pay payment")
        void shouldCreateGooglePayPayment() {
            // Act
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_GOOGLE_PAY);
            
            // Assert
            assertThat(payment).isInstanceOf(GooglePayPayment.class);
        }
    }
    
    @Nested
    @DisplayName("Payment Processing Tests")
    class PaymentProcessingTests {
        
        @Test
        @DisplayName("Should process credit card payment successfully")
        void shouldProcessCreditCardPayment_successfully() {
            // Arrange
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
            BigDecimal amount = BigDecimal.valueOf(100.00);
            String orderId = "ORDER-123";
            
            // Act
            PaymentResult result = payment.process(amount, orderId);
            
            // Assert
            assertThat(result).isNotNull();
            assertThat(result.isSuccessful()).isTrue();
            assertThat(result.getTransactionId()).isNotEmpty();
        }
        
        @Test
        @DisplayName("Should process PayPal payment successfully")
        void shouldProcessPayPalPayment_successfully() {
            // Arrange
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_PAYPAL);
            BigDecimal amount = BigDecimal.valueOf(250.00);
            String orderId = "ORDER-456";
            
            // Act
            PaymentResult result = payment.process(amount, orderId);
            
            // Assert
            assertThat(result).isNotNull();
            assertThat(result.isSuccessful()).isTrue();
        }
        
        @Test
        @DisplayName("Should fail when amount exceeds limit")
        void shouldFail_whenAmountExceedsLimit() {
            // Arrange
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_GOOGLE_PAY);
            BigDecimal amount = BigDecimal.valueOf(10000.00); // Exceeds Google Pay limit
            
            // Act
            PaymentResult result = payment.process(amount, "ORDER-789");
            
            // Assert
            assertThat(result).isNotNull();
            assertThat(result.isSuccessful()).isFalse();
        }
        
        @Test
        @DisplayName("Should fail when amount is negative")
        void shouldFail_whenAmountIsNegative() {
            // Arrange
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
            BigDecimal amount = BigDecimal.valueOf(-50.00);
            
            // Act & Assert
            assertThatThrownBy(() -> payment.process(amount, "ORDER-999"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Should fail when order ID is null")
        void shouldFail_whenOrderIdIsNull() {
            // Arrange
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_PAYPAL);
            
            // Act & Assert
            assertThatThrownBy(() -> payment.process(BigDecimal.valueOf(100.00), null))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    @Nested
    @DisplayName("Payment Method Properties Tests")
    class PaymentMethodPropertiesTests {
        
        @Test
        @DisplayName("Should have correct display names")
        void shouldHaveCorrectDisplayNames() {
            assertThat(PaymentMethod.PAY_WITH_CREDIT_CARD.getDisplayName()).isEqualTo("Credit Card");
            assertThat(PaymentMethod.PAY_WITH_PAYPAL.getDisplayName()).isEqualTo("PayPal");
            assertThat(PaymentMethod.PAY_WITH_GOOGLE_PAY.getDisplayName()).isEqualTo("Google Pay");
        }
        
        @Test
        @DisplayName("Should have correct processor IDs")
        void shouldHaveCorrectProcessorIds() {
            assertThat(PaymentMethod.PAY_WITH_CREDIT_CARD.getProcessorId()).isEqualTo("credit-card-processor");
            assertThat(PaymentMethod.PAY_WITH_PAYPAL.getProcessorId()).isEqualTo("paypal-gateway");
            assertThat(PaymentMethod.PAY_WITH_GOOGLE_PAY.getProcessorId()).isEqualTo("google-pay-service");
        }
    }
    
    @Nested
    @DisplayName("Refund Tests")
    class RefundTests {
        
        @Test
        @DisplayName("Should refund credit card payment")
        void shouldRefundCreditCardPayment() {
            // Arrange
            Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
            String transactionId = "TXN-123";
            BigDecimal amount = BigDecimal.valueOf(100.00);
            
            // Act
            PaymentResult result = payment.refund(transactionId, amount);
            
            // Assert
            assertThat(result).isNotNull();
            assertThat(result.isSuccessful()).isTrue();
        }
    }
}
