package com.example.patterns.structural.flyweight;

import com.example.patterns.common.DesignPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating and managing flyweight character objects.
 * 
 * <p>This is the FlyweightFactory. It creates and manages flyweight objects,
 * ensuring that flyweights are shared properly. When a client requests a
 * flyweight, the factory either returns an existing instance or creates a new one.
 * 
 * <p><b>Key Responsibilities:</b>
 * <ul>
 *   <li>Create flyweight objects on demand</li>
 *   <li>Cache and reuse flyweight objects</li>
 *   <li>Provide statistics on flyweight usage</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Flyweight",
    category = "Structural",
    description = "Shares character objects to minimize memory usage"
)
public class CharacterFactory {
    
    // Pool of flyweight objects
    private final Map<String, Character> characterPool;
    
    /**
     * Creates a new character factory.
     */
    public CharacterFactory() {
        this.characterPool = new HashMap<>();
    }
    
    /**
     * Gets a flyweight character object.
     * 
     * <p>If a character with the given value and font already exists,
     * returns the cached instance. Otherwise, creates a new one.
     *
     * @param value the character value
     * @param fontFamily the font family name
     * @return the flyweight character object
     */
    public Character getCharacter(char value, String fontFamily) {
        String key = createKey(value, fontFamily);
        
        // Return existing flyweight if available
        Character character = characterPool.get(key);
        
        if (character == null) {
            // Create new flyweight if not in pool
            character = new ConcreteCharacter(value, fontFamily);
            characterPool.put(key, character);
        }
        
        return character;
    }
    
    /**
     * Gets the number of flyweight objects in the pool.
     *
     * @return the pool size
     */
    public int getPoolSize() {
        return characterPool.size();
    }
    
    /**
     * Prints statistics about flyweight usage.
     */
    public void printStatistics() {
        System.out.println("\n=== Flyweight Statistics ===");
        System.out.printf("Total flyweight objects in pool: %d%n", characterPool.size());
        System.out.println("Unique characters:");
        
        Map<String, Long> fontCounts = new HashMap<>();
        for (Character ch : characterPool.values()) {
            String font = ch.getFontFamily();
            fontCounts.put(font, fontCounts.getOrDefault(font, 0L) + 1);
        }
        
        fontCounts.forEach((font, count) -> 
            System.out.printf("  - %s: %d characters%n", font, count));
    }
    
    /**
     * Clears the flyweight pool.
     */
    public void clear() {
        characterPool.clear();
    }
    
    /**
     * Creates a unique key for a character.
     *
     * @param value the character value
     * @param fontFamily the font family
     * @return the unique key
     */
    private String createKey(char value, String fontFamily) {
        return value + "-" + fontFamily;
    }
}
