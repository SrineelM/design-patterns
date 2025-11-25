package com.example.patterns.structural.adapter;

/**
 * Modern customer implementation using Java records.
 * 
 * <p>This is a concrete implementation of the Customer interface
 * using Java 17 records for immutability and conciseness.
 * 
 * @param id the customer ID
 * @param fullName the customer's full name
 * @param email the customer's email
 * @param phoneNumber the customer's phone number
 * @param address the customer's address
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public record ModernCustomer(
    String id,
    String fullName,
    String email,
    String phoneNumber,
    String address
) implements Customer {
    
    /**
     * Creates a new modern customer with validation.
     *
     * @param id the customer ID
     * @param fullName the customer's full name
     * @param email the customer's email
     * @param phoneNumber the customer's phone number
     * @param address the customer's address
     * @throws IllegalArgumentException if any required field is null or empty
     */
    public ModernCustomer {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public String getFullName() {
        return fullName;
    }
    
    @Override
    public String getEmail() {
        return email;
    }
    
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    @Override
    public String getAddress() {
        return address;
    }
}
