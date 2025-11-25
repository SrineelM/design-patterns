package com.example.patterns.behavioral.visitor;

import java.time.LocalDate;

/**
 * Food cart item.
 * 
 * @param name Food name
 * @param price Base price
 * @param expiryDate Expiration date
 * 
 * @since 1.0
 */
public record Food(String name, double price, LocalDate expiryDate) implements CartItem {
    
    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visitFood(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
    
    /**
     * Checks if food is expired.
     * 
     * @return true if expired
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}
