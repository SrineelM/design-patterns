package com.example.patterns.structural.decorator;

/**
 * Soy condiment decorator (ConcreteDecorator).
 * 
 * <p>This is a ConcreteDecorator that adds soy milk to a beverage.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class Soy extends CondimentDecorator {
    
    /**
     * Creates a soy decorator.
     *
     * @param beverage the beverage to add soy to
     */
    public Soy(Beverage beverage) {
        super(beverage);
    }
    
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }
    
    @Override
    public double getCost() {
        double condimentCost = 0.15 * getSize().getMultiplier();
        return beverage.getCost() + condimentCost;
    }
}
