package com.example.patterns.structural.decorator;

/**
 * Concrete espresso beverage (ConcreteComponent).
 * 
 * <p>This is a ConcreteComponent in the Decorator pattern.
 * It defines an object to which additional responsibilities can be attached.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class Espresso implements Beverage {
    
    private final Size size;
    
    /**
     * Creates an espresso with default medium size.
     */
    public Espresso() {
        this(Size.MEDIUM);
    }
    
    /**
     * Creates an espresso with specified size.
     *
     * @param size the beverage size
     */
    public Espresso(Size size) {
        this.size = size;
    }
    
    @Override
    public String getDescription() {
        return "Espresso (" + size + ")";
    }
    
    @Override
    public double getCost() {
        return 1.99 * size.getMultiplier();
    }
    
    @Override
    public Size getSize() {
        return size;
    }
}
