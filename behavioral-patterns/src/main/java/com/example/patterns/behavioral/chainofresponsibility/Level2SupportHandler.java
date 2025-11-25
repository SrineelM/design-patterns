package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Level 2 Support Handler - handles medium-priority technical requests.
 * 
 * <p>This is the second handler in the chain. Level 2 support typically handles:</p>
 * <ul>
 *   <li>Technical issues requiring specialized knowledge</li>
 *   <li>Software troubleshooting</li>
 *   <li>Configuration problems</li>
 *   <li>Integration issues</li>
 * </ul>
 * 
 * <p>If a request has priority level 2, it will be handled here. Otherwise,
 * it's escalated to Level 3 support.</p>
 * 
 * <h3>Real-world Example:</h3>
 * <p>These are the technical specialists who handle issues that basic support
 * can't resolve, like debugging software problems or complex configurations.</p>
 * 
 * @since 1.0
 */
@Component
public class Level2SupportHandler extends SupportHandler {
    
    private static final Logger log = LoggerFactory.getLogger(Level2SupportHandler.class);
    
    /**
     * Level 2 support handles priority 2 (medium priority) requests only.
     * 
     * @param request The support request to evaluate
     * @return true if priority is 2, false otherwise
     */
    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.priority() == 2;
    }
    
    /**
     * Processes a medium-priority technical support request.
     * 
     * <p>In a real system, this might:</p>
     * <ul>
     *   <li>Analyze system logs</li>
     *   <li>Run diagnostic tools</li>
     *   <li>Apply configuration changes</li>
     *   <li>Coordinate with development team if needed</li>
     * </ul>
     * 
     * @param request The request to process
     */
    @Override
    protected void processRequest(SupportRequest request) {
        log.info("Level 2 Support: Processing technical issue for customer '{}'", 
                 request.customerName());
        log.info("Request details: {}", request.description());
        log.info("Resolution: Analyzed logs, applied fix, and scheduled follow-up");
        log.info("Ticket #L2-{} assigned to technical specialist", 
                 System.currentTimeMillis() % 10000);
    }
}
