package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Level 3 Support Handler - handles high-priority critical requests.
 * 
 * <p>This is the final handler in the chain. Level 3 support typically handles:</p>
 * <ul>
 *   <li>Critical system failures</li>
 *   <li>Security incidents</li>
 *   <li>Data loss or corruption issues</li>
 *   <li>Production outages</li>
 * </ul>
 * 
 * <p>Level 3 is usually composed of senior engineers, architects, or the
 * development team who built the system.</p>
 * 
 * <h3>Real-world Example:</h3>
 * <p>These are the experts who handle the most critical issues - think of them
 * as the "emergency room doctors" of customer support. They're called in when
 * systems are down or there are major security concerns.</p>
 * 
 * @since 1.0
 */
@Component
public class Level3SupportHandler extends SupportHandler {
    
    private static final Logger log = LoggerFactory.getLogger(Level3SupportHandler.class);
    
    /**
     * Level 3 support handles priority 3 (high priority) requests only.
     * 
     * @param request The support request to evaluate
     * @return true if priority is 3, false otherwise
     */
    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.priority() == 3;
    }
    
    /**
     * Processes a high-priority critical support request.
     * 
     * <p>In a real system, this might:</p>
     * <ul>
     *   <li>Initiate emergency response procedures</li>
     *   <li>Notify senior management</li>
     *   <li>Coordinate with multiple teams</li>
     *   <li>Implement hotfixes directly to production</li>
     *   <li>Conduct post-mortem analysis</li>
     * </ul>
     * 
     * @param request The request to process
     */
    @Override
    protected void processRequest(SupportRequest request) {
        log.error("WARNING: CRITICAL ISSUE ESCALATED TO LEVEL 3 SUPPORT");
        log.error("Customer: {}", request.customerName());
        log.error("Critical issue: {}", request.description());
        log.error("Resolution: Senior engineer notified, emergency response initiated");
        log.error("Incident #L3-CRITICAL-{} - War room activated", 
                  System.currentTimeMillis() % 10000);
        log.error("Priority: IMMEDIATE - All hands on deck");
    }
}
