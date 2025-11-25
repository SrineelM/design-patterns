package com.example.patterns.structural.decorator;

/**
 * Concrete dark roast coffee (ConcreteComponent).
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class DarkRoast implements Beverage {
    
    private final Size size;
    
    /**
     * Creates a dark roast with default medium size.
     */
    public DarkRoast() {
        this(Size.MEDIUM);
    }
    
    /**
     * Creates a dark roast with specified size.
     *
     * @param size the beverage size
     */
    public DarkRoast(Size size) {
        this.size = size;
    }
    
    @Override
    public String getDescription() {
        return "Dark Roast Coffee (" + size + ")";
    }
    
    @Override
    public double getCost() {
        return 0.99 * size.getMultiplier();
    }
    
    @Override
    public Size getSize() {
        return size;
    }
}
