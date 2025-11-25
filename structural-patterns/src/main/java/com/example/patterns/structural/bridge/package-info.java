/**
 * Bridge Pattern Implementation
 * 
 * <p>The Bridge pattern decouples an abstraction from its implementation so that
 * the two can vary independently. It separates the interface from the implementation,
 * allowing both to evolve without affecting each other.
 * 
 * <p><b>Real-world Examples:</b>
 * <ul>
 *   <li>Remote controls (abstraction) working with different devices (implementations)</li>
 *   <li>Message senders (Email, SMS, Push) with different formatting strategies</li>
 *   <li>Drawing API that works with different rendering engines</li>
 *   <li>Database drivers that work with different database systems</li>
 * </ul>
 * 
 * <p><b>Key Components:</b>
 * <ul>
 *   <li>Abstraction: Defines the abstraction's interface</li>
 *   <li>RefinedAbstraction: Extends the abstraction interface</li>
 *   <li>Implementor: Defines the interface for implementation classes</li>
 *   <li>ConcreteImplementor: Implements the Implementor interface</li>
 * </ul>
 * 
 * @since 1.0.0
 * @version 1.0.0
 */
package com.example.patterns.structural.bridge;
