package com.example.patterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Visitor that calculates shipping weight.
 * 
 * <p>This is another visitor performing a completely different operation
 * on the same elements, demonstrating the pattern's flexibility.</p>
 * 
 * @since 1.0
 */
@Component
public class ShippingVisitor implements ItemVisitor {
    
    private static final Logger log = LoggerFactory.getLogger(ShippingVisitor.class);
    
    private double totalWeight = 0.0;
    
    @Override
    public void visitBook(Book book) {
        double weight = 0.5; // Books weigh 0.5 kg
        totalWeight += weight;
        log.info("Book '{}': {} kg", book.getName(), weight);
    }
    
    @Override
    public void visitElectronics(Electronics electronics) {
        double weight = 2.0; // Electronics weigh 2 kg
        totalWeight += weight;
        log.info("Electronics '{}': {} kg", electronics.getName(), weight);
    }
    
    @Override
    public void visitFood(Food food) {
        double weight = 0.3; // Food weighs 0.3 kg
        totalWeight += weight;
        log.info("Food '{}': {} kg", food.getName(), weight);
    }
    
    public double getTotalWeight() {
        return totalWeight;
    }
    
    public void reset() {
        totalWeight = 0.0;
    }
}
