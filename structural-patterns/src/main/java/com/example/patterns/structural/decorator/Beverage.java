package com.example.patterns.structural.decorator;

/**
 * Component interface for beverages.
 * 
 * <p>This is the Component in the Decorator pattern.
 * It defines the interface for objects that can have responsibilities
 * added to them dynamically.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface Beverage {
    
    /**
     * Gets the description of this beverage.
     *
     * @return the beverage description
     */
    String getDescription();
    
    /**
     * Calculates the cost of this beverage.
     *
     * @return the cost in dollars
     */
    double getCost();
    
    /**
     * Gets the size of this beverage.
     *
     * @return the size (SMALL, MEDIUM, LARGE)
     */
    default Size getSize() {
        return Size.MEDIUM;
    }
    
    /**
     * Beverage size enum.
     */
    enum Size {
        SMALL(0.8), MEDIUM(1.0), LARGE(1.2);
        
        private final double multiplier;
        
        Size(double multiplier) {
            this.multiplier = multiplier;
        }
        
        public double getMultiplier() {
            return multiplier;
        }
    }
}
