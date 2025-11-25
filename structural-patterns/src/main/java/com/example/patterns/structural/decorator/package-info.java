/**
 * Decorator Pattern Implementation
 * 
 * <p>The Decorator pattern attaches additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to subclassing for extending functionality.
 * 
 * <p><b>Real-world Examples:</b>
 * <ul>
 *   <li>Coffee with various add-ons (milk, sugar, whipped cream)</li>
 *   <li>Stream wrappers in Java I/O (BufferedInputStream, GZIPInputStream)</li>
 *   <li>UI component decorators (borders, scrollbars)</li>
 *   <li>Spring AOP aspects for cross-cutting concerns</li>
 * </ul>
 * 
 * <p><b>Key Components:</b>
 * <ul>
 *   <li>Component: Interface for objects that can have responsibilities added</li>
 *   <li>ConcreteComponent: The object to which responsibilities can be attached</li>
 *   <li>Decorator: Maintains a reference to a Component and conforms to Component interface</li>
 *   <li>ConcreteDecorator: Adds responsibilities to the component</li>
 * </ul>
 * 
 * @since 1.0.0
 * @version 1.0.0
 */
package com.example.patterns.structural.decorator;
