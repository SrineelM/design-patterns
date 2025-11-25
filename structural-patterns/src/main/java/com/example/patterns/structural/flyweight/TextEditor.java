package com.example.patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * Text editor that uses flyweight pattern for efficient character rendering.
 * 
 * <p>This is the Client in the Flyweight pattern. It maintains references
 * to flyweight objects and stores/computes extrinsic state.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class TextEditor {
    
    private final CharacterFactory factory;
    private final List<CharacterInstance> document;
    
    /**
     * Creates a new text editor.
     *
     * @param factory the character factory
     */
    public TextEditor(CharacterFactory factory) {
        this.factory = factory;
        this.document = new ArrayList<>();
    }
    
    /**
     * Inserts text into the document.
     *
     * @param text the text to insert
     * @param fontFamily the font family
     * @param fontSize the font size
     * @param color the text color
     */
    public void insertText(String text, String fontFamily, int fontSize, String color) {
        int x = 0;
        int y = 0;
        
        for (char c : text.toCharArray()) {
            if (c == '\n') {
                y += fontSize + 5;
                x = 0;
            } else {
                // Get flyweight character (shared)
                Character character = factory.getCharacter(c, fontFamily);
                
                // Store extrinsic state
                CharacterInstance instance = new CharacterInstance(
                    character, x, y, fontSize, color
                );
                document.add(instance);
                
                x += fontSize / 2;
            }
        }
    }
    
    /**
     * Renders the entire document.
     */
    public void render() {
        System.out.println("\n=== Rendering Document ===");
        for (CharacterInstance instance : document) {
            instance.render();
        }
    }
    
    /**
     * Gets the document size (number of characters).
     *
     * @return the document size
     */
    public int getDocumentSize() {
        return document.size();
    }
    
    /**
     * Calculates memory savings from using flyweight pattern.
     *
     * @return memory savings description
     */
    public String getMemoryStats() {
        int totalChars = document.size();
        int uniqueChars = factory.getPoolSize();
        int memorySavings = ((totalChars - uniqueChars) * 100) / Math.max(totalChars, 1);
        
        return String.format("""
            Memory Statistics:
            - Total characters in document: %d
            - Unique flyweight objects: %d
            - Memory savings: ~%d%%
            - Reuse ratio: %.2f:1
            """,
            totalChars,
            uniqueChars,
            memorySavings,
            totalChars / (double) Math.max(uniqueChars, 1)
        );
    }
    
    /**
     * Clears the document.
     */
    public void clear() {
        document.clear();
    }
    
    /**
     * Instance of a character with extrinsic state.
     * 
     * <p>This stores the extrinsic state (position, size, color) for each
     * character instance, while the intrinsic state (character value, font)
     * is stored in the shared flyweight object.
     */
    private record CharacterInstance(
        Character character,  // Reference to shared flyweight
        int x,               // Extrinsic state
        int y,               // Extrinsic state
        int fontSize,        // Extrinsic state
        String color         // Extrinsic state
    ) {
        void render() {
            character.render(x, y, fontSize, color);
        }
    }
}
