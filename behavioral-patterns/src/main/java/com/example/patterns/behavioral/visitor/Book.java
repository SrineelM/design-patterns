package com.example.patterns.behavioral.visitor;

/**
 * Book cart item.
 * 
 * @param name Book title
 * @param price Base price
 * @param isbn ISBN number
 * 
 * @since 1.0
 */
public record Book(String name, double price, String isbn) implements CartItem {
    
    @Override
    public void accept(ItemVisitor visitor) {
        // Double dispatch: delegate to visitor's specific method
        visitor.visitBook(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
}
