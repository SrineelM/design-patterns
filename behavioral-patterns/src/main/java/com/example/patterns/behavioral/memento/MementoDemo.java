package com.example.patterns.behavioral.memento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Memento pattern.
 * 
 * <p>This demo shows how the Memento pattern captures and externalizes an object's
 * internal state so that the object can be restored to this state later, without
 * violating encapsulation. This is commonly used for implementing undo/redo functionality.</p>
 * 
 * <p><b>Key Takeaways:</b></p>
 * <ul>
 *   <li>Captures object state without exposing internal structure</li>
 *   <li>Preserves encapsulation boundaries</li>
 *   <li>Enables undo/redo functionality</li>
 *   <li>Can be used for checkpointing and rollback</li>
 *   <li>Memento is immutable - state cannot be changed after creation</li>
 * </ul>
 * 
 * <h3>Real-World Analogy:</h3>
 * <p>Think of a video game's save system. When you save your game, it creates a snapshot
 * of your current progress (position, inventory, health, etc.). Later, you can load that
 * save to restore your game to exactly that state. The save file is like a memento - it
 * captures the state without exposing how the game internally stores that data.</p>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class MementoDemo {
    
    private static final Logger log = LoggerFactory.getLogger(MementoDemo.class);
    
    /**
     * Runs the Memento pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Memento Pattern Demonstration ===\n");
        
        // Create the text editor (Originator) and history manager (Caretaker)
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();
        
        log.info("Simulating a text editor with undo/redo functionality");
        log.info("The editor can save its state and restore it later\n");
        log.info("=".repeat(60));
        
        // Scenario 1: Initial typing
        log.info("\nScenario 1: Initial Typing");
        log.info("-".repeat(60));
        editor.type("Hello");
        log.info("Content: '{}'", editor.getContentWithCursor());
        history.save(editor.save("After typing 'Hello'"));
        
        editor.type(" World");
        log.info("Content: '{}'", editor.getContentWithCursor());
        history.save(editor.save("After typing ' World'"));
        
        editor.type("!");
        log.info("Content: '{}'", editor.getContentWithCursor());
        history.save(editor.save("After typing '!'"));
        
        // Scenario 2: Undo operations
        log.info("\n\nScenario 2: Undo Operations");
        log.info("-".repeat(60));
        log.info("Current content: '{}'", editor.getContent());
        
        log.info("\nUndoing last change...");
        if (history.canUndo()) {
            editor.restore(history.undo());
            log.info("Content after undo: '{}'", editor.getContentWithCursor());
        }
        
        log.info("\nUndoing again...");
        if (history.canUndo()) {
            editor.restore(history.undo());
            log.info("Content after undo: '{}'", editor.getContentWithCursor());
        }
        
        // Scenario 3: Redo operations
        log.info("\n\nScenario 3: Redo Operations");
        log.info("-".repeat(60));
        log.info("Current content: '{}'", editor.getContent());
        
        log.info("\nRedoing last undo...");
        if (history.canRedo()) {
            editor.restore(history.redo());
            log.info("Content after redo: '{}'", editor.getContentWithCursor());
        }
        
        // Scenario 4: New edits clear redo history
        log.info("\n\nScenario 4: New Edits Clear Redo History");
        log.info("-".repeat(60));
        log.info("Current content: '{}'", editor.getContent());
        log.info("Can redo? {}", history.canRedo());
        
        editor.type("?");
        log.info("\nTyped '?' - this clears the redo stack");
        log.info("Content: '{}'", editor.getContentWithCursor());
        history.save(editor.save("After typing '?'"));
        log.info("Can redo now? {} (redo stack was cleared)", history.canRedo());
        
        // Scenario 5: Complex editing session
        log.info("\n\n=== Complex Editing Session ===");
        log.info("=".repeat(60));
        
        // Start fresh
        editor = new TextEditor();
        history = new EditorHistory();
        
        log.info("\nStep 1: Type 'The quick brown fox'");
        editor.type("The quick brown fox");
        history.save(editor.save("Initial text"));
        log.info("Content: '{}'", editor.getContentWithCursor());
        
        log.info("\nStep 2: Move cursor and type ' jumps'");
        editor.type(" jumps");
        history.save(editor.save("Added ' jumps'"));
        log.info("Content: '{}'", editor.getContentWithCursor());
        
        log.info("\nStep 3: Backspace 5 characters");
        editor.backspace(5);
        history.save(editor.save("Removed 'jumps'"));
        log.info("Content: '{}'", editor.getContentWithCursor());
        
        log.info("\nStep 4: Type ' runs'");
        editor.type(" runs");
        history.save(editor.save("Added ' runs'"));
        log.info("Content: '{}'", editor.getContentWithCursor());
        
        // Show history
        log.info("\n\nHistory Stack:");
        log.info("-".repeat(60));
        log.info("History size: {}", history.getHistorySize());
        log.info("Can undo: {}", history.canUndo());
        log.info("Can redo: {}", history.canRedo());
        
        // Undo all changes
        log.info("\n\nUndoing all changes:");
        log.info("-".repeat(60));
        while (history.canUndo()) {
            EditorMemento memento = history.undo();
            editor.restore(memento);
            log.info("Restored to: '{}' ({})", 
                editor.getContent(), 
                memento.description()
            );
        }
        
        // Key Benefits Summary
        log.info("\n\n=== Key Benefits ===");
        log.info("✓ Preserves encapsulation - internal state is not exposed");
        log.info("✓ Simplifies originator - doesn't need to manage history");
        log.info("✓ Enables undo/redo functionality");
        log.info("✓ Mementos are immutable - state cannot be corrupted");
        log.info("✓ Can create checkpoints for rollback");
        
        log.info("\n\n=== Pattern Structure ===");
        log.info("Originator (TextEditor)");
        log.info("  ├─ Creates mementos with save()");
        log.info("  └─ Restores from mementos with restore()");
        log.info("\nMemento (EditorMemento)");
        log.info("  └─ Stores state immutably");
        log.info("\nCaretaker (EditorHistory)");
        log.info("  ├─ Manages memento history");
        log.info("  ├─ Provides undo/redo");
        log.info("  └─ Never modifies mementos");
        
        log.info("\n\n=== When to Use ===");
        log.info("• Implementing undo/redo functionality");
        log.info("• Creating checkpoints for rollback");
        log.info("• Saving/loading game state");
        log.info("• Transaction rollback in databases");
        log.info("• Version control systems");
    }
}
