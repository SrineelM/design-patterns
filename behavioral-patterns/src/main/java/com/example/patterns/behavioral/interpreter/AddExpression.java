package com.example.patterns.behavioral.interpreter;

/**
 * Non-terminal expression for addition.
 * 
 * <p>Non-terminal expressions contain other expressions and define
 * how to combine their results.</p>
 * 
 * @since 1.0
 */
public class AddExpression implements Expression {
    
    private final Expression left;
    private final Expression right;
    
    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
    
    @Override
    public String toString() {
        return "(" + left + " + " + right + ")";
    }
}
