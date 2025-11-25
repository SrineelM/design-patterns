package com.example.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Alert observer that triggers alerts for significant price changes.
 * 
 * <p>This demonstrates how different observers can react differently to the
 * same event. While PriceDisplayObserver just displays prices, this observer
 * checks for alert conditions.</p>
 * 
 * <h3>Multiple Observers:</h3>
 * <p>The beauty of the Observer pattern is that you can have any number of
 * observers, each doing different things with the same event:</p>
 * <ul>
 *   <li>PriceDisplayObserver - displays prices</li>
 *   <li>PriceAlertObserver - triggers alerts</li>
 *   <li>StatisticsObserver - calculates statistics</li>
 *   <li>LoggingObserver - logs to database</li>
 * </ul>
 * 
 * @since 1.0
 */
@Component
public class PriceAlertObserver {
    
    private static final Logger log = LoggerFactory.getLogger(PriceAlertObserver.class);
    
    /**
     * Threshold for triggering alerts (5% change).
     */
    private static final BigDecimal ALERT_THRESHOLD = BigDecimal.valueOf(5.0);
    
    /**
     * Alerts that have been triggered.
     */
    private final List<String> alerts = new ArrayList<>();
    
    /**
     * Handles stock price change events and triggers alerts if needed.
     * 
     * <p>This observer has different logic than PriceDisplayObserver even
     * though they both receive the same events. This demonstrates the
     * flexibility of the Observer pattern.</p>
     * 
     * @param event The price change event
     */
    @EventListener
    public void onPriceChange(StockPriceChangeEvent event) {
        BigDecimal percentChange = event.getPercentageChange().abs();
        
        if (percentChange.compareTo(ALERT_THRESHOLD) >= 0) {
            String direction = event.isIncrease() ? "UP" : "DOWN";
            String alert = String.format(
                "ALERT: %s moved %s by %.2f%% to $%s",
                event.symbol(),
                direction,
                percentChange,
                event.newPrice()
            );
            
            alerts.add(alert);
            log.warn("[PRICE ALERT] {}", alert);
        }
    }
    
    /**
     * Gets the list of triggered alerts.
     * 
     * @return List of alert messages
     */
    public List<String> getAlerts() {
        return new ArrayList<>(alerts);
    }
    
    /**
     * Gets the number of alerts triggered.
     * 
     * @return The alert count
     */
    public int getAlertCount() {
        return alerts.size();
    }
    
    /**
     * Clears all alerts.
     */
    public void clearAlerts() {
        alerts.clear();
    }
}
