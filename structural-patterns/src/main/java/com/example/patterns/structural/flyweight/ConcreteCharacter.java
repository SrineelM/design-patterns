package com.example.patterns.structural.flyweight;

/**
 * Concrete flyweight character implementation.
 * 
 * <p>This is a ConcreteFlyweight. It stores intrinsic state (character value
 * and font family) that is shared across multiple instances. Extrinsic state
 * (position, size, color) is passed as parameters to operations.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class ConcreteCharacter implements Character {
    
    // Intrinsic state - shared across instances
    private final char value;
    private final String fontFamily;
    
    /**
     * Creates a concrete character flyweight.
     * 
     * <p>This should only be called by the flyweight factory.
     *
     * @param value the character value
     * @param fontFamily the font family name
     */
    ConcreteCharacter(char value, String fontFamily) {
        this.value = value;
        this.fontFamily = fontFamily;
        
        // Simulate loading font resources (expensive operation)
        System.out.printf("[FLYWEIGHT] Creating new character '%c' with font '%s'%n", 
            value, fontFamily);
    }
    
    @Override
    public void render(int x, int y, int fontSize, String color) {
        // Use both intrinsic state (value, fontFamily) and extrinsic state (x, y, fontSize, color)
        System.out.printf("Rendering '%c' [Font: %s, Size: %d, Color: %s] at (%d, %d)%n",
            value, fontFamily, fontSize, color, x, y);
    }
    
    @Override
    public char getValue() {
        return value;
    }
    
    @Override
    public String getFontFamily() {
        return fontFamily;
    }
    
    @Override
    public String toString() {
        return String.format("Character{value='%c', font='%s'}", value, fontFamily);
    }
}
