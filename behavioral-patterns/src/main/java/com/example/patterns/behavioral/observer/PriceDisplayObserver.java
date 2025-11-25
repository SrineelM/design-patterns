package com.example.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Price display observer that shows current stock prices.
 * 
 * <p>This is a Concrete Observer in the Observer pattern. It:</p>
 * <ul>
 *   <li>Listens for StockPriceChangeEvent using @EventListener</li>
 *   <li>Updates its display when prices change</li>
 *   <li>Doesn't need to explicitly register with the subject</li>
 * </ul>
 * 
 * <h3>How @EventListener Works:</h3>
 * <p>Spring scans for methods annotated with @EventListener and automatically
 * calls them when matching events are published. No manual registration needed!</p>
 * 
 * <pre>
 * StockMarket.updatePrice() 
 *     → publishes StockPriceChangeEvent
 *     → Spring delivers to all @EventListener methods
 *     → this.onPriceChange() is called automatically
 * </pre>
 * 
 * @since 1.0
 */
@Component
public class PriceDisplayObserver {
    
    private static final Logger log = LoggerFactory.getLogger(PriceDisplayObserver.class);
    
    /**
     * Event history for this observer.
     */
    private final List<StockPriceChangeEvent> eventHistory = new ArrayList<>();
    
    /**
     * Handles stock price change events.
     * 
     * <p>This method is called automatically by Spring whenever a
     * StockPriceChangeEvent is published. The @EventListener annotation
     * makes this method an observer.</p>
     * 
     * <h3>Method Signature:</h3>
     * <p>Spring uses the parameter type (StockPriceChangeEvent) to determine
     * which events this method should receive. You can have multiple
     * @EventListener methods for different event types.</p>
     * 
     * @param event The price change event
     */
    @EventListener
    public void onPriceChange(StockPriceChangeEvent event) {
        eventHistory.add(event);
        
        String direction = event.isIncrease() ? "▲" : "▼";
        String color = event.isIncrease() ? "GREEN" : "RED";
        
        log.info("[PRICE DISPLAY] {} {} ${} ({} {}%)", 
                 direction,
                 event.symbol(),
                 event.newPrice(),
                 color,
                 event.getPercentageChange().abs());
    }
    
    /**
     * Gets the number of price changes observed.
     * 
     * @return The event count
     */
    public int getEventCount() {
        return eventHistory.size();
    }
    
    /**
     * Gets the event history.
     * 
     * @return List of all observed events
     */
    public List<StockPriceChangeEvent> getEventHistory() {
        return new ArrayList<>(eventHistory);
    }
}
