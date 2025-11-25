package com.example.patterns.behavioral.iterator;

import com.example.patterns.common.DesignPattern;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * A product catalog that demonstrates the Iterator pattern.
 * 
 * <p>This class shows how to implement Java's {@link Iterator} interface and
 * provide different types of iterators for the same collection.</p>
 * 
 * <h3>Why Use Iterator Pattern?</h3>
 * <p>Without the pattern, clients would need to know if products are stored in
 * an array, list, tree, or database. The iterator hides these details and provides
 * a uniform way to traverse the collection.</p>
 * 
 * <h3>Iterator Types Provided:</h3>
 * <ul>
 *   <li>Standard iterator - all products</li>
 *   <li>Category iterator - products in a specific category</li>
 *   <li>In-stock iterator - only products that are in stock</li>
 * </ul>
 * 
 * @since 1.0
 */
@Component
@DesignPattern(
    name = "Iterator",
    category = "Behavioral",
    description = "Provides sequential access to elements without exposing underlying structure"
)
public class ProductCatalog implements Iterable<Product> {
    
    /**
     * Internal storage - clients don't need to know this is an ArrayList.
     * Could be changed to a different structure without affecting clients.
     */
    private final List<Product> products = new ArrayList<>();
    
    /**
     * Adds a product to the catalog.
     * 
     * @param product The product to add
     */
    public void addProduct(Product product) {
        products.add(product);
    }
    
    /**
     * Returns the standard iterator for all products.
     * 
     * <p>This method is required by the Iterable interface and enables
     * the enhanced for-loop syntax:</p>
     * <pre>
     * for (Product p : catalog) {
     *     // process product
     * }
     * </pre>
     * 
     * @return An iterator over all products
     */
    @Override
    public Iterator<Product> iterator() {
        return new ProductIterator(products);
    }
    
    /**
     * Returns an iterator for products in a specific category.
     * 
     * <p>This demonstrates how to provide specialized iterators for
     * different traversal needs.</p>
     * 
     * @param category The category to filter by
     * @return An iterator over products in the specified category
     */
    public Iterator<Product> categoryIterator(String category) {
        return new FilteredProductIterator(products, p -> p.category().equals(category));
    }
    
    /**
     * Returns an iterator for products that are in stock.
     * 
     * @return An iterator over in-stock products
     */
    public Iterator<Product> inStockIterator() {
        return new FilteredProductIterator(products, Product::inStock);
    }
    
    /**
     * Returns an iterator for products matching a custom predicate.
     * 
     * <p>This uses Java 17 features for maximum flexibility:</p>
     * <pre>
     * var expensive = catalog.filteredIterator(p -> p.price().compareTo(BigDecimal.valueOf(100)) > 0);
     * </pre>
     * 
     * @param predicate The filter condition
     * @return An iterator over products matching the predicate
     */
    public Iterator<Product> filteredIterator(Predicate<Product> predicate) {
        return new FilteredProductIterator(products, predicate);
    }
    
    /**
     * Gets the total number of products.
     * 
     * @return The product count
     */
    public int size() {
        return products.size();
    }
    
    /**
     * Basic iterator implementation that traverses all products.
     * 
     * <p>This is a concrete implementation of the Iterator pattern. It maintains
     * the current position and provides methods to check for more elements and
     * retrieve the next element.</p>
     * 
     * <h3>Iterator State:</h3>
     * <pre>
     * [P1, P2, P3, P4]
     *  ^
     *  currentIndex
     *  
     * After next():
     * [P1, P2, P3, P4]
     *      ^
     *      currentIndex
     * </pre>
     */
    private static class ProductIterator implements Iterator<Product> {
        private final List<Product> products;
        private int currentIndex = 0;
        
        public ProductIterator(List<Product> products) {
            // Create a snapshot to avoid ConcurrentModificationException
            this.products = new ArrayList<>(products);
        }
        
        /**
         * Checks if there are more elements to iterate.
         * 
         * @return true if more elements exist
         */
        @Override
        public boolean hasNext() {
            return currentIndex < products.size();
        }
        
        /**
         * Returns the next element and advances the iterator.
         * 
         * @return The next product
         * @throws NoSuchElementException if no more elements exist
         */
        @Override
        public Product next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more products in catalog");
            }
            return products.get(currentIndex++);
        }
    }
    
    /**
     * Filtered iterator that only returns products matching a predicate.
     * 
     * <p>This demonstrates how iterators can provide different views of the same
     * collection. The filtering logic is encapsulated in the iterator, so clients
     * don't need to implement it themselves.</p>
     * 
     * <h3>Filtering Example:</h3>
     * <pre>
     * Products: [P1(in-stock), P2(out), P3(in-stock), P4(out)]
     * In-stock iterator only returns: [P1, P3]
     * </pre>
     */
    private static class FilteredProductIterator implements Iterator<Product> {
        private final List<Product> products;
        private final Predicate<Product> filter;
        private int currentIndex = 0;
        
        public FilteredProductIterator(List<Product> products, Predicate<Product> filter) {
            this.products = new ArrayList<>(products);
            this.filter = filter;
        }
        
        /**
         * Checks if there are more matching elements.
         * 
         * <p>We need to scan ahead to find the next matching element.</p>
         */
        @Override
        public boolean hasNext() {
            while (currentIndex < products.size()) {
                if (filter.test(products.get(currentIndex))) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }
        
        /**
         * Returns the next matching element.
         */
        @Override
        public Product next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more matching products");
            }
            return products.get(currentIndex++);
        }
    }
}
