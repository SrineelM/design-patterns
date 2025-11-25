package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Level 1 Support Handler - handles basic, low-priority requests.
 * 
 * <p>This is the first handler in the chain. Level 1 support typically handles:</p>
 * <ul>
 *   <li>General inquiries</li>
 *   <li>Basic troubleshooting</li>
 *   <li>FAQ-type questions</li>
 *   <li>Account information requests</li>
 * </ul>
 * 
 * <p>If a request has priority level 1, it will be handled here. Otherwise,
 * it's escalated to Level 2 support.</p>
 * 
 * <h3>Real-world Example:</h3>
 * <p>Think of this as the first line of customer service - they handle
 * routine questions but escalate complex issues to specialized teams.</p>
 * 
 * @since 1.0
 */
@Component
public class Level1SupportHandler extends SupportHandler {
    
    private static final Logger log = LoggerFactory.getLogger(Level1SupportHandler.class);
    
    /**
     * Level 1 support handles priority 1 (low priority) requests only.
     * 
     * @param request The support request to evaluate
     * @return true if priority is 1, false otherwise
     */
    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.priority() == 1;
    }
    
    /**
     * Processes a low-priority support request.
     * 
     * <p>In a real system, this might:</p>
     * <ul>
     *   <li>Send an automated response</li>
     *   <li>Create a ticket in the ticketing system</li>
     *   <li>Log the interaction</li>
     *   <li>Update customer records</li>
     * </ul>
     * 
     * @param request The request to process
     */
    @Override
    protected void processRequest(SupportRequest request) {
        log.info("Level 1 Support: Processing basic inquiry for customer '{}'", 
                 request.customerName());
        log.info("Request details: {}", request.description());
        log.info("Resolution: Provided standard FAQ response and created ticket #L1-{}", 
                 System.currentTimeMillis() % 10000);
    }
}
