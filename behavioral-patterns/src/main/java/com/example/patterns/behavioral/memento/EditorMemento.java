package com.example.patterns.behavioral.memento;

import java.time.LocalDateTime;

/**
 * Memento that stores the state of a text editor.
 * 
 * <p>This is the Memento in the pattern. Key characteristics:</p>
 * <ul>
 *   <li>Immutable - implemented as a Java 17 record</li>
 *   <li>Contains all state needed to restore the editor</li>
 *   <li>Opaque to clients (they can't see or modify internals)</li>
 * </ul>
 * 
 * <h3>Why Use Records?</h3>
 * <p>Java 17 records are perfect for mementos because they're:</p>
 * <ul>
 *   <li>Immutable by default</li>
 *   <li>Concise (no boilerplate)</li>
 *   <li>Automatically provide equals/hashCode</li>
 * </ul>
 * 
 * <h3>Memento Pattern Structure:</h3>
 * <pre>
 * Originator (TextEditor) → creates → Memento (EditorMemento)
 *                                           ↓
 * Caretaker (EditorHistory) <- stores <- Memento
 *                                           |
 * Originator <- restores from <- Memento
 * </pre>
 * 
 * @param content The text content
 * @param cursorPosition The cursor position in the text
 * @param timestamp When this state was captured
 * @param description A description of this save point
 * 
 * @since 1.0
 */
public record EditorMemento(
    String content,
    int cursorPosition,
    LocalDateTime timestamp,
    String description
) {
    /**
     * Creates a memento with automatic timestamp.
     */
    public static EditorMemento create(String content, int cursorPosition, String description) {
        return new EditorMemento(content, cursorPosition, LocalDateTime.now(), description);
    }
}
