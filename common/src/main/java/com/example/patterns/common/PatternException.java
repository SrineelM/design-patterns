package com.example.patterns.common;

/**
 * Base exception for all design pattern operations.
 * 
 * This exception is the root exception for all pattern-related errors.
 * Subclasses should provide specific error handling for individual patterns.
 * 
 * Example:
 * <pre>
 * try {
 *     Pattern pattern = factory.createPattern();
 * } catch (PatternException e) {
 *     logger.error("Failed to create pattern", e);
 * }
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class PatternException extends RuntimeException {
    
    /**
     * Constructs a new PatternException with the specified detail message.
     *
     * @param message the detail message
     */
    public PatternException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new PatternException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public PatternException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new PatternException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public PatternException(Throwable cause) {
        super(cause);
    }
}
