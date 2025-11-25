package com.example.patterns.structural.adapter;

/**
 * Modern customer interface expected by new application.
 * 
 * <p>This represents the Target interface in the Adapter pattern.
 * The new system expects customer data in this format.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface Customer {
    
    /**
     * Gets the customer's unique identifier.
     *
     * @return the customer ID
     */
    String getId();
    
    /**
     * Gets the customer's full name.
     *
     * @return the full name
     */
    String getFullName();
    
    /**
     * Gets the customer's email address.
     *
     * @return the email address
     */
    String getEmail();
    
    /**
     * Gets the customer's contact number.
     *
     * @return the phone number
     */
    String getPhoneNumber();
    
    /**
     * Gets the customer's complete address.
     *
     * @return the formatted address
     */
    String getAddress();
}
