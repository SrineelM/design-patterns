package com.example.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Demonstration of the Composite pattern.
 * 
 * <p>This demo shows how the Composite pattern allows treating individual
 * objects (files) and compositions (directories) uniformly through a common interface.
 * 
 * <p><b>Key Takeaways:</b>
 * <ul>
 *   <li>Clients treat leaf and composite objects uniformly</li>
 *   <li>Tree structures are naturally represented</li>
 *   <li>Operations propagate through the tree recursively</li>
 *   <li>Easy to add new component types</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class CompositeDemo {
    
    private static final Logger log = LoggerFactory.getLogger(CompositeDemo.class);
    
    /**
     * Runs the composite pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Composite Pattern Demonstration ===");
        
        // Build a file system structure
        Directory root = buildFileSystem();
        
        log.info("1. Display File System Structure:");
        log.info("=".repeat(50));
        root.display("");
        
        log.info("\n2. Calculate Total Size:");
        log.info("=".repeat(50));
        log.info("Total size of root: {} bytes", root.getSize());
        log.info("Total files: {}", root.getFileCount());
        log.info("Total directories: {}", root.getDirectoryCount());
        
        log.info("\n3. Search Operations:");
        log.info("=".repeat(50));
        
        // Search for Java files
        log.info("Searching for '*.java' files:");
        List<FileSystemComponent> javaFiles = root.search("*.java");
        for (FileSystemComponent component : javaFiles) {
            log.info("  Found: {}", component.getPath());
        }
        
        // Search for config files
        log.info("\nSearching for 'config' files:");
        List<FileSystemComponent> configFiles = root.search("*config*");
        for (FileSystemComponent component : configFiles) {
            log.info("  Found: {}", component.getPath());
        }
        
        log.info("\n4. Treating Leaf and Composite Uniformly:");
        log.info("=".repeat(50));
        
        // Get a specific directory
        Directory srcDir = (Directory) root.getChildren().get(0);
        log.info("Working with 'src' directory as FileSystemComponent:");
        demonstrateUniformInterface(srcDir);
        
        // Get a specific file
        File file = new File("README.md", 2048, "/project");
        log.info("\nWorking with 'README.md' file as FileSystemComponent:");
        demonstrateUniformInterface(file);
        
        log.info("\n5. Dynamic Structure Modification:");
        log.info("=".repeat(50));
        
        Directory newDir = new Directory("backup", "/project");
        newDir.add(new File("backup-2024.zip", 1048576, "/project/backup"));
        root.add(newDir);
        
        log.info("Added new 'backup' directory:");
        root.display("");
        
        log.info("\n=== Key Benefits ===");
        log.info("✓ Uniform treatment of individual and composite objects");
        log.info("✓ Natural representation of hierarchical structures");
        log.info("✓ Easy to add new types of components");
        log.info("✓ Clients simplified - don't need to distinguish leaf from composite");
    }
    
    /**
     * Builds a sample file system structure.
     *
     * @return the root directory
     */
    private static Directory buildFileSystem() {
        // Create root directory
        Directory root = new Directory("project", "");
        
        // Create src directory with files
        Directory src = new Directory("src", "/project");
        src.add(new File("Main.java", 1024, "/project/src"));
        src.add(new File("Utils.java", 2048, "/project/src"));
        src.add(new File("Config.java", 512, "/project/src"));
        
        // Create models subdirectory
        Directory models = new Directory("models", "/project/src");
        models.add(new File("User.java", 1536, "/project/src/models"));
        models.add(new File("Product.java", 2560, "/project/src/models"));
        src.add(models);
        
        // Create test directory
        Directory test = new Directory("test", "/project");
        test.add(new File("MainTest.java", 1024, "/project/test"));
        test.add(new File("UtilsTest.java", 1024, "/project/test"));
        
        // Create config directory
        Directory config = new Directory("config", "/project");
        config.add(new File("application.properties", 256, "/project/config"));
        config.add(new File("database.config", 128, "/project/config"));
        
        // Create docs directory
        Directory docs = new Directory("docs", "/project");
        docs.add(new File("README.md", 4096, "/project/docs"));
        docs.add(new File("API.md", 8192, "/project/docs"));
        
        // Assemble structure
        root.add(src);
        root.add(test);
        root.add(config);
        root.add(docs);
        root.add(new File("pom.xml", 2048, "/project"));
        root.add(new File(".gitignore", 256, "/project"));
        
        return root;
    }
    
    /**
     * Demonstrates uniform interface for both leaf and composite.
     *
     * @param component the component to work with
     */
    private static void demonstrateUniformInterface(FileSystemComponent component) {
        // Same operations work for both files and directories
        log.info("  Name: {}", component.getName());
        log.info("  Path: {}", component.getPath());
        log.info("  Size: {} bytes", component.getSize());
        log.info("  Search results for '*.java': {} matches", component.search("*.java").size());
    }
}
