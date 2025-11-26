package com.example.patterns.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private static final Logger log = LoggerFactory.getLogger(AdapterDemo.class);
    
    /**
     * Runs the adapter pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Adapter Pattern Demonstration ===\n");
        
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
        
        log.info("1. Processing order for LEGACY customer (via adapter):");
        log.info(service.processOrder(adaptedLegacyCustomer, 149.99));
        log.info("");
        
        log.info("2. Processing order for MODERN customer:");
        log.info(service.processOrder(modernCustomer, 249.99));
        log.info("");
        
        log.info("3. Generating reports:");
        log.info(service.generateCustomerReport(adaptedLegacyCustomer));
        log.info(service.generateCustomerReport(modernCustomer));
        
        log.info("4. Sending marketing emails to mixed customer base:");
        List<Customer> allCustomers = Arrays.asList(
            adaptedLegacyCustomer,
            modernCustomer
        );
        service.sendMarketingEmail(allCustomers, "20% off all items this week!");
        
        log.info("\n=== Key Points ===");
        log.info("✓ Legacy customer (incompatible format) works via adapter");
        log.info("✓ Modern customer works directly");
        log.info("✓ Service treats both identically through Customer interface");
        log.info("✓ No modification needed to legacy or service code");
    }
}
