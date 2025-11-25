package com.example.patterns.structural.decorator;

/**
 * Whipped cream condiment decorator (ConcreteDecorator).
 * 
 * <p>This is a ConcreteDecorator that adds whipped cream to a beverage.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class Whip extends CondimentDecorator {
    
    /**
     * Creates a whip decorator.
     *
     * @param beverage the beverage to add whipped cream to
     */
    public Whip(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }
    
    @Override
    public double getCost() {
        double condimentCost = 0.10 * getSize().getMultiplier();
        return beverage.getCost() + condimentCost;
    }
}
