package com.example.patterns.behavioral.interpreter;

import org.springframework.stereotype.Service;

import java.util.Stack;

/**
 * Expression parser that builds an expression tree from a string.
 * 
 * <p>This demonstrates how to construct the interpreter tree structure
 * from user input. Supports postfix notation for simplicity.</p>
 * 
 * <h3>Postfix Notation Examples:</h3>
 * <pre>
 * "5 3 +" → 5 + 3 = 8
 * "5 3 + 2 *" → (5 + 3) * 2 = 16
 * "10 5 - 3 *" → (10 - 5) * 3 = 15
 * </pre>
 * 
 * @since 1.0
 */
@Service
public class ExpressionParser {
    
    /**
     * Parses a postfix expression string into an Expression tree.
     * 
     * @param postfix The postfix expression (e.g., "5 3 +")
     * @return The root Expression node
     * @throws IllegalArgumentException if expression is invalid
     */
    public Expression parse(String postfix) {
        Stack<Expression> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");
        
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Invalid expression: not enough operands for +");
                    }
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new AddExpression(left, right));
                }
                case "-" -> {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Invalid expression: not enough operands for -");
                    }
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new SubtractExpression(left, right));
                }
                case "*" -> {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Invalid expression: not enough operands for *");
                    }
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new MultiplyExpression(left, right));
                }
                default -> {
                    try {
                        int value = Integer.parseInt(token);
                        stack.push(new NumberExpression(value));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid token: " + token);
                    }
                }
            }
        }
        
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: too many operands");
        }
        
        return stack.pop();
    }
}
