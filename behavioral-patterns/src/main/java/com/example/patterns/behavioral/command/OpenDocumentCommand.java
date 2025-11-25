package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command to open a document.
 * 
 * <p>This is a concrete command that encapsulates the action of opening a document.
 * It stores all the information needed to execute the command (the document name
 * and the receiver that will perform the action).</p>
 * 
 * <h3>Command Pattern Structure:</h3>
 * <pre>
 * Client → creates → OpenCommand(document, "file.txt")
 *                         ↓
 *                    stores receiver and parameters
 *                         ↓
 * Invoker → calls → execute()
 *                         ↓
 *                    document.open("file.txt")
 * </pre>
 * 
 * @since 1.0
 */
public class OpenDocumentCommand implements Command {
    
    private static final Logger log = LoggerFactory.getLogger(OpenDocumentCommand.class);
    
    private final Document document;
    private final String documentName;
    private String previousDocument; // For undo
    
    /**
     * Creates a new OpenDocumentCommand.
     * 
     * @param document The document receiver that will perform the action
     * @param documentName The name of the document to open
     */
    public OpenDocumentCommand(Document document, String documentName) {
        this.document = document;
        this.documentName = documentName;
    }
    
    /**
     * Executes the command by opening the document.
     * 
     * <p>Before opening, we save the name of the currently open document
     * so we can restore it if undo() is called.</p>
     */
    @Override
    public void execute() {
        log.info("Executing: Open document '{}'", documentName);
        previousDocument = document.getCurrentDocumentName();
        document.open(documentName);
    }
    
    /**
     * Undoes the open operation by closing the document and reopening the previous one.
     * 
     * <p>This demonstrates how commands can maintain state needed for undo operations.</p>
     */
    @Override
    public void undo() {
        log.info("Undoing: Open document '{}'", documentName);
        document.close(documentName);
        
        if (previousDocument != null) {
            document.open(previousDocument);
            log.info("Restored previous document '{}'", previousDocument);
        }
    }
    
    @Override
    public String getDescription() {
        return "Open document: " + documentName;
    }
}
