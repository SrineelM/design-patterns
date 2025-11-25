package com.example.patterns.behavioral.interpreter;

import com.example.patterns.common.DesignPattern;

/**
 * Expression interface for the Interpreter pattern.
 * 
 * <p>All expressions (terminal and non-terminal) implement this interface.</p>
 * 
 * @since 1.0
 */
@DesignPattern(
    name = "Interpreter",
    category = "Behavioral",
    description = "Interprets sentences in a language using grammar representations"
)
public interface Expression {
    
    /**
     * Interprets this expression and returns the result.
     * 
     * @return The numeric result of interpreting this expression
     */
    int interpret();
}
