package com.example.patterns.common;

import java.lang.annotation.*;

/**
 * Annotation to mark classes and methods that demonstrate design patterns.
 * 
 * This annotation documents the pattern type, the GoF category, and provides
 * a description of the pattern's purpose and use case.
 * 
 * Example:
 * <pre>
 * @DesignPattern(
 *     name = "Singleton",
 *     category = "Creational",
 *     description = "Ensures a class has only one instance with global access"
 * )
 * public class DatabaseConnection {
 *     // implementation
 * }
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DesignPattern {
    
    /**
     * The name of the design pattern.
     *
     * @return the pattern name (e.g., "Singleton", "Factory Method")
     */
    String name();
    
    /**
     * The category of the pattern (Creational, Structural, or Behavioral).
     *
     * @return the pattern category
     */
    String category();
    
    /**
     * A description of the pattern's purpose and intent.
     *
     * @return the pattern description
     */
    String description() default "";
}
