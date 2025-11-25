/**
 * Iterator Pattern Implementation.
 * 
 * <h2>Intent</h2>
 * <p>Provide a way to access the elements of an aggregate object sequentially
 * without exposing its underlying representation.</p>
 * 
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Traversing collections (lists, trees, graphs) without exposing internal structure</li>
 *   <li>Supporting multiple simultaneous traversals of a collection</li>
 *   <li>Providing a uniform interface for traversing different collection types</li>
 *   <li>Database result set iteration</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *   <li>Supports variations in traversal (forward, backward, filtered)</li>
 *   <li>Simplifies the collection interface</li>
 *   <li>Multiple iterators can traverse the same collection</li>
 *   <li>Decouples collection traversal from collection structure</li>
 * </ul>
 * 
 * <h2>Implementation</h2>
 * <p>This package demonstrates both custom iterators and Java's Iterator interface
 * for a product catalog system.</p>
 * 
 * @since 1.0
 * @author Design Patterns Demo
 */
package com.example.patterns.behavioral.iterator;
