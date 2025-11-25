package com.example.patterns.structural.decorator;

/**
 * Caramel condiment decorator (ConcreteDecorator).
 * 
 * <p>This is a ConcreteDecorator that adds caramel to a beverage.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class Caramel extends CondimentDecorator {
    
    /**
     * Creates a caramel decorator.
     *
     * @param beverage the beverage to add caramel to
     */
    public Caramel(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Caramel";
    }
    
    @Override
    public double getCost() {
        double condimentCost = 0.25 * getSize().getMultiplier();
        return beverage.getCost() + condimentCost;
    }
}
