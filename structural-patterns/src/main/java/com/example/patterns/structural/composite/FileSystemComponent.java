package com.example.patterns.structural.composite;

import java.util.List;

/**
 * Component interface for file system elements.
 * 
 * <p>This is the Component interface in the Composite pattern.
 * It declares operations that are common to both leaf and composite objects.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface FileSystemComponent {
    
    /**
     * Gets the name of this component.
     *
     * @return the component name
     */
    String getName();
    
    /**
     * Gets the size of this component in bytes.
     * For files, returns file size. For directories, returns total size of all contents.
     *
     * @return the size in bytes
     */
    long getSize();
    
    /**
     * Displays the component structure with proper indentation.
     *
     * @param indent the indentation string
     */
    void display(String indent);
    
    /**
     * Searches for components matching the given name pattern.
     *
     * @param pattern the search pattern (supports wildcards)
     * @return list of matching components
     */
    List<FileSystemComponent> search(String pattern);
    
    /**
     * Gets the full path of this component.
     *
     * @return the full path
     */
    String getPath();
}
