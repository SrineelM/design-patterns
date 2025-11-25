package com.example.patterns.behavioral.visitor;

/**
 * Element interface in the Visitor pattern.
 * 
 * <p>All visitable elements must implement this interface and accept visitors.</p>
 * 
 * @since 1.0
 */
public interface CartItem {
    
    /**
     * Accepts a visitor.
     * 
     * <p>This is the key method in the Visitor pattern. It enables double dispatch
     * by calling the appropriate visit method on the visitor.</p>
     * 
     * @param visitor The visitor to accept
     */
    void accept(ItemVisitor visitor);
    
    /**
     * Gets the item name.
     * 
     * @return The item name
     */
    String getName();
    
    /**
     * Gets the base price.
     * 
     * @return The base price
     */
    double getPrice();
}
