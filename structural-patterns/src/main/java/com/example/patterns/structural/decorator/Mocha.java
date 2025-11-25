package com.example.patterns.structural.decorator;

/**
 * Mocha condiment decorator (ConcreteDecorator).
 * 
 * <p>This is a ConcreteDecorator that adds mocha to a beverage.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class Mocha extends CondimentDecorator {
    
    /**
     * Creates a mocha decorator.
     *
     * @param beverage the beverage to add mocha to
     */
    public Mocha(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
    
    @Override
    public double getCost() {
        double condimentCost = 0.20 * getSize().getMultiplier();
        return beverage.getCost() + condimentCost;
    }
}
