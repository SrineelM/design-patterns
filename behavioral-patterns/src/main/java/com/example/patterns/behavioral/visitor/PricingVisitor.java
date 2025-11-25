package com.example.patterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Visitor that calculates total price with category-specific pricing rules.
 * 
 * <p>This visitor demonstrates how different operations can be performed on
 * elements without modifying the element classes themselves.</p>
 * 
 * @since 1.0
 */
@Component
public class PricingVisitor implements ItemVisitor {
    
    private static final Logger log = LoggerFactory.getLogger(PricingVisitor.class);
    
    private double totalCost = 0.0;
    
    @Override
    public void visitBook(Book book) {
        // Books get 10% discount
        double discount = book.getPrice() * 0.10;
        double finalPrice = book.getPrice() - discount;
        totalCost += finalPrice;
        log.info("Book '{}': ${} - 10% discount = ${}", 
                 book.getName(), book.getPrice(), finalPrice);
    }
    
    @Override
    public void visitElectronics(Electronics electronics) {
        // Electronics include warranty cost
        double warrantyCost = electronics.warrantyMonths() * 5.0;
        double finalPrice = electronics.getPrice() + warrantyCost;
        totalCost += finalPrice;
        log.info("Electronics '{}': ${} + warranty ${} = ${}", 
                 electronics.getName(), electronics.getPrice(), warrantyCost, finalPrice);
    }
    
    @Override
    public void visitFood(Food food) {
        // Fresh food at full price, expired food at 50% off
        double finalPrice = food.isExpired() ? 
            food.getPrice() * 0.5 : food.getPrice();
        totalCost += finalPrice;
        log.info("Food '{}': ${} {} = ${}", 
                 food.getName(), food.getPrice(), 
                 food.isExpired() ? "(expired, 50% off)" : "", finalPrice);
    }
    
    /**
     * Gets the total calculated cost.
     * 
     * @return The total cost
     */
    public double getTotalCost() {
        return totalCost;
    }
    
    /**
     * Resets the total cost for a new calculation.
     */
    public void reset() {
        totalCost = 0.0;
    }
}
