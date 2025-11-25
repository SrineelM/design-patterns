package com.example.patterns.behavioral.chainofresponsibility;

import org.springframework.stereotype.Service;

/**
 * Service that sets up and manages the support handler chain.
 * 
 * <p>This class demonstrates how to configure the Chain of Responsibility pattern
 * in a Spring application. It builds the chain: Level1 → Level2 → Level3</p>
 * 
 * <h3>Chain Configuration:</h3>
 * <pre>
 * Client Request
 *       ↓
 * [Level 1 Support] → Can handle priority 1?
 *       ↓ No
 * [Level 2 Support] → Can handle priority 2?
 *       ↓ No
 * [Level 3 Support] → Can handle priority 3?
 *       ↓ No
 * [End of Chain]
 * </pre>
 * 
 * <p>Spring's dependency injection automatically creates and injects the handler
 * instances, and this service chains them together.</p>
 * 
 * @since 1.0
 */
@Service
public class SupportChainService {
    
    private final SupportHandler chainHead;
    
    /**
     * Constructor that builds the support handler chain.
     * 
     * <p>Spring automatically injects the handler instances. We then chain them
     * together using the fluent API provided by {@link SupportHandler#setNext(SupportHandler)}.</p>
     * 
     * <p>The order matters! We start with Level 1 (handles simple cases) and
     * escalate to Level 3 (handles critical cases).</p>
     * 
     * @param level1Handler The first handler in the chain
     * @param level2Handler The second handler in the chain
     * @param level3Handler The third handler in the chain
     */
    public SupportChainService(
            Level1SupportHandler level1Handler,
            Level2SupportHandler level2Handler,
            Level3SupportHandler level3Handler) {
        
        // Build the chain: Level1 → Level2 → Level3
        // The setNext() method returns the next handler, allowing fluent chaining
        this.chainHead = level1Handler;
        level1Handler.setNext(level2Handler)
                     .setNext(level3Handler);
    }
    
    /**
     * Submits a support request to the handler chain.
     * 
     * <p>The request is given to the first handler in the chain (Level 1).
     * Each handler decides whether to process it or pass it along.</p>
     * 
     * <h3>Example Usage:</h3>
     * <pre>
     * var request = SupportRequest.highPriority(
     *     "Database server is down!", 
     *     "John Doe"
     * );
     * supportChainService.handleRequest(request);
     * // This will escalate through Level 1 and 2, finally handled by Level 3
     * </pre>
     * 
     * @param request The support request to process
     */
    public void handleRequest(SupportRequest request) {
        chainHead.handle(request);
    }
}
