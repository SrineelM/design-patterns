package com.example.patterns.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private static final Logger log = LoggerFactory.getLogger(ProxyDemo.class);
    
    /**
     * Runs the proxy pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Proxy Pattern Demonstration ===\n");
        
        demonstrateVirtualProxy();
        log.info("\n" + "=".repeat(60) + "\n");
        
        demonstrateCachingProxy();
        log.info("\n" + "=".repeat(60) + "\n");
        
        demonstrateProtectionProxy();
        
        log.info("\n=== Key Benefits ===");
        log.info("✓ Controls access to objects");
        log.info("✓ Adds functionality without modifying real objects");
        log.info("✓ Can improve performance (lazy loading, caching)");
        log.info("✓ Can enforce security (protection proxy)");
        log.info("✓ Transparent to clients");
    }
    
    /**
     * Demonstrates virtual proxy (lazy loading).
     */
    private static void demonstrateVirtualProxy() {
        log.info("1. VIRTUAL PROXY - Lazy Loading");
        log.info("=".repeat(60));
        
        // Create proxies - instant, no loading
        log.info("\nCreating 3 image proxies...");
        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.jpg");
        Image image3 = new ImageProxy("photo3.jpg");
        
        // Can access metadata without loading full image
        log.info("\nAccessing metadata (no loading required):");
        log.info("Image 1: {}, Size: {} bytes", 
            image1.getFilename(), image1.getFileSize());
        log.info("Image 2: {}, Dimensions: {}",
            image2.getFilename(), image2.getDimensions());
        
        // Display triggers lazy loading
        log.info("\nDisplaying image1 (triggers loading):");
        image1.display();
        
        log.info("\nDisplaying image1 again (already loaded):");
        image1.display();
        
        log.info("\nNote: image3 was never displayed, so never loaded!");
        log.info("This saves memory and loading time.");
    }
    
    /**
     * Demonstrates caching proxy.
     */
    private static void demonstrateCachingProxy() {
        log.info("2. CACHING PROXY - Performance Optimization");
        log.info("=".repeat(60));
        
        Database db = new CachingDatabaseProxy();
        
        log.info("\nExecuting queries...\n");
        
        // First query - cache miss
        log.info("Query 1 (first time):");
        db.query("SELECT * FROM users WHERE id = 1");
        
        log.info("\nQuery 2 (first time):");
        db.query("SELECT * FROM products WHERE price > 100");
        
        // Repeat queries - cache hits
        log.info("\nQuery 1 (again - should be cached):");
        db.query("SELECT * FROM users WHERE id = 1");
        
        log.info("\nQuery 2 (again - should be cached):");
        db.query("SELECT * FROM products WHERE price > 100");
        
        log.info("\nQuery 1 (again - still cached):");
        db.query("SELECT * FROM users WHERE id = 1");
        
        log.info("\n{}", db.getStatistics());
        
        log.info("Note: Cached queries return instantly!");
        log.info("This reduces database load and improves performance.");
    }
    
    /**
     * Demonstrates protection proxy (access control).
     */
    private static void demonstrateProtectionProxy() {
        log.info("3. PROTECTION PROXY - Access Control");
        log.info("=".repeat(60));
        
        Set<String> editors = Set.of("alice", "bob");
        Set<String> deleters = Set.of("admin");
        
        // Alice can edit
        log.info("\nAlice (editor) accessing document:");
        Document doc1 = new ProtectedDocumentProxy(
            "report.txt", "alice", editors, deleters
        );
        
        doc1.view();
        doc1.edit("New content by Alice");
        
        try {
            doc1.delete();
        } catch (SecurityException e) {
            log.info("Expected: {}", e.getMessage());
        }
        
        // Charlie (regular user) - limited access
        log.info("\nCharlie (regular user) accessing document:");
        Document doc2 = new ProtectedDocumentProxy(
            "report.txt", "charlie", editors, deleters
        );
        
        doc2.view();
        
        try {
            doc2.edit("Trying to edit");
        } catch (SecurityException e) {
            log.info("Expected: {}", e.getMessage());
        }
        
        // Admin - full access
        log.info("\nAdmin accessing document:");
        Document doc3 = new ProtectedDocumentProxy(
            "report.txt", "admin", editors, deleters
        );
        
        doc3.view();
        doc3.delete();
        
        log.info("\nNote: Protection proxy enforces security without");
        log.info("modifying the real document class!");
    }
}
