package com.example.patterns.behavioral.observer;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationEventPublisher;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Observer pattern implementation using Spring Events.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Observer Pattern Tests")
class StockMarketTest {
    
    @Mock
    private ApplicationEventPublisher eventPublisher;
    
    @Nested
    @DisplayName("Stock Price Update Tests")
    class StockPriceUpdateTests {
        
        @Test
        @DisplayName("Should publish event when stock price changes")
        void shouldPublishEvent_whenStockPriceChanges() {
            // Arrange
            StockMarket stockMarket = new StockMarket(eventPublisher);
            stockMarket.updatePrice("AAPL", 100.00); // Set initial price
            
            // Act
            stockMarket.updatePrice("AAPL", 150.50);
            
            // Assert
            verify(eventPublisher).publishEvent(any(StockPriceChangeEvent.class));
        }
        
        @Test
        @DisplayName("Should not publish event when setting initial price")
        void shouldNotPublishEvent_whenSettingInitialPrice() {
            // Arrange
            StockMarket stockMarket = new StockMarket(eventPublisher);
            
            // Act
            stockMarket.updatePrice("GOOGL", 200.00);
            
            // Assert
            verify(eventPublisher, never()).publishEvent(any());
        }
        
        @Test
        @DisplayName("Should not publish event when price is unchanged")
        void shouldNotPublishEvent_whenPriceUnchanged() {
            // Arrange
            StockMarket stockMarket = new StockMarket(eventPublisher);
            stockMarket.updatePrice("MSFT", 300.00);
            reset(eventPublisher); // Reset mock after initial setup
            
            // Act
            stockMarket.updatePrice("MSFT", 300.00);
            
            // Assert
            verify(eventPublisher, never()).publishEvent(any());
        }
    }
    
    @Nested
    @DisplayName("Stock Price Query Tests")
    class StockPriceQueryTests {
        
        @Test
        @DisplayName("Should return current price for tracked stock")
        void shouldReturnCurrentPrice_forTrackedStock() {
            // Arrange
            StockMarket stockMarket = new StockMarket(eventPublisher);
            BigDecimal expectedPrice = BigDecimal.valueOf(150.50);
            stockMarket.updatePrice("AAPL", expectedPrice);
            
            // Act
            BigDecimal actualPrice = stockMarket.getCurrentPrice("AAPL");
            
            // Assert
            assertThat(actualPrice).isEqualByComparingTo(expectedPrice);
        }
        
        @Test
        @DisplayName("Should return null for untracked stock")
        void shouldReturnNull_forUntrackedStock() {
            // Arrange
            StockMarket stockMarket = new StockMarket(eventPublisher);
            
            // Act
            BigDecimal price = stockMarket.getCurrentPrice("UNKNOWN");
            
            // Assert
            assertThat(price).isNull();
        }
        
        @Test
        @DisplayName("Should correctly identify tracked stocks")
        void shouldIdentifyTrackedStocks() {
            // Arrange
            StockMarket stockMarket = new StockMarket(eventPublisher);
            stockMarket.updatePrice("AAPL", 100.00);
            
            // Act & Assert
            assertThat(stockMarket.isTracked("AAPL")).isTrue();
            assertThat(stockMarket.isTracked("UNKNOWN")).isFalse();
        }
    }
}
