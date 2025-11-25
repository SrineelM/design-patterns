package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * The Document class represents the receiver in the Command pattern.
 * 
 * <p>The receiver is the object that performs the actual work when a command is executed.
 * In this case, the Document class maintains the state (open documents, content) and
 * provides methods that commands can call.</p>
 * 
 * <h3>Receiver's Role:</h3>
 * <p>While Commands know WHAT to do, the Receiver knows HOW to do it. The separation
 * allows you to change either independently:</p>
 * <ul>
 *   <li>Add new commands without changing the Document</li>
 *   <li>Change Document implementation without changing commands</li>
 * </ul>
 * 
 * @since 1.0
 */
@Component
public class Document {
    
    private static final Logger log = LoggerFactory.getLogger(Document.class);
    
    /**
     * Stores open documents by name and their content.
     * In a real application, this might be file handles or database connections.
     */
    private final Map<String, String> openDocuments = new HashMap<>();
    
    /**
     * The currently active document name.
     */
    private String currentDocument;
    
    /**
     * Opens a document with the given name.
     * 
     * <p>If the document doesn't exist in memory, it creates a new empty document.
     * In a real application, this would load from disk or database.</p>
     * 
     * @param name The name of the document to open
     */
    public void open(String name) {
        if (openDocuments.containsKey(name)) {
            log.info("Document '{}' is already open, switching to it", name);
        } else {
            openDocuments.put(name, "");
            log.info("Opened new document '{}'", name);
        }
        currentDocument = name;
    }
    
    /**
     * Saves the current document.
     * 
     * <p>In a real application, this would write to disk, database, or cloud storage.</p>
     * 
     * @return true if save was successful, false if no document is open
     */
    public boolean save() {
        if (currentDocument == null) {
            log.warn("No document is currently open to save");
            return false;
        }
        
        String content = openDocuments.get(currentDocument);
        log.info("Saved document '{}' with {} characters", 
                 currentDocument, content.length());
        // In real implementation: write to disk/database
        return true;
    }
    
    /**
     * Closes the specified document.
     * 
     * @param name The name of the document to close
     * @return true if the document was closed, false if it wasn't open
     */
    public boolean close(String name) {
        if (openDocuments.remove(name) != null) {
            log.info("Closed document '{}'", name);
            if (name.equals(currentDocument)) {
                currentDocument = null;
            }
            return true;
        }
        log.warn("Cannot close '{}' - document is not open", name);
        return false;
    }
    
    /**
     * Writes text to the current document.
     * 
     * @param text The text to append
     */
    public void write(String text) {
        if (currentDocument == null) {
            log.warn("No document is open for writing");
            return;
        }
        
        String currentContent = openDocuments.get(currentDocument);
        openDocuments.put(currentDocument, currentContent + text);
        log.info("Wrote {} characters to '{}'", text.length(), currentDocument);
    }
    
    /**
     * Gets the content of the current document.
     * 
     * @return The document content, or empty string if no document is open
     */
    public String getContent() {
        if (currentDocument == null) {
            return "";
        }
        return openDocuments.getOrDefault(currentDocument, "");
    }
    
    /**
     * Gets the name of the currently active document.
     * 
     * @return The current document name, or null if none is open
     */
    public String getCurrentDocumentName() {
        return currentDocument;
    }
    
    /**
     * Checks if a document is open.
     * 
     * @param name The document name to check
     * @return true if the document is open
     */
    public boolean isOpen(String name) {
        return openDocuments.containsKey(name);
    }
}
