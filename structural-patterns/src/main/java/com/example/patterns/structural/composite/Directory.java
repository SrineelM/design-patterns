package com.example.patterns.structural.composite;

import com.example.patterns.common.DesignPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Composite component representing a directory (Composite).
 * 
 * <p>This is a Composite in the Composite pattern. It represents
 * components that can have children. It stores child components and
 * implements child-related operations.
 * 
 * <p><b>Key Responsibilities:</b>
 * <ul>
 *   <li>Store and manage child components</li>
 *   <li>Implement operations by delegating to children</li>
 *   <li>Provide methods to add/remove children</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Composite",
    category = "Structural",
    description = "Composes file system elements into tree structure"
)
public class Directory implements FileSystemComponent {
    
    private final String name;
    private final String parentPath;
    private final List<FileSystemComponent> children;
    
    /**
     * Creates a new directory.
     *
     * @param name the directory name
     * @param parentPath the parent directory path
     */
    public Directory(String name, String parentPath) {
        this.name = name;
        this.parentPath = parentPath;
        this.children = new CopyOnWriteArrayList<>();
    }
    
    /**
     * Adds a child component to this directory.
     *
     * @param component the component to add
     * @throws IllegalArgumentException if component is null
     */
    public void add(FileSystemComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }
        children.add(component);
    }
    
    /**
     * Removes a child component from this directory.
     *
     * @param component the component to remove
     * @return true if removed successfully
     */
    public boolean remove(FileSystemComponent component) {
        return children.remove(component);
    }
    
    /**
     * Gets all children of this directory.
     *
     * @return list of child components
     */
    public List<FileSystemComponent> getChildren() {
        return new ArrayList<>(children);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public long getSize() {
        // Calculate total size by summing all children
        return children.stream()
            .mapToLong(FileSystemComponent::getSize)
            .sum();
    }
    
    @Override
    public void display(String indent) {
        System.out.printf("%s[DIR] %s/ (%s, %d items)%n", 
            indent, 
            name, 
            formatSize(getSize()),
            children.size());
        
        // Recursively display all children
        for (FileSystemComponent child : children) {
            child.display(indent + "  ");
        }
    }
    
    @Override
    public List<FileSystemComponent> search(String pattern) {
        List<FileSystemComponent> results = new ArrayList<>();
        
        // Check if this directory matches
        if (name.contains(pattern) || pattern.equals("*")) {
            results.add(this);
        }
        
        // Recursively search in all children
        for (FileSystemComponent child : children) {
            results.addAll(child.search(pattern));
        }
        
        return results;
    }
    
    @Override
    public String getPath() {
        return parentPath.isEmpty() ? "/" + name : parentPath + "/" + name;
    }
    
    /**
     * Gets the number of files in this directory (recursive).
     *
     * @return total number of files
     */
    public int getFileCount() {
        int count = 0;
        for (FileSystemComponent child : children) {
            if (child instanceof File) {
                count++;
            } else if (child instanceof Directory dir) {
                count += dir.getFileCount();
            }
        }
        return count;
    }
    
    /**
     * Gets the number of directories in this directory (recursive).
     *
     * @return total number of directories
     */
    public int getDirectoryCount() {
        int count = 0;
        for (FileSystemComponent child : children) {
            if (child instanceof Directory dir) {
                count++;
                count += dir.getDirectoryCount();
            }
        }
        return count;
    }
    
    /**
     * Formats size in human-readable format.
     *
     * @param bytes the size in bytes
     * @return formatted size string
     */
    private String formatSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024));
        } else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
        }
    }
}
