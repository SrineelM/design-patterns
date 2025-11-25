package com.example.patterns.structural.adapter;

import java.util.Arrays;
import java.util.List;

/**
 * Demonstration of the Adapter pattern.
 * 
 * <p>This demo shows how the Adapter pattern allows legacy customer data
 * to work seamlessly with modern systems that expect a different interface.
 * 
 * <p><b>Key Takeaways:</b>
 * <ul>
 *   <li>Legacy code doesn't need to be modified</li>
 *   <li>New code works with a consistent interface</li>
 *   <li>Adapter handles all conversion logic in one place</li>
 *   <li>Both legacy and modern implementations work interchangeably</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class AdapterDemo {
    
    /**
     * Runs the adapter pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Demonstration ===\n");
        
        // Create a legacy customer from the old system
        LegacyCustomer legacyCustomer = new LegacyCustomer(
            12345,
            "John",
            "Doe",
            "john.doe@example.com",
            "5551234567",
            "123 Main Street",
            "Springfield",
            "12345"
        );
        
        // Create a modern customer using the new system
        ModernCustomer modernCustomer = new ModernCustomer(
            "CUST-000001",
            "Jane Smith",
            "jane.smith@example.com",
            "(555) 987-6543",
            "456 Oak Avenue, Shelbyville 67890"
        );
        
        // Adapt the legacy customer to work with modern systems
        Customer adaptedLegacyCustomer = new CustomerAdapter(legacyCustomer);
        
        // Create a service that works with the modern Customer interface
        CustomerService service = new CustomerService();
        
        System.out.println("1. Processing order for LEGACY customer (via adapter):");
        System.out.println(service.processOrder(adaptedLegacyCustomer, 149.99));
        System.out.println();
        
        System.out.println("2. Processing order for MODERN customer:");
        System.out.println(service.processOrder(modernCustomer, 249.99));
        System.out.println();
        
        System.out.println("3. Generating reports:");
        System.out.println(service.generateCustomerReport(adaptedLegacyCustomer));
        System.out.println(service.generateCustomerReport(modernCustomer));
        
        System.out.println("4. Sending marketing emails to mixed customer base:");
        List<Customer> allCustomers = Arrays.asList(
            adaptedLegacyCustomer,
            modernCustomer
        );
        service.sendMarketingEmail(allCustomers, "20% off all items this week!");
        
        System.out.println("\n=== Key Points ===");
        System.out.println("✓ Legacy customer (incompatible format) works via adapter");
        System.out.println("✓ Modern customer works directly");
        System.out.println("✓ Service treats both identically through Customer interface");
        System.out.println("✓ No modification needed to legacy or service code");
    }
}
