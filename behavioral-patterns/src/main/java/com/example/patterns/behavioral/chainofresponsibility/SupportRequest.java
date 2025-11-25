package com.example.patterns.behavioral.chainofresponsibility;

import java.util.Objects;

/**
 * Represents a support request in the customer support system.
 * 
 * <p>A SupportRequest contains all the information about a customer's issue,
 * including priority level (1-3), description, and customer information.</p>
 * 
 * <h3>Priority Levels:</h3>
 * <ul>
 *   <li>1 = Low priority (general inquiries)</li>
 *   <li>2 = Medium priority (technical issues)</li>
 *   <li>3 = High priority (critical system failures)</li>
 * </ul>
 * 
 * <p>This is implemented as a Java 17 record for immutability and conciseness.</p>
 * 
 * @param priority The priority level (1-3)
 * @param description The description of the support request
 * @param customerName The name of the customer making the request
 * 
 * @since 1.0
 */
public record SupportRequest(
    int priority,
    String description,
    String customerName
) {
    /**
     * Compact constructor that validates the support request data.
     * 
     * @throws IllegalArgumentException if priority is not between 1 and 3
     * @throws NullPointerException if description or customerName is null
     */
    public SupportRequest {
        Objects.requireNonNull(description, "Description cannot be null");
        Objects.requireNonNull(customerName, "Customer name cannot be null");
        
        if (priority < 1 || priority > 3) {
            throw new IllegalArgumentException(
                "Priority must be between 1 and 3, got: " + priority
            );
        }
    }
    
    /**
     * Creates a low priority support request.
     * 
     * @param description The request description
     * @param customerName The customer's name
     * @return A new SupportRequest with priority 1
     */
    public static SupportRequest lowPriority(String description, String customerName) {
        return new SupportRequest(1, description, customerName);
    }
    
    /**
     * Creates a medium priority support request.
     * 
     * @param description The request description
     * @param customerName The customer's name
     * @return A new SupportRequest with priority 2
     */
    public static SupportRequest mediumPriority(String description, String customerName) {
        return new SupportRequest(2, description, customerName);
    }
    
    /**
     * Creates a high priority support request.
     * 
     * @param description The request description
     * @param customerName The customer's name
     * @return A new SupportRequest with priority 3
     */
    public static SupportRequest highPriority(String description, String customerName) {
        return new SupportRequest(3, description, customerName);
    }
}
