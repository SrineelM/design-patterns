package com.example.patterns.creational.singleton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for the DatabaseConnection Singleton pattern.
 * 
 * These tests verify that:
 * - Only one instance exists
 * - The instance is thread-safe
 * - The connection functionality works correctly
 * 
 * @author Design Patterns Team
 */
@DisplayName("Singleton Pattern - DatabaseConnection Tests")
class DatabaseConnectionTest {
    
    @Nested
    @DisplayName("Singleton Instance Tests")
    class SingletonInstanceTests {
        
        @Test
        @DisplayName("Should return the same instance when called multiple times")
        void shouldReturnSameInstance_whenCalledMultipleTimes() {
            // Arrange & Act
            var instance1 = DatabaseConnection.INSTANCE;
            var instance2 = DatabaseConnection.INSTANCE;
            
            // Assert
            assertThat(instance1).isSameAs(instance2);
        }
        
        @Test
        @DisplayName("Should return the same instance across different threads")
        void shouldReturnSameInstance_acrossThreads() throws InterruptedException {
            // Arrange
            final DatabaseConnection[] instances = new DatabaseConnection[2];
            
            // Act - Create threads that access the singleton
            Thread thread1 = new Thread(() -> instances[0] = DatabaseConnection.INSTANCE);
            Thread thread2 = new Thread(() -> instances[1] = DatabaseConnection.INSTANCE);
            
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            
            // Assert
            assertThat(instances[0]).isSameAs(instances[1]);
        }
    }
    
    @Nested
    @DisplayName("Connection Functionality Tests")
    class ConnectionFunctionalityTests {
        
        @Test
        @DisplayName("Should connect successfully")
        void shouldConnect_successfully() {
            // Arrange
            var connection = DatabaseConnection.INSTANCE;
            
            // Act
            connection.connect();
            
            // Assert
            assertThat(connection.isConnected()).isTrue();
        }
        
        @Test
        @DisplayName("Should disconnect successfully")
        void shouldDisconnect_successfully() {
            // Arrange
            var connection = DatabaseConnection.INSTANCE;
            connection.connect();
            
            // Act
            connection.disconnect();
            
            // Assert
            assertThat(connection.isConnected()).isFalse();
        }
        
        @Test
        @DisplayName("Should execute query when connected")
        void shouldExecuteQuery_whenConnected() {
            // Arrange
            var connection = DatabaseConnection.INSTANCE;
            connection.connect();
            String query = "SELECT * FROM users";
            
            // Act
            String result = connection.executeQuery(query);
            
            // Assert
            assertThat(result).isNotNull().contains(query);
        }
        
        @Test
        @DisplayName("Should throw exception when executing query without connection")
        void shouldThrowException_whenExecutingQueryWithoutConnection() {
            // Arrange
            var connection = DatabaseConnection.INSTANCE;
            connection.disconnect();
            
            // Act & Assert
            assertThatThrownBy(() -> connection.executeQuery("SELECT * FROM users"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("not connected");
        }
    }
    
    @Nested
    @DisplayName("Configuration Tests")
    class ConfigurationTests {
        
        @Test
        @DisplayName("Should have a pool size")
        void shouldHavePoolSize() {
            // Arrange & Act
            var connection = DatabaseConnection.INSTANCE;
            
            // Assert
            assertThat(connection.getPoolSize()).isGreaterThan(0);
        }
        
        @Test
        @DisplayName("Should set and get pool size")
        void shouldSetAndGetPoolSize() {
            // Arrange
            var connection = DatabaseConnection.INSTANCE;
            int newPoolSize = 25;
            
            // Act
            connection.setPoolSize(newPoolSize);
            
            // Assert
            assertThat(connection.getPoolSize()).isEqualTo(newPoolSize);
        }
        
        @Test
        @DisplayName("Should ignore invalid pool size")
        void shouldIgnoreInvalidPoolSize() {
            // Arrange
            var connection = DatabaseConnection.INSTANCE;
            connection.setPoolSize(15); // Set known value
            int originalPoolSize = connection.getPoolSize();
            
            // Act
            connection.setPoolSize(-1);
            connection.setPoolSize(0);
            
            // Assert
            assertThat(connection.getPoolSize()).isEqualTo(originalPoolSize);
        }
    }
}
