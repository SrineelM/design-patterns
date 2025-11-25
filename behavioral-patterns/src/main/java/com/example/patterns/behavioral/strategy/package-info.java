/**
 * Strategy Pattern Implementation.
 * 
 * <h2>Intent</h2>
 * <p>Define a family of algorithms, encapsulate each one, and make them interchangeable.
 * Strategy lets the algorithm vary independently from clients that use it.</p>
 * 
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Payment processing (credit card, PayPal, cryptocurrency)</li>
 *   <li>Sorting algorithms (quicksort, mergesort, bubblesort)</li>
 *   <li>Compression algorithms (ZIP, RAR, GZIP)</li>
 *   <li>Route calculation (fastest, shortest, scenic)</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *   <li>Eliminates conditional statements for selecting behaviors</li>
 *   <li>Easy to add new strategies without modifying existing code</li>
 *   <li>Strategies can be selected at runtime</li>
 *   <li>Isolates algorithm implementation from code that uses it</li>
 * </ul>
 * 
 * <h2>Implementation</h2>
 * <p>This package demonstrates payment processing strategies with Spring's
 * dependency injection for strategy selection.</p>
 * 
 * @since 1.0
 * @author Design Patterns Demo
 */
package com.example.patterns.behavioral.strategy;
