package com.example.patterns.behavioral.chainofresponsibility;

import com.example.patterns.common.DesignPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract handler in the Chain of Responsibility pattern.
 * 
 * <p>This is the core of the Chain of Responsibility pattern. Each handler:</p>
 * <ol>
 *   <li>Holds a reference to the next handler in the chain</li>
 *   <li>Decides whether to handle the request or pass it along</li>
 *   <li>Can process the request even if it doesn't fully handle it</li>
 * </ol>
 * 
 * <h3>How the Chain Works:</h3>
 * <pre>
 * Client → Handler1 → Handler2 → Handler3 → null
 *            |           |           |
 *         Can I      Can I       Can I
 *         handle?    handle?     handle?
 * </pre>
 * 
 * <p>Subclasses must implement {@link #canHandle(SupportRequest)} to determine
 * if they can process a request, and {@link #processRequest(SupportRequest)} to
 * actually handle it.</p>
 * 
 * @since 1.0
 */
@DesignPattern(
    name = "Chain of Responsibility",
    category = "Behavioral",
    description = "Passes requests along a chain of handlers until one handles it"
)
public abstract class SupportHandler {
    
    private static final Logger log = LoggerFactory.getLogger(SupportHandler.class);
    
    /**
     * The next handler in the chain. If this is null, we're at the end of the chain.
     */
    private SupportHandler nextHandler;
    
    /**
     * Sets the next handler in the chain.
     * 
     * <p>This method returns 'this' to allow fluent chaining:</p>
     * <pre>
     * handler1.setNext(handler2).setNext(handler3);
     * </pre>
     * 
     * @param handler The next handler to add to the chain
     * @return This handler (for method chaining)
     */
    public SupportHandler setNext(SupportHandler handler) {
        this.nextHandler = handler;
        return handler;
    }
    
    /**
     * Handles the support request by checking if this handler can process it.
     * If not, it passes the request to the next handler in the chain.
     * 
     * <p>This is the template method that defines the chain's behavior:</p>
     * <ol>
     *   <li>Check if we can handle this request</li>
     *   <li>If yes, process it</li>
     *   <li>If no, pass it to the next handler (or log if chain ends)</li>
     * </ol>
     * 
     * @param request The support request to handle
     */
    public void handle(SupportRequest request) {
        if (canHandle(request)) {
            log.info("{} is handling the request", getClass().getSimpleName());
            processRequest(request);
        } else if (nextHandler != null) {
            log.info("{} cannot handle, passing to next handler", getClass().getSimpleName());
            nextHandler.handle(request);
        } else {
            log.warn("End of chain reached - no handler could process request: {}", request);
        }
    }
    
    /**
     * Determines if this handler can process the given request.
     * 
     * <p>This is typically based on the request's properties like priority,
     * type, or other criteria specific to the handler.</p>
     * 
     * @param request The request to check
     * @return true if this handler can process the request, false otherwise
     */
    protected abstract boolean canHandle(SupportRequest request);
    
    /**
     * Processes the request. This method is only called if {@link #canHandle(SupportRequest)}
     * returns true.
     * 
     * @param request The request to process
     */
    protected abstract void processRequest(SupportRequest request);
}
