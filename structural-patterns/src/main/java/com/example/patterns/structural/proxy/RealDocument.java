package com.example.patterns.structural.proxy;

/**
 * Real document implementation.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class RealDocument implements Document {
    
    private final String filename;
    private String content;
    
    /**
     * Creates a real document.
     *
     * @param filename the document filename
     */
    public RealDocument(String filename) {
        this.filename = filename;
        this.content = "Initial content of " + filename;
    }
    
    @Override
    public void view() {
        System.out.printf("[DOCUMENT] Viewing '%s':%n%s%n", filename, content);
    }
    
    @Override
    public void edit(String content) {
        System.out.printf("[DOCUMENT] Editing '%s'%n", filename);
        this.content = content;
    }
    
    @Override
    public void delete() {
        System.out.printf("[DOCUMENT] Deleting '%s'%n", filename);
    }
}
