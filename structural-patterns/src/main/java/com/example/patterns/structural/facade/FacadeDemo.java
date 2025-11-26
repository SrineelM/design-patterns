package com.example.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Facade pattern.
 * 
 * <p>This demo shows how the Facade pattern simplifies complex subsystem interactions
 * by providing a unified, high-level interface.
 * 
 * <p><b>Key Takeaways:</b>
 * <ul>
 *   <li>Simplifies complex subsystem interactions</li>
 *   <li>Reduces client dependencies on subsystems</li>
 *   <li>Provides a single point of entry for common operations</li>
 *   <li>Doesn't prevent access to subsystems if needed</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class FacadeDemo {
    
    private static final Logger log = LoggerFactory.getLogger(FacadeDemo.class);
    
    /**
     * Runs the facade pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Facade Pattern Demonstration ===\n");
        
        // Create the facade
        OrderFacade orderFacade = new OrderFacade();
        
        log.info("WITHOUT FACADE: Client must interact with all subsystems");
        log.info("=".repeat(60));
        demonstrateWithoutFacade();
        
        log.info("\n\nWITH FACADE: Client uses simple interface");
        log.info("=".repeat(60));
        demonstrateWithFacade(orderFacade);
        
        log.info("\n\n=== Additional Orders ===");
        
        // Success case
        System.out.println("\nOrder 2: Successful Order");
        OrderResult result2 = orderFacade.placeOrder(
            "CUST-002",
            "bob@example.com",
            "PROD-456",
            2,
            "5555666677778888",
            "456",
            "12/26",
            "789 Oak Street, Springfield, IL 62701",
            49.98
        );
        printResult(result2);
        
        // Failure case - invalid payment
        System.out.println("\nOrder 3: Failed Order (Invalid Payment)");
        OrderResult result3 = orderFacade.placeOrder(
            "CUST-003",
            "charlie@example.com",
            "PROD-789",
            1,
            "123",  // Invalid card number
            "99",   // Invalid CVV
            "01/25",
            "321 Elm Avenue, Shelbyville, IL 62565",
            29.99
        );
        printResult(result3);
        
        System.out.println("\n=== Key Benefits ===");
        System.out.println("✓ Complex workflow reduced to single method call");
        System.out.println("✓ Client doesn't need to know about subsystem interactions");
        System.out.println("✓ Error handling and rollback centralized in facade");
        System.out.println("✓ Easy to test and maintain");
        System.out.println("✓ Changes to subsystems don't affect client code");
    }
    
    /**
     * Demonstrates order processing WITHOUT facade.
     * Shows the complexity client must deal with.
     */
    private static void demonstrateWithoutFacade() {
        System.out.println("Client must manually coordinate 4 subsystems:");
        System.out.println("  1. InventoryService - check and reserve");
        System.out.println("  2. PaymentService - validate, authorize, capture");
        System.out.println("  3. ShippingService - validate, calculate, schedule");
        System.out.println("  4. NotificationService - send confirmations");
        System.out.println("\nClient code would need:");
        System.out.println("  - Create instances of all 4 services");
        System.out.println("  - Call 10+ methods in correct order");
        System.out.println("  - Handle errors and rollback at each step");
        System.out.println("  - Know internal details of each subsystem");
        System.out.println("\nThis creates tight coupling and complex client code!");
    }
    
    /**
     * Demonstrates order processing WITH facade.
     * Shows the simplification.
     *
     * @param facade the order facade
     */
    private static void demonstrateWithFacade(OrderFacade facade) {
        System.out.println("Client makes ONE simple call:\n");
        
        OrderResult result = facade.placeOrder(
            "CUST-001",
            "alice@example.com",
            "PROD-123",
            1,
            "4111111111111111",
            "123",
            "12/25",
            "123 Main Street, Springfield, IL 62701",
            99.99
        );
        
        printResult(result);
        
        System.out.println("\nFacade handles:");
        System.out.println("  ✓ All subsystem coordination");
        System.out.println("  ✓ Error handling and rollback");
        System.out.println("  ✓ Complex workflow orchestration");
        System.out.println("  ✓ Internal subsystem details");
    }
    
    /**
     * Prints order result.
     *
     * @param result the order result
     */
    private static void printResult(OrderResult result) {
        System.out.println("\n" + "-".repeat(60));
        if (result.success()) {
            System.out.println("✓ SUCCESS: " + result.message());
            System.out.println("  Order ID: " + result.orderId());
        } else {
            System.out.println("✗ FAILURE: " + result.message());
        }
        System.out.println("-".repeat(60));
    }
}
