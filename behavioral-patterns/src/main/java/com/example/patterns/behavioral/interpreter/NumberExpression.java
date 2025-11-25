package com.example.patterns.behavioral.interpreter;

/**
 * Terminal expression representing a number.
 * 
 * <p>Terminal expressions are the leaves of the expression tree.
 * They don't contain other expressions.</p>
 * 
 * @param value The numeric value
 * 
 * @since 1.0
 */
public record NumberExpression(int value) implements Expression {
    
    @Override
    public int interpret() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
