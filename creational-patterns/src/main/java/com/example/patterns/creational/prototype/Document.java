package com.example.patterns.creational.prototype;

/**
 * Prototype Interface for Document Cloning
 * 
 * This interface defines the contract for objects that can be cloned.
 * Implementers must provide a clone() method that creates a deep copy.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface Document extends Cloneable {
    
    /**
     * Creates a deep copy of this document.
     * 
     * The clone must be independent from the original document.
     * Modifications to the clone should not affect the original.
     * 
     * @return a deep copy of this document
     * @throws CloneNotSupportedException if cloning is not supported
     */
    Document clone() throws CloneNotSupportedException;
    
    /**
     * Gets the content of this document.
     * 
     * @return the document content
     */
    String getContent();
    
    /**
     * Sets the content of this document.
     * 
     * @param content the new document content
     */
    void setContent(String content);
    
    /**
     * Gets the type of this document.
     * 
     * @return the document type
     */
    String getType();
}
