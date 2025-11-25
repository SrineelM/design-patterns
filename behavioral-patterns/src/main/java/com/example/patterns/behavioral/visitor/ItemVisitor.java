package com.example.patterns.behavioral.visitor;

import com.example.patterns.common.DesignPattern;

/**
 * Visitor interface for shopping cart items.
 * 
 * <p>The Visitor pattern uses double dispatch to achieve runtime polymorphism.
 * Each visit method corresponds to a different element type.</p>
 * 
 * <h3>Double Dispatch:</h3>
 * <pre>
 * 1. item.accept(visitor)        <- First dispatch (which item type?)
 * 2. visitor.visitBook(this)     <- Second dispatch (which visitor?)
 * </pre>
 * 
 * @since 1.0
 */
@DesignPattern(
    name = "Visitor",
    category = "Behavioral",
    description = "Adds new operations to elements without modifying their classes"
)
public interface ItemVisitor {
    
    /**
     * Visits a book item.
     * 
     * @param book The book to visit
     */
    void visitBook(Book book);
    
    /**
     * Visits an electronics item.
     * 
     * @param electronics The electronics to visit
     */
    void visitElectronics(Electronics electronics);
    
    /**
     * Visits a food item.
     * 
     * @param food The food to visit
     */
    void visitFood(Food food);
}
