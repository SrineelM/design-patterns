package com.example.patterns.behavioral.visitor;

/**
 * Electronics cart item.
 * 
 * @param name Product name
 * @param price Base price
 * @param warrantyMonths Warranty period in months
 * 
 * @since 1.0
 */
public record Electronics(String name, double price, int warrantyMonths) implements CartItem {
    
    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visitElectronics(this);
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
