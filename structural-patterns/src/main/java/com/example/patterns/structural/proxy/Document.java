package com.example.patterns.structural.proxy;

import java.util.Set;

/**
 * Subject interface for document operations.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface Document {
    
    /**
     * Views the document.
     */
    void view();
    
    /**
     * Edits the document.
     *
     * @param content the new content
     */
    void edit(String content);
    
    /**
     * Deletes the document.
     */
    void delete();
}
