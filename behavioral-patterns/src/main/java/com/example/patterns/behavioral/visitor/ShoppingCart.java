package com.example.patterns.behavioral.visitor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Shopping cart that holds items and accepts visitors.
 * 
 * @since 1.0
 */
@Service
public class ShoppingCart {
    
    private final List<CartItem> items = new ArrayList<>();
    
    public void addItem(CartItem item) {
        items.add(item);
    }
    
    /**
     * Accepts a visitor and applies it to all items.
     * 
     * <p>This is the "object structure" in the Visitor pattern that
     * manages the collection of elements.</p>
     * 
     * @param visitor The visitor to apply
     */
    public void accept(ItemVisitor visitor) {
        items.forEach(item -> item.accept(visitor));
    }
    
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public void clear() {
        items.clear();
    }
}
