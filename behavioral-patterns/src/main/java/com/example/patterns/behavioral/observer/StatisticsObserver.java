package com.example.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Statistics observer that calculates running statistics for stock prices.
 * 
 * <p>This is another observer demonstrating different processing of the same events.
 * It maintains running statistics like average price, total change, etc.</p>
 * 
 * <h3>Observer Independence:</h3>
 * <p>Notice how this observer maintains its own state completely independently
 * of other observers. They all receive the same events but process them
 * differently and maintain different state.</p>
 * 
 * @since 1.0
 */
@Component
public class StatisticsObserver {
    
    private static final Logger log = LoggerFactory.getLogger(StatisticsObserver.class);
    
    /**
     * Statistics for each stock symbol.
     */
    private final Map<String, StockStatistics> statistics = new ConcurrentHashMap<>();
    
    /**
     * Processes price change events and updates statistics.
     * 
     * @param event The price change event
     */
    @EventListener
    public void onPriceChange(StockPriceChangeEvent event) {
        statistics.compute(event.symbol(), (symbol, stats) -> {
            if (stats == null) {
                stats = new StockStatistics(symbol);
            }
            stats.addPriceChange(event);
            return stats;
        });
        
        StockStatistics stats = statistics.get(event.symbol());
        log.info("[STATISTICS] {} - Changes: {}, Avg: ${}, Total Change: {}%",
                 event.symbol(),
                 stats.changeCount,
                 stats.getAveragePrice(),
                 stats.getTotalPercentageChange());
    }
    
    /**
     * Gets statistics for a specific stock.
     * 
     * @param symbol The stock symbol
     * @return The statistics, or null if no data
     */
    public StockStatistics getStatistics(String symbol) {
        return statistics.get(symbol);
    }
    
    /**
     * Inner class holding statistics for a single stock.
     */
    public static class StockStatistics {
        private final String symbol;
        private int changeCount = 0;
        private BigDecimal totalChange = BigDecimal.ZERO;
        private BigDecimal sumOfPrices = BigDecimal.ZERO;
        private BigDecimal minPrice;
        private BigDecimal maxPrice;
        
        public StockStatistics(String symbol) {
            this.symbol = symbol;
        }
        
        public void addPriceChange(StockPriceChangeEvent event) {
            changeCount++;
            totalChange = totalChange.add(event.getPercentageChange());
            sumOfPrices = sumOfPrices.add(event.newPrice());
            
            if (minPrice == null || event.newPrice().compareTo(minPrice) < 0) {
                minPrice = event.newPrice();
            }
            
            if (maxPrice == null || event.newPrice().compareTo(maxPrice) > 0) {
                maxPrice = event.newPrice();
            }
        }
        
        public BigDecimal getAveragePrice() {
            if (changeCount == 0) return BigDecimal.ZERO;
            return sumOfPrices.divide(BigDecimal.valueOf(changeCount), 2, RoundingMode.HALF_UP);
        }
        
        public BigDecimal getTotalPercentageChange() {
            return totalChange.setScale(2, RoundingMode.HALF_UP);
        }
        
        public int getChangeCount() {
            return changeCount;
        }
        
        public BigDecimal getMinPrice() {
            return minPrice;
        }
        
        public BigDecimal getMaxPrice() {
            return maxPrice;
        }
    }
}
