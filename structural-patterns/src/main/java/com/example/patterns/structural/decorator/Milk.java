package com.example.patterns.structural.decorator;

/**
 * Milk condiment decorator (ConcreteDecorator).
 * 
 * <p>This is a ConcreteDecorator that adds milk to a beverage.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class Milk extends CondimentDecorator {
    
    /**
     * Creates a milk decorator.
     *
     * @param beverage the beverage to add milk to
     */
    public Milk(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }
    
    @Override
    public double getCost() {
        double condimentCost = 0.10 * getSize().getMultiplier();
        return beverage.getCost() + condimentCost;
    }
}
