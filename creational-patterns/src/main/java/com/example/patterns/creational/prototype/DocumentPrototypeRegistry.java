package com.example.patterns.creational.prototype;

import com.example.patterns.common.DesignPattern;
import java.util.*;

/**
 * Document Prototype Registry
 * 
 * This class manages a registry of document prototypes.
 * It provides methods to register prototypes and create clones from them.
 * 
 * This is a common pattern when working with prototypes:
 * 1. Store prototype instances in a registry
 * 2. Clone from the registry instead of creating new instances
 * 3. Faster than creating objects from scratch
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Prototype",
    category = "Creational",
    description = "Registry for managing document prototypes"
)
public class DocumentPrototypeRegistry {
    
    private static final Map<String, Document> prototypes = new HashMap<>();
    
    /**
     * Registers a document prototype.
     * 
     * The prototype is stored by name and can be cloned later.
     * If a prototype with the same name exists, it will be replaced.
     * 
     * Example:
     * <pre>
     * Document letterTemplate = new WordDocument();
     * letterTemplate.setContent("Dear Sir/Madam...");
     * DocumentPrototypeRegistry.registerPrototype("businessLetter", letterTemplate);
     * </pre>
     * 
     * @param name the name of the prototype
     * @param prototype the document prototype to register
     */
    public static void registerPrototype(String name, Document prototype) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Prototype name cannot be null or empty");
        }
        if (prototype == null) {
            throw new IllegalArgumentException("Prototype cannot be null");
        }
        prototypes.put(name, prototype);
    }
    
    /**
     * Creates a clone of a registered prototype.
     * 
     * The original prototype is never modified by this operation.
     * Each call returns a new independent clone.
     * 
     * Example:
     * <pre>
     * Document letter = DocumentPrototypeRegistry.createDocument("businessLetter");
     * letter.setContent("Modified content...");
     * // Original prototype remains unchanged
     * </pre>
     * 
     * @param name the name of the prototype to clone
     * @return a new clone of the prototype
     * @throws IllegalArgumentException if prototype not found
     */
    public static Document createDocument(String name) {
        if (!prototypes.containsKey(name)) {
            throw new IllegalArgumentException("Prototype not found: " + name);
        }
        
        try {
            Document original = prototypes.get(name);
            return original.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Failed to clone prototype: " + name, e);
        }
    }
    
    /**
     * Gets a list of all registered prototype names.
     * 
     * @return collection of prototype names
     */
    public static Collection<String> getPrototypeNames() {
        return new ArrayList<>(prototypes.keySet());
    }
    
    /**
     * Clears all registered prototypes.
     */
    public static void clearRegistry() {
        prototypes.clear();
    }
    
    /**
     * Gets the number of registered prototypes.
     * 
     * @return count of prototypes
     */
    public static int getPrototypeCount() {
        return prototypes.size();
    }
}
