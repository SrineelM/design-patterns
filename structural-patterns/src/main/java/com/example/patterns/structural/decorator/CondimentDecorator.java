package com.example.patterns.structural.decorator;

import com.example.patterns.common.DesignPattern;

/**
 * Abstract decorator for beverages (Decorator).
 * 
 * <p>This is the Decorator base class in the Decorator pattern.
 * It maintains a reference to a Component object and defines an interface
 * that conforms to Component's interface.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Decorator",
    category = "Structural",
    description = "Adds responsibilities to beverages dynamically"
)
public abstract class CondimentDecorator implements Beverage {
    
    protected final Beverage beverage;
    
    /**
     * Creates a decorator wrapping the given beverage.
     *
     * @param beverage the beverage to decorate
     * @throws IllegalArgumentException if beverage is null
     */
    protected CondimentDecorator(Beverage beverage) {
        if (beverage == null) {
            throw new IllegalArgumentException("Beverage cannot be null");
        }
        this.beverage = beverage;
    }
    
    /**
     * Gets the wrapped beverage.
     *
     * @return the wrapped beverage
     */
    public Beverage getBeverage() {
        return beverage;
    }
    
    @Override
    public Size getSize() {
        return beverage.getSize();
    }
    
    /**
     * Gets the description - must be implemented by concrete decorators.
     *
     * @return the description including the condiment
     */
    @Override
    public abstract String getDescription();
}
