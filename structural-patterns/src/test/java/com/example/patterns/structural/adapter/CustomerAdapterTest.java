package com.example.patterns.structural.adapter;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Adapter pattern implementation.
 */
@DisplayName("Adapter Pattern Tests")
class CustomerAdapterTest {
    
    @Nested
    @DisplayName("Adapter Creation Tests")
    class AdapterCreationTests {
        
        @Test
        @DisplayName("Should create adapter with valid legacy customer")
        void shouldCreateAdapter_withValidLegacyCustomer() {
            // Arrange
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                12345,
                "John",
                "Doe",
                "john.doe@example.com",
                "123 Main St"
            );
            
            // Act
            Customer adapter = new CustomerAdapter(legacyCustomer);
            
            // Assert
            assertThat(adapter).isNotNull();
        }
        
        @Test
        @DisplayName("Should throw exception when legacy customer is null")
        void shouldThrowException_whenLegacyCustomerIsNull() {
            // Act & Assert
            assertThatThrownBy(() -> new CustomerAdapter(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Legacy customer cannot be null");
        }
    }
    
    @Nested
    @DisplayName("ID Adaptation Tests")
    class IdAdaptationTests {
        
        @Test
        @DisplayName("Should adapt customer number to ID format")
        void shouldAdaptCustomerNumber_toIdFormat() {
            // Arrange
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                12345,
                "John",
                "Doe",
                "john.doe@example.com",
                "123 Main St"
            );
            Customer adapter = new CustomerAdapter(legacyCustomer);
            
            // Act
            String id = adapter.getId();
            
            // Assert
            assertThat(id).isEqualTo("CUST-012345");
        }
        
        @Test
        @DisplayName("Should pad small customer numbers with zeros")
        void shouldPadSmallCustomerNumbers_withZeros() {
            // Arrange
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                42,
                "Jane",
                "Smith",
                "jane@example.com",
                "555-5678",
                "456 Elm St",
                "Springfield",
                "12346"
            );
            Customer adapter = new CustomerAdapter(legacyCustomer);
            
            // Act
            String id = adapter.getId();
            
            // Assert
            assertThat(id).isEqualTo("CUST-000042");
        }
    }
    
    @Nested
    @DisplayName("Name Adaptation Tests")
    class NameAdaptationTests {
        
        @Test
        @DisplayName("Should combine first and last name")
        void shouldCombineFirstAndLastName() {
            // Arrange
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                12345,
                "John",
                "Doe",
                "john.doe@example.com",
                "555-1234",
                "123 Main St",
                "Springfield",
                "12345"
            );
            Customer adapter = new CustomerAdapter(legacyCustomer);
            
            // Act
            String fullName = adapter.getFullName();
            
            // Assert
            assertThat(fullName).isEqualTo("John Doe");
        }
    }
    
    @Nested
    @DisplayName("Email and Address Adaptation Tests")
    class EmailAndAddressAdaptationTests {
        
        @Test
        @DisplayName("Should pass through email unchanged")
        void shouldPassThroughEmail_unchanged() {
            // Arrange
            String expectedEmail = "john.doe@example.com";
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                12345,
                "John",
                "Doe",
                expectedEmail,
                "555-1234",
                "123 Main St",
                "Springfield",
                "12345"
            );
            Customer adapter = new CustomerAdapter(legacyCustomer);
            
            // Act
            String email = adapter.getEmail();
            
            // Assert
            assertThat(email).isEqualTo(expectedEmail);
        }
        
        @Test
        @DisplayName("Should pass through address unchanged")
        void shouldPassThroughAddress_unchanged() {
            // Arrange
            String expectedAddress = "123 Main St";
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                12345,
                "John",
                "Doe",
                "john.doe@example.com",
                "555-1234",
                expectedAddress,
                "Springfield",
                "12345"
            );
            Customer adapter = new CustomerAdapter(legacyCustomer);
            
            // Act
            String address = adapter.getAddress();
            
            // Assert
            assertThat(address).isEqualTo(expectedAddress);
        }
    }
    
    @Nested
    @DisplayName("Service Integration Tests")
    class ServiceIntegrationTests {
        
        @Test
        @DisplayName("Should work seamlessly with CustomerService")
        void shouldWorkSeamlessly_withCustomerService() {
            // Arrange
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                12345,
                "John",
                "Doe",
                "john.doe@example.com",
                "555-1234",
                "123 Main St",
                "Springfield",
                "12345"
            );
            Customer adapter = new CustomerAdapter(legacyCustomer);
            CustomerService service = new CustomerService();
            
            // Act
            String result = service.processOrder(adapter, 99.99);
            
            // Assert
            assertThat(result)
                .contains("John Doe")
                .contains("CUST-012345")
                .contains("99.99")
                .contains("john.doe@example.com")
                .contains("123 Main St");
        }
        
        @Test
        @DisplayName("Modern and adapted customers should work together")
        void modernAndAdaptedCustomers_shouldWorkTogether() {
            // Arrange
            Customer modernCustomer = new ModernCustomer(
                "CUST-999999",
                "Alice",
                "Johnson",
                "alice@example.com",
                "789 Oak Ave"
            );
            
            LegacyCustomer legacyCustomer = new LegacyCustomer(
                12345,
                "Bob",
                "Smith",
                "bob@example.com",
                "555-9999",
                "321 Pine St",
                "Springfield",
                "12347"
            );
            Customer adaptedCustomer = new CustomerAdapter(legacyCustomer);
            
            CustomerService service = new CustomerService();
            
            // Act
            String modernResult = service.processOrder(modernCustomer, 100.00);
            String legacyResult = service.processOrder(adaptedCustomer, 150.00);
            
            // Assert
            assertThat(modernResult).contains("Alice Johnson", "CUST-999999");
            assertThat(legacyResult).contains("Bob Smith", "CUST-012345");
        }
    }
}
