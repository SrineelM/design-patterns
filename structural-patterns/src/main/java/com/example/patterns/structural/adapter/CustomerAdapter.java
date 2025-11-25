package com.example.patterns.structural.adapter;

import com.example.patterns.common.DesignPattern;

/**
 * Adapter that converts LegacyCustomer to modern Customer interface.
 * 
 * <p>This is the Adapter in the Adapter pattern. It wraps a LegacyCustomer
 * and adapts its interface to match the Customer interface expected by
 * modern systems.
 * 
 * <p><b>Pattern Benefits:</b>
 * <ul>
 *   <li>Reuses existing legacy code without modification</li>
 *   <li>Maintains single responsibility - adapter handles conversion logic</li>
 *   <li>Makes incompatible interfaces work together</li>
 *   <li>Allows gradual migration from legacy to modern systems</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Adapter",
    category = "Structural",
    description = "Converts legacy customer format to modern customer interface"
)
public class CustomerAdapter implements Customer {
    
    private final LegacyCustomer legacyCustomer;
    
    /**
     * Creates an adapter for the given legacy customer.
     *
     * @param legacyCustomer the legacy customer to adapt
     * @throws IllegalArgumentException if legacyCustomer is null
     */
    public CustomerAdapter(LegacyCustomer legacyCustomer) {
        if (legacyCustomer == null) {
            throw new IllegalArgumentException("Legacy customer cannot be null");
        }
        this.legacyCustomer = legacyCustomer;
    }
    
    @Override
    public String getId() {
        // Convert numeric ID to String format
        return "CUST-" + String.format("%06d", legacyCustomer.getCustomerNumber());
    }
    
    @Override
    public String getFullName() {
        // Combine first and last name
        return legacyCustomer.getFirstName() + " " + legacyCustomer.getLastName();
    }
    
    @Override
    public String getEmail() {
        // Map old field name to new name
        return legacyCustomer.getEmailAddr();
    }
    
    @Override
    public String getPhoneNumber() {
        // Format phone number consistently
        String phone = legacyCustomer.getTelephone();
        // Remove any formatting and add standard format
        String digitsOnly = phone.replaceAll("[^0-9]", "");
        if (digitsOnly.length() == 10) {
            return String.format("(%s) %s-%s", 
                digitsOnly.substring(0, 3),
                digitsOnly.substring(3, 6),
                digitsOnly.substring(6));
        }
        return phone;
    }
    
    @Override
    public String getAddress() {
        // Combine address components into single formatted string
        return String.format("%s, %s %s",
            legacyCustomer.getStreet(),
            legacyCustomer.getCity(),
            legacyCustomer.getZipCode());
    }
    
    /**
     * Gets the wrapped legacy customer object.
     * 
     * <p>This method is useful for accessing legacy-specific functionality
     * or for debugging purposes.
     *
     * @return the underlying legacy customer
     */
    public LegacyCustomer getLegacyCustomer() {
        return legacyCustomer;
    }
}
