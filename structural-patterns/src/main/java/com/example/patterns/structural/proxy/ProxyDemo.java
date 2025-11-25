package com.example.patterns.structural.proxy;

import java.util.Set;

/**
 * Demonstration of the Proxy pattern.
 * 
 * <p>This demo shows three types of proxies:
 * <ul>
 *   <li>Virtual Proxy - lazy loading of expensive objects</li>
 *   <li>Caching Proxy - caching results of expensive operations</li>
 *   <li>Protection Proxy - access control and security</li>
 * </ul>
 * 
 * <p><b>Key Takeaways:</b>
 * <ul>
 *   <li>Proxy has same interface as real object</li>
 *   <li>Controls access to the real object</li>
 *   <li>Can add functionality without changing real object</li>
 *   <li>Transparent to clients</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class ProxyDemo {
    
    /**
     * Runs the proxy pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Demonstration ===\n");
        
        demonstrateVirtualProxy();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        demonstrateCachingProxy();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        demonstrateProtectionProxy();
        
        System.out.println("\n=== Key Benefits ===");
        System.out.println("✓ Controls access to objects");
        System.out.println("✓ Adds functionality without modifying real objects");
        System.out.println("✓ Can improve performance (lazy loading, caching)");
        System.out.println("✓ Can enforce security (protection proxy)");
        System.out.println("✓ Transparent to clients");
    }
    
    /**
     * Demonstrates virtual proxy (lazy loading).
     */
    private static void demonstrateVirtualProxy() {
        System.out.println("1. VIRTUAL PROXY - Lazy Loading");
        System.out.println("=".repeat(60));
        
        // Create proxies - instant, no loading
        System.out.println("\nCreating 3 image proxies...");
        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.jpg");
        Image image3 = new ImageProxy("photo3.jpg");
        
        // Can access metadata without loading full image
        System.out.println("\nAccessing metadata (no loading required):");
        System.out.printf("Image 1: %s, Size: %d bytes%n", 
            image1.getFilename(), image1.getFileSize());
        System.out.printf("Image 2: %s, Dimensions: %s%n",
            image2.getFilename(), image2.getDimensions());
        
        // Display triggers lazy loading
        System.out.println("\nDisplaying image1 (triggers loading):");
        image1.display();
        
        System.out.println("\nDisplaying image1 again (already loaded):");
        image1.display();
        
        System.out.println("\nNote: image3 was never displayed, so never loaded!");
        System.out.println("This saves memory and loading time.");
    }
    
    /**
     * Demonstrates caching proxy.
     */
    private static void demonstrateCachingProxy() {
        System.out.println("2. CACHING PROXY - Performance Optimization");
        System.out.println("=".repeat(60));
        
        Database db = new CachingDatabaseProxy();
        
        System.out.println("\nExecuting queries...\n");
        
        // First query - cache miss
        System.out.println("Query 1 (first time):");
        db.query("SELECT * FROM users WHERE id = 1");
        
        System.out.println("\nQuery 2 (first time):");
        db.query("SELECT * FROM products WHERE price > 100");
        
        // Repeat queries - cache hits
        System.out.println("\nQuery 1 (again - should be cached):");
        db.query("SELECT * FROM users WHERE id = 1");
        
        System.out.println("\nQuery 2 (again - should be cached):");
        db.query("SELECT * FROM products WHERE price > 100");
        
        System.out.println("\nQuery 1 (again - still cached):");
        db.query("SELECT * FROM users WHERE id = 1");
        
        System.out.println("\n" + db.getStatistics());
        
        System.out.println("Note: Cached queries return instantly!");
        System.out.println("This reduces database load and improves performance.");
    }
    
    /**
     * Demonstrates protection proxy (access control).
     */
    private static void demonstrateProtectionProxy() {
        System.out.println("3. PROTECTION PROXY - Access Control");
        System.out.println("=".repeat(60));
        
        Set<String> editors = Set.of("alice", "bob");
        Set<String> deleters = Set.of("admin");
        
        // Alice can edit
        System.out.println("\nAlice (editor) accessing document:");
        Document doc1 = new ProtectedDocumentProxy(
            "report.txt", "alice", editors, deleters
        );
        
        doc1.view();
        doc1.edit("New content by Alice");
        
        try {
            doc1.delete();
        } catch (SecurityException e) {
            System.out.println("Expected: " + e.getMessage());
        }
        
        // Charlie (regular user) - limited access
        System.out.println("\nCharlie (regular user) accessing document:");
        Document doc2 = new ProtectedDocumentProxy(
            "report.txt", "charlie", editors, deleters
        );
        
        doc2.view();
        
        try {
            doc2.edit("Trying to edit");
        } catch (SecurityException e) {
            System.out.println("Expected: " + e.getMessage());
        }
        
        // Admin - full access
        System.out.println("\nAdmin accessing document:");
        Document doc3 = new ProtectedDocumentProxy(
            "report.txt", "admin", editors, deleters
        );
        
        doc3.view();
        doc3.delete();
        
        System.out.println("\nNote: Protection proxy enforces security without");
        System.out.println("modifying the real document class!");
    }
}
