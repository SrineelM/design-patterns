package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command to close a document.
 * 
 * <p>This command demonstrates a command with state that allows for proper undo.
 * When we close a document, we remember its name so we can reopen it if needed.</p>
 * 
 * @since 1.0
 */
public class CloseDocumentCommand implements Command {
    
    private static final Logger log = LoggerFactory.getLogger(CloseDocumentCommand.class);
    
    private final Document document;
    private final String documentName;
    private boolean wasOpen;
    
    /**
     * Creates a new CloseDocumentCommand.
     * 
     * @param document The document receiver
     * @param documentName The name of the document to close
     */
    public CloseDocumentCommand(Document document, String documentName) {
        this.document = document;
        this.documentName = documentName;
    }
    
    /**
     * Executes the close command.
     * 
     * <p>We track whether the document was actually open so that undo
     * works correctly.</p>
     */
    @Override
    public void execute() {
        log.info("Executing: Close document '{}'", documentName);
        wasOpen = document.isOpen(documentName);
        document.close(documentName);
    }
    
    /**
     * Undoes the close by reopening the document.
     * 
     * <p>We only reopen if the document was actually open before we closed it.</p>
     */
    @Override
    public void undo() {
        if (wasOpen) {
            log.info("Undoing: Close document '{}' - reopening", documentName);
            document.open(documentName);
        } else {
            log.info("Undo: Document '{}' wasn't open, nothing to restore", documentName);
        }
    }
    
    @Override
    public String getDescription() {
        return "Close document: " + documentName;
    }
}
