package com.example.patterns.structural.flyweight;

/**
 * Demonstration of the Flyweight pattern.
 * 
 * <p>This demo shows how the Flyweight pattern reduces memory consumption
 * by sharing common state among many objects.
 * 
 * <p><b>Key Takeaways:</b>
 * <ul>
 *   <li>Separates intrinsic (shared) from extrinsic (unique) state</li>
 *   <li>Dramatically reduces memory usage for large numbers of similar objects</li>
 *   <li>Factory manages object sharing transparently</li>
 *   <li>Ideal when many objects share common properties</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class FlyweightDemo {
    
    /**
     * Runs the flyweight pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Flyweight Pattern Demonstration ===\n");
        
        // Create factory and text editor
        CharacterFactory factory = new CharacterFactory();
        TextEditor editor = new TextEditor(factory);
        
        System.out.println("Scenario 1: Simple Text");
        System.out.println("=".repeat(60));
        
        editor.insertText("Hello", "Arial", 12, "black");
        
        System.out.println("\nObserve: Only 4 flyweight objects created for 'Hello'");
        System.out.println("(H, e, l, o - 'l' is reused)");
        factory.printStatistics();
        
        System.out.println("\n\nScenario 2: Repeated Text");
        System.out.println("=".repeat(60));
        
        editor.clear();
        factory.clear();
        
        editor.insertText("aaa bbb ccc", "Arial", 12, "black");
        
        System.out.println("\nObserve: Only 5 unique characters despite 11 total characters");
        factory.printStatistics();
        System.out.println(editor.getMemoryStats());
        
        System.out.println("\nScenario 3: Large Document");
        System.out.println("=".repeat(60));
        
        editor.clear();
        factory.clear();
        
        String largeText = "The Flyweight pattern is a structural design pattern that " +
                          "lets you fit more objects into the available RAM by sharing " +
                          "common parts of state between multiple objects instead of " +
                          "keeping all of the data in each object.";
        
        editor.insertText(largeText, "Times New Roman", 14, "black");
        
        System.out.printf("%nDocument has %d characters%n", editor.getDocumentSize());
        factory.printStatistics();
        System.out.println(editor.getMemoryStats());
        
        System.out.println("\nScenario 4: Mixed Fonts");
        System.out.println("=".repeat(60));
        
        editor.clear();
        factory.clear();
        
        editor.insertText("Hello ", "Arial", 12, "black");
        editor.insertText("World", "Courier", 12, "blue");
        editor.insertText("!!!", "Arial", 12, "red");
        
        System.out.println("\nMixed fonts increase unique flyweights:");
        factory.printStatistics();
        System.out.println(editor.getMemoryStats());
        
        System.out.println("\n=== Memory Comparison ===");
        compareMemoryUsage();
        
        System.out.println("\n=== Key Benefits ===");
        System.out.println("✓ Reduces memory usage dramatically");
        System.out.println("✓ Enables handling of massive numbers of objects");
        System.out.println("✓ Transparent sharing through factory");
        System.out.println("✓ Clear separation of intrinsic and extrinsic state");
    }
    
    /**
     * Compares memory usage with and without flyweight pattern.
     */
    private static void compareMemoryUsage() {
        int documentSize = 10000;  // 10,000 characters
        int uniqueChars = 50;      // Typical for English text with symbols
        
        // Without flyweight: each character stores full state
        int bytesPerCharWithout = 100;  // Approximate size with all state
        int totalMemoryWithout = documentSize * bytesPerCharWithout;
        
        // With flyweight: shared intrinsic state + small extrinsic state
        int bytesPerFlyweight = 80;   // Intrinsic state
        int bytesPerInstance = 20;    // Extrinsic state (position, color, size)
        int totalMemoryWith = (uniqueChars * bytesPerFlyweight) + 
                             (documentSize * bytesPerInstance);
        
        int savings = ((totalMemoryWithout - totalMemoryWith) * 100) / totalMemoryWithout;
        
        System.out.printf("""
            For a document with %,d characters:
            
            WITHOUT Flyweight Pattern:
              - %,d characters × %d bytes = %,d bytes (%.2f MB)
            
            WITH Flyweight Pattern:
              - %d unique flyweights × %d bytes = %,d bytes
              - %,d instances × %d bytes = %,d bytes
              - Total: %,d bytes (%.2f MB)
            
            Memory Savings: %d%% reduction!
            """,
            documentSize,
            documentSize, bytesPerCharWithout, totalMemoryWithout, 
            totalMemoryWithout / (1024.0 * 1024),
            uniqueChars, bytesPerFlyweight, uniqueChars * bytesPerFlyweight,
            documentSize, bytesPerInstance, documentSize * bytesPerInstance,
            totalMemoryWith, totalMemoryWith / (1024.0 * 1024),
            savings
        );
    }
}
