package com.example.patterns.behavioral.observer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Event representing a stock price change.
 * 
 * <p>This is the event that observers will receive. In Spring's event system,
 * this is equivalent to the "notification" in the traditional Observer pattern.</p>
 * 
 * <p>Java 17 record makes this concise and immutable.</p>
 * 
 * @param symbol The stock symbol (e.g., "AAPL", "GOOGL")
 * @param oldPrice The previous price
 * @param newPrice The new current price
 * @param timestamp When the price changed
 * 
 * @since 1.0
 */
public record StockPriceChangeEvent(
    String symbol,
    BigDecimal oldPrice,
    BigDecimal newPrice,
    LocalDateTime timestamp
) {
    /**
     * Gets the price change amount.
     * 
     * @return The difference between new and old price
     */
    public BigDecimal getChange() {
        return newPrice.subtract(oldPrice);
    }
    
    /**
     * Gets the percentage change.
     * 
     * @return The percentage change
     */
    public BigDecimal getPercentageChange() {
        if (oldPrice.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return getChange()
            .divide(oldPrice, 4, java.math.RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));
    }
    
    /**
     * Checks if this is a price increase.
     * 
     * @return true if price went up
     */
    public boolean isIncrease() {
        return newPrice.compareTo(oldPrice) > 0;
    }
    
    /**
     * Checks if this is a price decrease.
     * 
     * @return true if price went down
     */
    public boolean isDecrease() {
        return newPrice.compareTo(oldPrice) < 0;
    }
}
