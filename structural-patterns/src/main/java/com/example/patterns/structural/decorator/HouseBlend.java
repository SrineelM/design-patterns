package com.example.patterns.structural.decorator;

/**
 * Concrete house blend coffee (ConcreteComponent).
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class HouseBlend implements Beverage {
    
    private final Size size;
    
    /**
     * Creates a house blend with default medium size.
     */
    public HouseBlend() {
        this(Size.MEDIUM);
    }
    
    /**
     * Creates a house blend with specified size.
     *
     * @param size the beverage size
     */
    public HouseBlend(Size size) {
        this.size = size;
    }
    
    @Override
    public String getDescription() {
        return "House Blend Coffee (" + size + ")";
    }
    
    @Override
    public double getCost() {
        return 0.89 * size.getMultiplier();
    }
    
    @Override
    public Size getSize() {
        return size;
    }
}
