package com.example.patterns.behavioral.iterator;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a product in our catalog.
 * 
 * <p>This is a Java 17 record - an immutable data carrier. Records automatically provide:</p>
 * <ul>
 *   <li>Constructor</li>
 *   <li>Getters (name(), price(), category())</li>
 *   <li>equals(), hashCode(), toString()</li>
 * </ul>
 * 
 * @param name The product name
 * @param price The product price
 * @param category The product category
 * @param inStock Whether the product is in stock
 * 
 * @since 1.0
 */
public record Product(
    String name,
    BigDecimal price,
    String category,
    boolean inStock
) {
    /**
     * Compact constructor with validation.
     */
    public Product {
        Objects.requireNonNull(name, "Product name cannot be null");
        Objects.requireNonNull(price, "Product price cannot be null");
        Objects.requireNonNull(category, "Product category cannot be null");
        
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
    
    /**
     * Factory method for creating products.
     */
    public static Product of(String name, double price, String category, boolean inStock) {
        return new Product(name, BigDecimal.valueOf(price), category, inStock);
    }
}
