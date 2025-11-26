package com.example.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Leaf component representing a file (Leaf).
 * 
 * <p>This is a Leaf in the Composite pattern. It represents
 * end objects in the composition - objects that have no children.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class File implements FileSystemComponent {
    
    private static final Logger log = LoggerFactory.getLogger(File.class);
    
    private final String name;
    private final long size;
    private final String parentPath;
    
    /**
     * Creates a new file.
     *
     * @param name the file name
     * @param size the file size in bytes
     * @param parentPath the parent directory path
     */
    public File(String name, long size, String parentPath) {
        this.name = name;
        this.size = size;
        this.parentPath = parentPath;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public long getSize() {
        return size;
    }
    
    @Override
    public void display(String indent) {
        log.info("{} [FILE] {} ({})",
            indent,
            name,
            formatSize(size));
    }
    
    @Override
    public List<FileSystemComponent> search(String pattern) {
        List<FileSystemComponent> results = new ArrayList<>();
        if (matches(name, pattern)) {
            results.add(this);
        }
        return results;
    }
    
    @Override
    public String getPath() {
        return parentPath + "/" + name;
    }
    
    /**
     * Formats file size in human-readable format.
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
    
    /**
     * Checks if name matches the pattern (supports * wildcard).
     *
     * @param name the name to check
     * @param pattern the pattern with wildcards
     * @return true if matches
     */
    private boolean matches(String name, String pattern) {
        String regexPattern = pattern
            .replace(".", "\\.")
            .replace("*", ".*");
        return Pattern.matches(regexPattern, name);
    }
    
    /**
     * Add operation is not supported for files.
     * @param component the component to add
     * @throws UnsupportedOperationException always
     */
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add components to a file");
    }
    
    /**
     * Remove operation is not supported for files.
     * @param component the component to remove
     * @return never returns
     * @throws UnsupportedOperationException always
     */
    public boolean remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot remove components from a file");
    }
}
