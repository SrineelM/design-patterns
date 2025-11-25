/**
 * Template Method Pattern Implementation.
 * 
 * <h2>Intent</h2>
 * <p>Define the skeleton of an algorithm in an operation, deferring some steps to
 * subclasses. Template Method lets subclasses redefine certain steps without
 * changing the algorithm's structure.</p>
 * 
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Framework classes with customizable behavior</li>
 *   <li>Data processing pipelines</li>
 *   <li>Document generation with different formats</li>
 *   <li>Game AI with standard phases but custom actions</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *   <li>Promotes code reuse through inheritance</li>
 *   <li>Controls which parts of algorithm can be changed</li>
 *   <li>Inverts control - framework calls application code</li>
 * </ul>
 * 
 * <h2>Implementation</h2>
 * <p>This package demonstrates data processing with different formats (CSV, JSON, XML).</p>
 * 
 * @since 1.0
 * @author Design Patterns Demo
 */
package com.example.patterns.behavioral.templatemethod;
