package com.example.patterns.structural.flyweight;

/**
 * Flyweight interface for characters.
 * 
 * <p>This is the Flyweight interface. It defines operations that
 * flyweight objects can perform using both intrinsic and extrinsic state.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface Character {
    
    /**
     * Renders this character at the specified position and size.
     * 
     * <p>The character value and font (intrinsic state) are shared.
     * The position, size, and color (extrinsic state) are unique per instance.
     *
     * @param x the x coordinate (extrinsic state)
     * @param y the y coordinate (extrinsic state)
     * @param fontSize the font size (extrinsic state)
     * @param color the text color (extrinsic state)
     */
    void render(int x, int y, int fontSize, String color);
    
    /**
     * Gets the character value.
     *
     * @return the character
     */
    char getValue();
    
    /**
     * Gets the font family name.
     *
     * @return the font family
     */
    String getFontFamily();
}
