package com.example.patterns.behavioral.memento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

/**
 * Manages the history of editor states (Caretaker in Memento pattern).
 * 
 * <p>This is the Caretaker in the pattern. It:</p>
 * <ul>
 *   <li>Stores mementos created by the originator</li>
 *   <li>Manages undo/redo stacks</li>
 *   <li>Never examines or modifies memento contents</li>
 *   <li>Treats mementos as opaque objects</li>
 * </ul>
 * 
 * <h3>Caretaker's Role:</h3>
 * <p>The Caretaker doesn't know what's inside the mementos - it just
 * stores and retrieves them. This preserves encapsulation of the
 * originator's internal state.</p>
 * 
 * <h3>History Management:</h3>
 * <pre>
 * State1 → State2 → State3 → Current State
 *          ↑
 *      Can undo to here
 * </pre>
 * 
 * @since 1.0
 */
@Service
public class EditorHistory {
    
    private static final Logger log = LoggerFactory.getLogger(EditorHistory.class);
    
    /**
     * Stack of saved states for undo.
     */
    private final Deque<EditorMemento> undoStack = new ArrayDeque<>();
    
    /**
     * Stack of undone states for redo.
     */
    private final Deque<EditorMemento> redoStack = new ArrayDeque<>();
    
    /**
     * Maximum number of states to keep in history.
     */
    private static final int MAX_HISTORY_SIZE = 50;
    
    /**
     * Saves the current state to history.
     * 
     * <p>When a new state is saved, the redo stack is cleared because
     * you can't redo after making new changes.</p>
     * 
     * @param memento The memento to save
     */
    public void save(EditorMemento memento) {
        log.info("Saving state: {}", memento.description());
        
        undoStack.push(memento);
        
        // Clear redo stack
        if (!redoStack.isEmpty()) {
            log.debug("Clearing redo stack ({} states)", redoStack.size());
            redoStack.clear();
        }
        
        // Limit history size
        if (undoStack.size() > MAX_HISTORY_SIZE) {
            undoStack.removeLast();
            log.debug("Trimmed history to {} states", MAX_HISTORY_SIZE);
        }
        
        log.info("History size: {} (undo), {} (redo)", 
                 undoStack.size(), redoStack.size());
    }
    
    /**
     * Gets the previous state for undo.
     * 
     * <p>Moves the current state to the redo stack.</p>
     * 
     * @return The previous memento, or null if no undo available
     */
    public EditorMemento undo() {
        if (undoStack.isEmpty()) {
            log.warn("Cannot undo - no history available");
            return null;
        }
        
        EditorMemento memento = undoStack.pop();
        redoStack.push(memento);
        
        log.info("Undo to: {} (created at {})", 
                 memento.description(), memento.timestamp());
        log.info("History size: {} (undo), {} (redo)", 
                 undoStack.size(), redoStack.size());
        
        return memento;
    }
    
    /**
     * Gets the next state for redo.
     * 
     * <p>Moves the state back to the undo stack.</p>
     * 
     * @return The next memento, or null if no redo available
     */
    public EditorMemento redo() {
        if (redoStack.isEmpty()) {
            log.warn("Cannot redo - no redo history available");
            return null;
        }
        
        EditorMemento memento = redoStack.pop();
        undoStack.push(memento);
        
        log.info("Redo to: {} (created at {})", 
                 memento.description(), memento.timestamp());
        log.info("History size: {} (undo), {} (redo)", 
                 undoStack.size(), redoStack.size());
        
        return memento;
    }
    
    /**
     * Checks if undo is available.
     * 
     * @return true if there are states to undo to
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }
    
    /**
     * Checks if redo is available.
     * 
     * @return true if there are states to redo to
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
    
    /**
     * Gets the undo history (for display purposes).
     * 
     * @return List of mementos in undo stack
     */
    public List<EditorMemento> getUndoHistory() {
        return new ArrayList<>(undoStack);
    }
    
    /**
     * Gets the redo history (for display purposes).
     * 
     * @return List of mementos in redo stack
     */
    public List<EditorMemento> getRedoHistory() {
        return new ArrayList<>(redoStack);
    }
    
    /**
     * Clears all history.
     */
    public void clear() {
        log.info("Clearing all history");
        undoStack.clear();
        redoStack.clear();
    }
}
