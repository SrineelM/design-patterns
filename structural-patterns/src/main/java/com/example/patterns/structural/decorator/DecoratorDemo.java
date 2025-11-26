package com.example.patterns.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Decorator pattern.
 * 
 * <p>This demo shows how the Decorator pattern allows behavior to be added
 * to individual objects dynamically, without affecting other objects of the same class.
 * 
 * <p><b>Key Takeaways:</b>
 * <ul>
 *   <li>Add responsibilities to objects dynamically</li>
 *   <li>More flexible than static inheritance</li>
 *   <li>Combine decorators in any order and quantity</li>
 *   <li>Follows Open/Closed Principle - open for extension, closed for modification</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class DecoratorDemo {
    
    private static final Logger log = LoggerFactory.getLogger(DecoratorDemo.class);
    
    /**
     * Runs the decorator pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Demonstration ===\n");
        System.out.println("*** Welcome to the Coffee Shop! ***\n");
        
        System.out.println("Order 1: Simple Espresso");
        System.out.println("=".repeat(50));
        Beverage beverage1 = new Espresso();
        printOrder(beverage1);
        
        System.out.println("\nOrder 2: Dark Roast with Mocha and Whip");
        System.out.println("=".repeat(50));
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        printOrder(beverage2);
        
        System.out.println("\nOrder 3: House Blend with Double Mocha and Whip");
        System.out.println("=".repeat(50));
        Beverage beverage3 = new HouseBlend();
        beverage3 = new Mocha(beverage3);
        beverage3 = new Mocha(beverage3);  // Double mocha!
        beverage3 = new Whip(beverage3);
        printOrder(beverage3);
        
        System.out.println("\nOrder 4: Large Espresso with Soy, Mocha, and Caramel");
        System.out.println("=".repeat(50));
        Beverage beverage4 = new Espresso(Beverage.Size.LARGE);
        beverage4 = new Soy(beverage4);
        beverage4 = new Mocha(beverage4);
        beverage4 = new Caramel(beverage4);
        printOrder(beverage4);
        
        System.out.println("\nOrder 5: Small Dark Roast with Milk");
        System.out.println("=".repeat(50));
        Beverage beverage5 = new DarkRoast(Beverage.Size.SMALL);
        beverage5 = new Milk(beverage5);
        printOrder(beverage5);
        
        System.out.println("\nOrder 6: The Works - Everything!");
        System.out.println("=".repeat(50));
        Beverage beverage6 = new Espresso();
        beverage6 = new Milk(beverage6);
        beverage6 = new Mocha(beverage6);
        beverage6 = new Soy(beverage6);
        beverage6 = new Whip(beverage6);
        beverage6 = new Caramel(beverage6);
        printOrder(beverage6);
        
        System.out.println("\n=== Key Benefits ===");
        System.out.println("✓ Add new condiments without modifying existing code");
        System.out.println("✓ Mix and match condiments in any combination");
        System.out.println("✓ Add multiple instances of the same condiment (double mocha)");
        System.out.println("✓ Different sizes automatically adjust condiment costs");
        System.out.println("✓ Each object can have different decorations");
        
        System.out.println("\n=== Pattern Structure ===");
        System.out.println("Component (Beverage)");
        System.out.println("  ├─ ConcreteComponent (Espresso, DarkRoast, HouseBlend)");
        System.out.println("  └─ Decorator (CondimentDecorator)");
        System.out.println("      └─ ConcreteDecorators (Milk, Mocha, Soy, Whip, Caramel)");
    }
    
    /**
     * Prints an order with description and cost.
     *
     * @param beverage the beverage to print
     */
    private static void printOrder(Beverage beverage) {
        System.out.println("Description: " + beverage.getDescription());
        System.out.printf("Cost: $%.2f%n", beverage.getCost());
    }
}
