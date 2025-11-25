package com.example.patterns.structural.composite;

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
    
    /**
     * Runs the composite pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Composite Pattern Demonstration ===\n");
        
        // Build a file system structure
        Directory root = buildFileSystem();
        
        System.out.println("1. Display File System Structure:");
        System.out.println("=".repeat(50));
        root.display("");
        
        System.out.println("\n2. Calculate Total Size:");
        System.out.println("=".repeat(50));
        System.out.printf("Total size of root: %d bytes%n", root.getSize());
        System.out.printf("Total files: %d%n", root.getFileCount());
        System.out.printf("Total directories: %d%n", root.getDirectoryCount());
        
        System.out.println("\n3. Search Operations:");
        System.out.println("=".repeat(50));
        
        // Search for Java files
        System.out.println("Searching for '*.java' files:");
        List<FileSystemComponent> javaFiles = root.search("*.java");
        for (FileSystemComponent component : javaFiles) {
            System.out.println("  Found: " + component.getPath());
        }
        
        // Search for config files
        System.out.println("\nSearching for 'config' files:");
        List<FileSystemComponent> configFiles = root.search("*config*");
        for (FileSystemComponent component : configFiles) {
            System.out.println("  Found: " + component.getPath());
        }
        
        System.out.println("\n4. Treating Leaf and Composite Uniformly:");
        System.out.println("=".repeat(50));
        
        // Get a specific directory
        Directory srcDir = (Directory) root.getChildren().get(0);
        System.out.println("Working with 'src' directory as FileSystemComponent:");
        demonstrateUniformInterface(srcDir);
        
        // Get a specific file
        File file = new File("README.md", 2048, "/project");
        System.out.println("\nWorking with 'README.md' file as FileSystemComponent:");
        demonstrateUniformInterface(file);
        
        System.out.println("\n5. Dynamic Structure Modification:");
        System.out.println("=".repeat(50));
        
        Directory newDir = new Directory("backup", "/project");
        newDir.add(new File("backup-2024.zip", 1048576, "/project/backup"));
        root.add(newDir);
        
        System.out.println("Added new 'backup' directory:");
        root.display("");
        
        System.out.println("\n=== Key Benefits ===");
        System.out.println("✓ Uniform treatment of individual and composite objects");
        System.out.println("✓ Natural representation of hierarchical structures");
        System.out.println("✓ Easy to add new types of components");
        System.out.println("✓ Clients simplified - don't need to distinguish leaf from composite");
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
        System.out.println("  Name: " + component.getName());
        System.out.println("  Path: " + component.getPath());
        System.out.println("  Size: " + component.getSize() + " bytes");
        System.out.println("  Search results for '*.java': " + 
            component.search("*.java").size() + " matches");
    }
}
