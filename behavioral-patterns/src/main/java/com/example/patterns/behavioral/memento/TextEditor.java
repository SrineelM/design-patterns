package com.example.patterns.behavioral.memento;

import com.example.patterns.common.DesignPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Text editor that can save and restore its state using mementos.
 * 
 * <p>This is the Originator in the Memento pattern. It:</p>
 * <ul>
 *   <li>Creates mementos containing its current state</li>
 *   <li>Restores its state from mementos</li>
 *   <li>Doesn't expose its internal state directly</li>
 * </ul>
 * 
 * <h3>Key Insight:</h3>
 * <p>The editor doesn't know about history management - that's the
 * Caretaker's job. This separation allows the editor to focus on editing
 * while the caretaker focuses on history management.</p>
 * 
 * @since 1.0
 */
@Component
@DesignPattern(
    name = "Memento",
    category = "Behavioral",
    description = "Captures and restores object state without violating encapsulation"
)
public class TextEditor {
    
    private static final Logger log = LoggerFactory.getLogger(TextEditor.class);
    
    /**
     * The current text content.
     */
    private StringBuilder content;
    
    /**
     * The current cursor position.
     */
    private int cursorPosition;
    
    public TextEditor() {
        this.content = new StringBuilder();
        this.cursorPosition = 0;
    }
    
    /**
     * Types text at the current cursor position.
     * 
     * @param text The text to type
     */
    public void type(String text) {
        content.insert(cursorPosition, text);
        cursorPosition += text.length();
        log.info("Typed: '{}' (cursor now at {})", text, cursorPosition);
    }
    
    /**
     * Deletes characters before the cursor (like backspace).
     * 
     * @param count Number of characters to delete
     */
    public void backspace(int count) {
        if (cursorPosition < count) {
            count = cursorPosition;
        }
        
        if (count > 0) {
            content.delete(cursorPosition - count, cursorPosition);
            cursorPosition -= count;
            log.info("Deleted {} characters (cursor now at {})", count, cursorPosition);
        }
    }
    
    /**
     * Moves the cursor to a specific position.
     * 
     * @param position The new cursor position
     */
    public void moveCursor(int position) {
        if (position < 0) position = 0;
        if (position > content.length()) position = content.length();
        
        cursorPosition = position;
        log.info("Moved cursor to position {}", cursorPosition);
    }
    
    /**
     * Creates a memento of the current state.
     * 
     * <p>This is the key method in the Memento pattern. It creates an
     * immutable snapshot of the current state that can be stored and
     * later used to restore.</p>
     * 
     * @param description A description of this save point
     * @return A memento containing the current state
     */
    public EditorMemento save(String description) {
        log.info("Creating memento: {}", description);
        return EditorMemento.create(
            content.toString(),
            cursorPosition,
            description
        );
    }
    
    /**
     * Restores the editor state from a memento.
     * 
     * <p>This demonstrates encapsulation - the memento knows how to
     * restore the editor without exposing internal implementation details.</p>
     * 
     * @param memento The memento to restore from
     */
    public void restore(EditorMemento memento) {
        log.info("Restoring from memento: {} (created at {})", 
                 memento.description(), memento.timestamp());
        
        this.content = new StringBuilder(memento.content());
        this.cursorPosition = memento.cursorPosition();
        
        log.info("Restored state - content length: {}, cursor: {}", 
                 content.length(), cursorPosition);
    }
    
    /**
     * Gets the current content (for display purposes).
     * 
     * @return The current text content
     */
    public String getContent() {
        return content.toString();
    }
    
    /**
     * Gets the current cursor position.
     * 
     * @return The cursor position
     */
    public int getCursorPosition() {
        return cursorPosition;
    }
    
    /**
     * Gets the content with cursor position marked.
     * 
     * @return Content with cursor shown as |
     */
    public String getContentWithCursor() {
        var withCursor = new StringBuilder(content);
        withCursor.insert(cursorPosition, "|");
        return withCursor.toString();
    }
}
