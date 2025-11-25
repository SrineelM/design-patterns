package com.example.patterns.behavioral.observer;

import com.example.patterns.common.DesignPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Stock market that publishes price changes (Subject in Observer pattern).
 * 
 * <p>This is the Subject/Observable in the Observer pattern. In Spring's event model:</p>
 * <ul>
 *   <li>Subject = Event Publisher (this class)</li>
 *   <li>Observer = Event Listener (classes with @EventListener)</li>
 *   <li>Notification = Event (StockPriceChangeEvent)</li>
 * </ul>
 * 
 * <h3>Traditional Observer vs Spring Events:</h3>
 * <pre>
 * Traditional:
 * Subject → maintains list of observers
 *        → notifies each observer directly
 *        
 * Spring:
 * Subject → publishes event
 * Framework → delivers to all @EventListener methods
 * </pre>
 * 
 * <h3>Benefits of Spring Approach:</h3>
 * <ul>
 *   <li>No manual observer registration/unregistration</li>
 *   <li>Type-safe event handling</li>
 *   <li>Automatic observer discovery</li>
 *   <li>Support for async and conditional listeners</li>
 * </ul>
 * 
 * @since 1.0
 */
@Service
@DesignPattern(
    name = "Observer",
    category = "Behavioral",
    description = "Notifies observers automatically when subject state changes"
)
public class StockMarket {
    
    private static final Logger log = LoggerFactory.getLogger(StockMarket.class);
    
    /**
     * Spring's event publisher - this is what makes Spring's event system work.
     * It handles all the observer notification logic for us.
     */
    private final ApplicationEventPublisher eventPublisher;
    
    /**
     * Current stock prices. Using ConcurrentHashMap for thread safety.
     */
    private final Map<String, BigDecimal> stockPrices = new ConcurrentHashMap<>();
    
    public StockMarket(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    /**
     * Updates a stock price and notifies observers.
     * 
     * <p>This is the key method that triggers the Observer pattern.
     * When the price changes, all registered observers are automatically
     * notified through Spring's event system.</p>
     * 
     * <h3>What Happens:</h3>
     * <ol>
     *   <li>Price is updated in our state</li>
     *   <li>Event is created with old and new prices</li>
     *   <li>Event is published via ApplicationEventPublisher</li>
     *   <li>Spring delivers event to all @EventListener methods</li>
     *   <li>Each observer processes the event independently</li>
     * </ol>
     * 
     * @param symbol The stock symbol
     * @param newPrice The new price
     */
    public void updatePrice(String symbol, BigDecimal newPrice) {
        BigDecimal oldPrice = stockPrices.get(symbol);
        
        if (oldPrice == null) {
            // First time setting this stock price
            log.info("Setting initial price for {}: {}", symbol, newPrice);
            stockPrices.put(symbol, newPrice);
            return;
        }
        
        if (oldPrice.compareTo(newPrice) == 0) {
            // Price didn't actually change
            log.debug("Price for {} unchanged at {}", symbol, newPrice);
            return;
        }
        
        // Price changed - update and notify observers
        stockPrices.put(symbol, newPrice);
        
        var event = new StockPriceChangeEvent(
            symbol,
            oldPrice,
            newPrice,
            LocalDateTime.now()
        );
        
        log.info("Publishing price change for {}: {} → {} ({}%)", 
                 symbol, oldPrice, newPrice, event.getPercentageChange());
        
        // This single call notifies ALL observers
        eventPublisher.publishEvent(event);
    }
    
    /**
     * Convenience method for updating price from double.
     * 
     * @param symbol The stock symbol
     * @param newPrice The new price
     */
    public void updatePrice(String symbol, double newPrice) {
        updatePrice(symbol, BigDecimal.valueOf(newPrice));
    }
    
    /**
     * Gets the current price of a stock.
     * 
     * @param symbol The stock symbol
     * @return The current price, or null if not tracked
     */
    public BigDecimal getCurrentPrice(String symbol) {
        return stockPrices.get(symbol);
    }
    
    /**
     * Checks if a stock is being tracked.
     * 
     * @param symbol The stock symbol
     * @return true if the stock has a price
     */
    public boolean isTracked(String symbol) {
        return stockPrices.containsKey(symbol);
    }
}
