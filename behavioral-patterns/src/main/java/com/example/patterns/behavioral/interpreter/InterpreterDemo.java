package com.example.patterns.behavioral.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Interpreter pattern.
 * 
 * <p>This demo shows how the Interpreter pattern defines a grammar for a language
 * and provides an interpreter to process sentences in that language. Each grammar
 * rule is represented by a class, and complex expressions are built by composing
 * these classes.</p>
 * 
 * <p><b>Key Takeaways:</b></p>
 * <ul>
 *   <li>Each grammar rule is represented by a class</li>
 *   <li>Complex expressions are built by composing simple ones</li>
 *   <li>Easy to add new grammar rules (new expression classes)</li>
 *   <li>Useful for simple languages and domain-specific languages (DSLs)</li>
 *   <li>Can become complex for large grammars</li>
 * </ul>
 * 
 * <h3>Real-World Analogy:</h3>
 * <p>Think of a calculator that understands mathematical expressions. Each operation
 * (addition, subtraction, multiplication) is a rule in the grammar, and numbers are
 * terminal expressions. Complex calculations are built by combining these rules.</p>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class InterpreterDemo {
    
    private static final Logger log = LoggerFactory.getLogger(InterpreterDemo.class);
    
    /**
     * Runs the Interpreter pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Interpreter Pattern Demonstration ===\n");
        
        // The parser converts string expressions into expression trees
        ExpressionParser parser = new ExpressionParser();
        
        log.info("This demo uses postfix notation (Reverse Polish Notation)");
        log.info("Examples: '5 3 +' means 5 + 3, '5 3 + 2 *' means (5 + 3) * 2\n");
        log.info("=".repeat(60));
        
        // Scenario 1: Simple addition
        log.info("\nScenario 1: Simple Addition");
        log.info("-".repeat(60));
        String expression1 = "5 3 +";
        log.info("Expression: {}", expression1);
        log.info("Meaning: 5 + 3");
        Expression expr1 = parser.parse(expression1);
        int result1 = expr1.interpret();
        log.info("Result: {}\n", result1);
        
        // Scenario 2: Subtraction
        log.info("\nScenario 2: Subtraction");
        log.info("-".repeat(60));
        String expression2 = "10 3 -";
        log.info("Expression: {}", expression2);
        log.info("Meaning: 10 - 3");
        Expression expr2 = parser.parse(expression2);
        int result2 = expr2.interpret();
        log.info("Result: {}\n", result2);
        
        // Scenario 3: Multiplication
        log.info("\nScenario 3: Multiplication");
        log.info("-".repeat(60));
        String expression3 = "4 5 *";
        log.info("Expression: {}", expression3);
        log.info("Meaning: 4 * 5");
        Expression expr3 = parser.parse(expression3);
        int result3 = expr3.interpret();
        log.info("Result: {}\n", result3);
        
        // Scenario 4: Complex expression with multiple operations
        log.info("\nScenario 4: Complex Expression");
        log.info("-".repeat(60));
        String expression4 = "5 3 + 2 *";
        log.info("Expression: {}", expression4);
        log.info("Meaning: (5 + 3) * 2");
        Expression expr4 = parser.parse(expression4);
        int result4 = expr4.interpret();
        log.info("Result: {}\n", result4);
        
        // Scenario 5: Even more complex
        log.info("\nScenario 5: More Complex Expression");
        log.info("-".repeat(60));
        String expression5 = "10 5 - 3 *";
        log.info("Expression: {}", expression5);
        log.info("Meaning: (10 - 5) * 3");
        Expression expr5 = parser.parse(expression5);
        int result5 = expr5.interpret();
        log.info("Result: {}\n", result5);
        
        // Scenario 6: Nested operations
        log.info("\nScenario 6: Nested Operations");
        log.info("-".repeat(60));
        String expression6 = "15 7 1 + - 3 *";
        log.info("Expression: {}", expression6);
        log.info("Meaning: (15 - (7 + 1)) * 3");
        Expression expr6 = parser.parse(expression6);
        int result6 = expr6.interpret();
        log.info("Result: {}\n", result6);
        
        // Scenario 7: Multiple expressions
        log.info("\n\n=== Batch Processing Multiple Expressions ===");
        log.info("=".repeat(60));
        
        String[] expressions = {
            "2 3 +",           // 5
            "10 4 -",          // 6
            "7 8 *",           // 56
            "20 5 - 3 *",      // 45
            "100 50 + 2 -"     // 148
        };
        
        for (String expr : expressions) {
            Expression parsed = parser.parse(expr);
            int result = parsed.interpret();
            log.info("{} = {}", expr, result);
        }
        
        // Key Benefits Summary
        log.info("\n\n=== Key Benefits ===");
        log.info("✓ Each grammar rule is a separate class (easy to understand)");
        log.info("✓ Easy to add new operations (just add new expression classes)");
        log.info("✓ Grammar is explicitly defined in code structure");
        log.info("✓ Expressions can be built and evaluated dynamically");
        log.info("✓ Good for domain-specific languages (DSLs)");
        
        log.info("\n\n=== Pattern Structure ===");
        log.info("Expression (interface)");
        log.info("  ├─ TerminalExpression (NumberExpression)");
        log.info("  └─ NonTerminalExpression");
        log.info("      ├─ AddExpression");
        log.info("      ├─ SubtractExpression");
        log.info("      └─ MultiplyExpression");
        
        log.info("\n\n=== When to Use ===");
        log.info("• Simple grammars (complex grammars become hard to maintain)");
        log.info("• Domain-specific languages (DSLs)");
        log.info("• Configuration file parsers");
        log.info("• Query languages");
        log.info("• Rule engines");
    }
}
