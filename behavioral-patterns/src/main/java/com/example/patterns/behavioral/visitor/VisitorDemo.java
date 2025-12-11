package com.example.patterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Visitor pattern.
 * 
 * <p>This demo shows how the Visitor pattern lets you add new operations to existing
 * object structures without modifying those structures. It separates algorithms from
 * the objects they operate on, using a technique called "double dispatch" to route
 * operations to the correct method based on both the visitor and element types.</p>
 * 
 * <p><b>Key Takeaways:</b></p>
 * <ul>
 *   <li>Add new operations without modifying existing classes</li>
 *   <li>Separate algorithms from object structure</li>
 *   <li>Use double dispatch to route to correct method</li>
 *   <li>Gather related operations in one visitor class</li>
 *   <li>Easy to add new visitors, harder to add new element types</li>
 * </ul>
 * 
 * <h3>Real-World Analogy:</h3>
 * <p>Think of a shopping cart with different types of items (books, electronics, food).
 * You want to calculate different things: total price, shipping cost, tax, etc. Instead
 * of adding all these calculations to each item class, you create visitors that "visit"
 * each item and perform the calculation. This way, items stay simple and focused, while
 * visitors handle the complex operations.</p>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class VisitorDemo {
    
    private static final Logger log = LoggerFactory.getLogger(VisitorDemo.class);
    
    /**
     * Runs the Visitor pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Visitor Pattern Demonstration ===\n");
        
        log.info("This demo shows a shopping cart with different item types");
        log.info("Visitors perform different operations on the items\n");
        log.info("=".repeat(60));
        
        // Create a shopping cart with different item types
        ShoppingCart cart = new ShoppingCart();
        
        // Add various items
        cart.addItem(new Book("Design Patterns", 45.00, "978-0201633610"));
        cart.addItem(new Book("Clean Code", 40.00, "978-0132350884"));
        cart.addItem(new Electronics("Laptop", 1200.00, 24)); // 24 months warranty
        cart.addItem(new Electronics("Mouse", 25.00, 12)); // 12 months warranty
        cart.addItem(new Food("Apple", 2.00, false)); // Fresh
        cart.addItem(new Food("Banana", 1.50, true)); // Expired
        
        log.info("\nShopping Cart Contents:");
        log.info("-".repeat(60));
        for (CartItem item : cart.getItems()) {
            log.info("  • {}: ${}", item.getName(), item.getPrice());
        }
        
        // Scenario 1: Calculate pricing with discounts
        log.info("\n\nScenario 1: Pricing Calculation");
        log.info("-".repeat(60));
        log.info("Applying category-specific pricing rules:\n");
        
        PricingVisitor pricingVisitor = new PricingVisitor();
        cart.accept(pricingVisitor);
        
        log.info("\nTotal Cost: ${}", String.format("%.2f", pricingVisitor.getTotalCost()));
        
        // Scenario 2: Calculate shipping costs
        log.info("\n\nScenario 2: Shipping Calculation");
        log.info("-".repeat(60));
        log.info("Calculating shipping costs by category:\n");
        
        ShippingVisitor shippingVisitor = new ShippingVisitor();
        cart.accept(shippingVisitor);
        
        log.info("\nTotal Shipping: ${}", String.format("%.2f", shippingVisitor.getTotalShipping()));
        
        // Scenario 3: Different cart
        log.info("\n\n=== Different Shopping Cart ===");
        log.info("=".repeat(60));
        
        ShoppingCart cart2 = new ShoppingCart();
        cart2.addItem(new Book("Refactoring", 50.00, "978-0134757599"));
        cart2.addItem(new Electronics("Keyboard", 80.00, 12));
        cart2.addItem(new Food("Orange", 3.00, false));
        
        log.info("\nCart 2 Contents:");
        log.info("-".repeat(60));
        for (CartItem item : cart2.getItems()) {
            log.info("  • {}: ${}", item.getName(), item.getPrice());
        }
        
        // Apply both visitors to cart 2
        log.info("\n\nPricing for Cart 2:");
        log.info("-".repeat(60));
        pricingVisitor.reset();
        cart2.accept(pricingVisitor);
        log.info("Total Cost: ${}", String.format("%.2f", pricingVisitor.getTotalCost()));
        
        log.info("\n\nShipping for Cart 2:");
        log.info("-".repeat(60));
        shippingVisitor.reset();
        cart2.accept(shippingVisitor);
        log.info("Total Shipping: ${}", String.format("%.2f", shippingVisitor.getTotalShipping()));
        
        // Show the pattern structure
        log.info("\n\n=== Pattern Structure ===");
        log.info("=".repeat(60));
        log.info("\nDouble Dispatch Mechanism:");
        log.info("  1. Client calls: cart.accept(visitor)");
        log.info("  2. Cart calls: item.accept(visitor) for each item");
        log.info("  3. Item calls: visitor.visitBook(this) [or visitElectronics, visitFood]");
        log.info("  4. Visitor executes the specific operation for that item type");
        
        log.info("\n\nElement Hierarchy:");
        log.info("  CartItem (interface)");
        log.info("    ├─ Book");
        log.info("    ├─ Electronics");
        log.info("    └─ Food");
        
        log.info("\n\nVisitor Hierarchy:");
        log.info("  ItemVisitor (interface)");
        log.info("    ├─ PricingVisitor");
        log.info("    └─ ShippingVisitor");
        
        // Key Benefits Summary
        log.info("\n\n=== Key Benefits ===");
        log.info("✓ Add new operations without modifying item classes");
        log.info("✓ Related operations are grouped in visitor classes");
        log.info("✓ Visitors can accumulate state during traversal");
        log.info("✓ Easy to add new visitors (new operations)");
        log.info("✓ Follows Single Responsibility Principle");
        
        log.info("\n\n=== Trade-offs ===");
        log.info("Advantages:");
        log.info("  • Easy to add new operations (just add new visitor)");
        log.info("  • Operations are centralized in visitor classes");
        log.info("\nDisadvantages:");
        log.info("  • Hard to add new element types (must update all visitors)");
        log.info("  • Breaks encapsulation (visitors need access to element internals)");
        
        log.info("\n\n=== When to Use ===");
        log.info("• Object structure is stable (element types rarely change)");
        log.info("• You need to perform many different operations on objects");
        log.info("• Operations are unrelated to the objects' primary purpose");
        log.info("• You want to avoid polluting classes with many operations");
        log.info("• Examples: compilers (AST visitors), document processing, reporting");
    }
}
