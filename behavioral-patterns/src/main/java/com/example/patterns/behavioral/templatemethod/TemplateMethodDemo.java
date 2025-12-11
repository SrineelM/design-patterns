package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Template Method pattern.
 * 
 * <p>This demo shows how the Template Method pattern defines the skeleton of an
 * algorithm in a base class, allowing subclasses to override specific steps without
 * changing the algorithm's structure. This promotes code reuse and ensures consistent
 * behavior across different implementations.</p>
 * 
 * <p><b>Key Takeaways:</b></p>
 * <ul>
 *   <li>Algorithm structure is defined once in the base class</li>
 *   <li>Subclasses customize specific steps without changing the overall flow</li>
 *   <li>Promotes code reuse - common steps are implemented once</li>
 *   <li>Enforces a consistent algorithm structure across implementations</li>
 *   <li>Uses inheritance (unlike Strategy which uses composition)</li>
 * </ul>
 * 
 * <h3>Real-World Analogy:</h3>
 * <p>Think of a recipe for making different types of pasta. The overall process is the same:
 * (1) Boil water, (2) Add pasta, (3) Cook for specified time, (4) Drain, (5) Add sauce.
 * The template method defines these steps, but each pasta type (spaghetti, penne, etc.)
 * specifies its own cooking time and sauce. The structure stays the same, but details vary.</p>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class TemplateMethodDemo {
    
    private static final Logger log = LoggerFactory.getLogger(TemplateMethodDemo.class);
    
    /**
     * Runs the Template Method pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Template Method Pattern Demonstration ===\n");
        
        log.info("This demo shows data processors for different formats (CSV, JSON, XML)");
        log.info("All follow the same processing algorithm, but customize specific steps\n");
        log.info("=".repeat(60));
        
        // Scenario 1: Process CSV data
        log.info("\nScenario 1: Processing CSV Data");
        log.info("-".repeat(60));
        DataProcessor csvProcessor = new CsvDataProcessor();
        csvProcessor.process();
        
        // Scenario 2: Process JSON data
        log.info("\n\nScenario 2: Processing JSON Data");
        log.info("-".repeat(60));
        DataProcessor jsonProcessor = new JsonDataProcessor();
        jsonProcessor.process();
        
        // Scenario 3: Process XML data
        log.info("\n\nScenario 3: Processing XML Data");
        log.info("-".repeat(60));
        DataProcessor xmlProcessor = new XmlDataProcessor();
        xmlProcessor.process();
        
        // Show the template method structure
        log.info("\n\n=== Template Method Structure ===");
        log.info("=".repeat(60));
        log.info("\nDataProcessor.process() [TEMPLATE METHOD - final]");
        log.info("  ├─ Step 1: loadData()       [ABSTRACT - must implement]");
        log.info("  ├─ Step 2: validateData()   [CONCRETE - same for all]");
        log.info("  ├─ Step 3: parseData()      [ABSTRACT - format specific]");
        log.info("  ├─ Step 4: transformData()  [HOOK - can override]");
        log.info("  └─ Step 5: saveResults()    [ABSTRACT - format specific]");
        
        log.info("\n\nSubclasses (CsvDataProcessor, JsonDataProcessor, XmlDataProcessor):");
        log.info("  • MUST implement: loadData(), parseData(), saveResults()");
        log.info("  • CAN override: transformData() (hook method)");
        log.info("  • CANNOT change: process() (template method is final)");
        
        // Key Benefits Summary
        log.info("\n\n=== Key Benefits ===");
        log.info("✓ Algorithm structure defined once - no duplication");
        log.info("✓ Common steps (like validation) implemented once");
        log.info("✓ Subclasses only override what's different");
        log.info("✓ Consistent behavior across all implementations");
        log.info("✓ Easy to add new formats - just extend and implement");
        
        log.info("\n\n=== Pattern Comparison ===");
        log.info("Template Method vs Strategy:");
        log.info("  Template Method:");
        log.info("    • Uses inheritance");
        log.info("    • Algorithm structure is fixed");
        log.info("    • Subclasses customize steps");
        log.info("    • Compile-time binding");
        log.info("\n  Strategy:");
        log.info("    • Uses composition");
        log.info("    • Entire algorithm can be swapped");
        log.info("    • Strategies are independent");
        log.info("    • Runtime binding");
        
        log.info("\n\n=== When to Use ===");
        log.info("• Multiple classes have similar algorithms with minor differences");
        log.info("• You want to avoid code duplication");
        log.info("• You want to control which steps subclasses can override");
        log.info("• The algorithm structure should remain consistent");
        log.info("• Examples: data processing pipelines, game AI, UI frameworks");
    }
}
