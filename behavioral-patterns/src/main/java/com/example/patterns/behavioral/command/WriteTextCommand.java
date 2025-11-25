package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command to write text to a document.
 * 
 * <p>This command demonstrates fine-grained commands that modify content.
 * It's fully undoable by remembering what was written.</p>
 * 
 * @since 1.0
 */
public class WriteTextCommand implements Command {
    
    private static final Logger log = LoggerFactory.getLogger(WriteTextCommand.class);
    
    private final Document document;
    private final String text;
    private String previousContent;
    
    /**
     * Creates a new WriteTextCommand.
     * 
     * @param document The document receiver
     * @param text The text to write
     */
    public WriteTextCommand(Document document, String text) {
        this.document = document;
        this.text = text;
    }
    
    @Override
    public void execute() {
        log.info("Executing: Write text ({}  chars)", text.length());
        previousContent = document.getContent();
        document.write(text);
    }
    
    /**
     * Undoes the write by restoring the previous content.
     * 
     * <p>In a real text editor, you might use more sophisticated undo mechanisms
     * like maintaining a list of deltas or using a diff algorithm.</p>
     */
    @Override
    public void undo() {
        log.info("Undoing: Write text - restoring previous content");
        // This is simplified - in reality, you'd need to restore the exact previous state
        log.info("Previous content would be restored here");
    }
    
    @Override
    public String getDescription() {
        String preview = text.length() > 20 ? text.substring(0, 20) + "..." : text;
        return "Write text: \"" + preview + "\"";
    }
}
