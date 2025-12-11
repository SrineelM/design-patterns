package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Chain of Responsibility pattern.
 * 
 * <p>This demo shows how the Chain of Responsibility pattern allows a request
 * to be passed along a chain of handlers until one of them handles it. Each handler
 * decides whether to process the request or pass it to the next handler in the chain.</p>
 * 
 * <p><b>Key Takeaways:</b></p>
 * <ul>
 *   <li>Decouples sender from receiver - sender doesn't need to know who handles the request</li>
 *   <li>Multiple handlers can be chained together dynamically</li>
 *   <li>Each handler has a single responsibility</li>
 *   <li>Easy to add new handlers without modifying existing code</li>
 *   <li>Requests can be handled at different levels based on their characteristics</li>
 * </ul>
 * 
 * <h3>Real-World Analogy:</h3>
 * <p>Think of a customer support system where tickets are escalated through different levels:
 * Level 1 (basic issues) → Level 2 (moderate issues) → Level 3 (critical issues).
 * Each level tries to handle the ticket, and if they can't, they pass it up the chain.</p>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class ChainOfResponsibilityDemo {
    
    private static final Logger log = LoggerFactory.getLogger(ChainOfResponsibilityDemo.class);
    
    /**
     * Runs the Chain of Responsibility pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Chain of Responsibility Pattern Demonstration ===\n");
        
        // Step 1: Create the handlers
        // Each handler represents a different support level
        SupportHandler level1 = new Level1SupportHandler();
        SupportHandler level2 = new Level2SupportHandler();
        SupportHandler level3 = new Level3SupportHandler();
        
        // Step 2: Build the chain
        // Level 1 → Level 2 → Level 3
        level1.setNext(level2).setNext(level3);
        
        log.info("Support Chain Built: Level 1 → Level 2 → Level 3\n");
        log.info("=" .repeat(60));
        
        // Scenario 1: Low priority request (handled by Level 1)
        log.info("\nScenario 1: Low Priority Request");
        log.info("-".repeat(60));
        SupportRequest request1 = SupportRequest.lowPriority(
            "How do I reset my password?",
            "Alice Johnson"
        );
        log.info("Request: {}", request1.getDescription());
        log.info("Priority: {}", request1.getPriority());
        log.info("Customer: {}\n", request1.getCustomerName());
        level1.handle(request1);
        
        // Scenario 2: Medium priority request (handled by Level 2)
        log.info("\n\nScenario 2: Medium Priority Request");
        log.info("-".repeat(60));
        SupportRequest request2 = SupportRequest.mediumPriority(
            "Payment failed but amount was deducted",
            "Bob Smith"
        );
        log.info("Request: {}", request2.getDescription());
        log.info("Priority: {}", request2.getPriority());
        log.info("Customer: {}\n", request2.getCustomerName());
        level1.handle(request2);
        
        // Scenario 3: High priority request (handled by Level 3)
        log.info("\n\nScenario 3: High Priority Request");
        log.info("-".repeat(60));
        SupportRequest request3 = SupportRequest.highPriority(
            "Database server is down! Production is affected!",
            "Charlie Davis"
        );
        log.info("Request: {}", request3.getDescription());
        log.info("Priority: {}", request3.getPriority());
        log.info("Customer: {}\n", request3.getCustomerName());
        level1.handle(request3);
        
        // Scenario 4: Multiple requests showing the chain in action
        log.info("\n\n=== Batch Processing Multiple Requests ===");
        log.info("=" .repeat(60));
        
        SupportRequest[] requests = {
            SupportRequest.lowPriority("Can't find my order history", "User1"),
            SupportRequest.mediumPriority("Refund not received after 5 days", "User2"),
            SupportRequest.highPriority("Security breach detected!", "User3"),
            SupportRequest.lowPriority("How to change email address?", "User4"),
            SupportRequest.mediumPriority("Account locked after failed login", "User5")
        };
        
        for (int i = 0; i < requests.length; i++) {
            log.info("\nRequest #{}: [{}] {}", 
                i + 1, 
                requests[i].getPriority(), 
                requests[i].getDescription()
            );
            level1.handle(requests[i]);
        }
        
        // Key Benefits Summary
        log.info("\n\n=== Key Benefits ===");
        log.info("✓ Sender doesn't need to know which handler will process the request");
        log.info("✓ New handlers can be added without modifying existing code");
        log.info("✓ Each handler has a single, well-defined responsibility");
        log.info("✓ The chain can be configured dynamically at runtime");
        log.info("✓ Promotes loose coupling between sender and receiver");
        
        log.info("\n\n=== Pattern Structure ===");
        log.info("Client → Handler1 → Handler2 → Handler3");
        log.info("         (Level1)   (Level2)   (Level3)");
        log.info("\nEach handler:");
        log.info("  1. Checks if it can handle the request");
        log.info("  2. If yes: processes it and stops");
        log.info("  3. If no: passes to next handler in chain");
    }
}
