package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command to save a document.
 * 
 * <p>This command demonstrates a simple command that doesn't need complex undo logic.
 * Saving is typically not undoable (you can't "unsave" a file), but you could
 * implement undo by restoring from a backup if needed.</p>
 * 
 * <h3>When Commands Don't Need Undo:</h3>
 * <p>Not all operations make sense to undo:</p>
 * <ul>
 *   <li>Print commands (can't unprint paper)</li>
 *   <li>Send email commands (can't unsend in many cases)</li>
 *   <li>Save commands (though you could restore from backup)</li>
 * </ul>
 * 
 * @since 1.0
 */
public class SaveDocumentCommand implements Command {
    
    private static final Logger log = LoggerFactory.getLogger(SaveDocumentCommand.class);
    
    private final Document document;
    
    /**
     * Creates a new SaveDocumentCommand.
     * 
     * @param document The document receiver that will perform the save
     */
    public SaveDocumentCommand(Document document) {
        this.document = document;
    }
    
    /**
     * Executes the save command.
     * 
     * <p>This delegates to the Document's save() method. The command doesn't
     * know HOW to save - it just knows it should ask the document to save itself.</p>
     */
    @Override
    public void execute() {
        String docName = document.getCurrentDocumentName();
        if (docName != null) {
            log.info("Executing: Save document '{}'", docName);
            document.save();
        } else {
            log.warn("Cannot save - no document is currently open");
        }
    }
    
    /**
     * Undo is not supported for save operations.
     * 
     * <p>In a real application, you might implement this by:</p>
     * <ul>
     *   <li>Keeping a backup of the previous version</li>
     *   <li>Using version control systems</li>
     *   <li>Maintaining a save history</li>
     * </ul>
     */
    @Override
    public void undo() {
        log.warn("Undo is not supported for save operations");
        // In a real implementation, you might restore from a backup
    }
    
    @Override
    public String getDescription() {
        String docName = document.getCurrentDocumentName();
        return docName != null ? "Save document: " + docName : "Save document";
    }
}
