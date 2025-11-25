package com.example.patterns.structural.adapter;

import java.util.List;

/**
 * Service that works with modern Customer interface.
 * 
 * <p>This service demonstrates how client code works with the
 * Customer interface without knowing whether it's dealing with
 * legacy or modern implementations.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class CustomerService {
    
    /**
     * Processes a customer order.
     * 
     * <p>This method works with any Customer implementation,
     * whether it's a ModernCustomer or a CustomerAdapter wrapping
     * a legacy system.
     *
     * @param customer the customer placing the order
     * @param orderAmount the order total amount
     * @return confirmation message
     */
    public String processOrder(Customer customer, double orderAmount) {
        return String.format(
            "Processing order for %s (ID: %s)\n" +
            "Amount: $%.2f\n" +
            "Confirmation sent to: %s\n" +
            "Shipping to: %s",
            customer.getFullName(),
            customer.getId(),
            orderAmount,
            customer.getEmail(),
            customer.getAddress()
        );
    }
    
    /**
     * Sends a marketing email to customers.
     *
     * @param customers list of customers to email
     * @param message the marketing message
     */
    public void sendMarketingEmail(List<Customer> customers, String message) {
        System.out.println("Sending marketing email: " + message);
        System.out.println("Recipients:");
        for (Customer customer : customers) {
            System.out.printf("  - %s <%s>%n", 
                customer.getFullName(), 
                customer.getEmail());
        }
    }
    
    /**
     * Generates a customer report.
     *
     * @param customer the customer to report on
     * @return formatted customer information
     */
    public String generateCustomerReport(Customer customer) {
        return String.format("""
            ================== CUSTOMER REPORT ==================
            ID:           %s
            Name:         %s
            Email:        %s
            Phone:        %s
            Address:      %s
            ====================================================
            """,
            customer.getId(),
            customer.getFullName(),
            customer.getEmail(),
            customer.getPhoneNumber(),
            customer.getAddress()
        );
    }
}
