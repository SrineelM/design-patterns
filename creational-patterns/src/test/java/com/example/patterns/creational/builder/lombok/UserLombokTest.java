package com.example.patterns.creational.builder.lombok;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for UserLombok Builder pattern implementation.
 */
@DisplayName("Builder Pattern (Lombok) - UserLombok Tests")
class UserLombokTest {
    
    @Nested
    @DisplayName("Builder Construction Tests")
    class BuilderConstructionTests {
        
        @Test
        @DisplayName("Should build user with all fields")
        void shouldBuildUser_withAllFields() {
            // Act
            var user = UserLombok.builder()
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .phone("+1-555-0123")
                .age(30)
                .active(true)
                .build();
            
            // Assert
            assertThat(user).isNotNull();
            assertThat(user.getEmail()).isEqualTo("john@example.com");
            assertThat(user.getFirstName()).isEqualTo("John");
            assertThat(user.getLastName()).isEqualTo("Doe");
            assertThat(user.getPhone()).isEqualTo("+1-555-0123");
            assertThat(user.getAge()).isEqualTo(30);
            assertThat(user.isActive()).isTrue();
        }
        
        @Test
        @DisplayName("Should build user with only required field")
        void shouldBuildUser_withOnlyRequiredField() {
            // Act
            var user = UserLombok.builder()
                .email("jane@example.com")
                .build();
            
            // Assert
            assertThat(user).isNotNull();
            assertThat(user.getEmail()).isEqualTo("jane@example.com");
            assertThat(user.getFirstName()).isNull();
            assertThat(user.getLastName()).isNull();
        }
        
        @Test
        @DisplayName("Should throw exception when required email is null")
        void shouldThrowException_whenRequiredEmailIsNull() {
            // Act & Assert
            assertThatThrownBy(() -> 
                UserLombok.builder()
                    .firstName("Test")
                    .build()
            ).isInstanceOf(NullPointerException.class);
        }
    }
    
    @Nested
    @DisplayName("Immutability Tests")
    class ImmutabilityTests {
        
        @Test
        @DisplayName("Should create immutable object")
        void shouldCreateImmutableObject() {
            // Arrange
            var user = UserLombok.builder()
                .email("test@example.com")
                .firstName("Test")
                .build();
            
            // Act - try to get fields
            String email = user.getEmail();
            String firstName = user.getFirstName();
            
            // Assert - fields are final, no setters available
            assertThat(email).isEqualTo("test@example.com");
            assertThat(firstName).isEqualTo("Test");
        }
    }
    
    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {
        
        @Test
        @DisplayName("Should generate readable toString")
        void shouldGenerateReadableToString() {
            // Arrange
            var user = UserLombok.builder()
                .email("test@example.com")
                .firstName("Test")
                .age(25)
                .build();
            
            // Act
            String toString = user.toString();
            
            // Assert
            assertThat(toString).contains("test@example.com");
            assertThat(toString).contains("Test");
            assertThat(toString).contains("25");
        }
    }
}
