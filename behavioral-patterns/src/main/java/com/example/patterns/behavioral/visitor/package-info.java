/**
 * Visitor Pattern Implementation.
 * 
 * <h2>Intent</h2>
 * <p>Represent an operation to be performed on elements of an object structure.
 * Visitor lets you define a new operation without changing the classes of the
 * elements on which it operates.</p>
 * 
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Compiler abstract syntax tree operations</li>
 *   <li>Document structure processing</li>
 *   <li>Shopping cart with different pricing strategies</li>
 *   <li>Reporting on complex object structures</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *   <li>Easy to add new operations without modifying element classes</li>
 *   <li>Groups related operations in a single class</li>
 *   <li>Can accumulate state while visiting</li>
 * </ul>
 * 
 * <h2>Implementation</h2>
 * <p>This package demonstrates a shopping cart system with different visitors
 * for pricing, taxes, and shipping calculations.</p>
 * 
 * @since 1.0
 * @author Design Patterns Demo
 */
package com.example.patterns.behavioral.visitor;
