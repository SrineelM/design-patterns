/**
 * Builder Pattern Implementation using Java Records.
 *
 * <p>The Builder pattern is used to construct complex objects step by step. This module demonstrates
 * the Builder pattern using Java Records (introduced in Java 14, finalized in Java 16). Records
 * provide built-in immutability and reduce boilerplate code.
 *
 * <p><b>Key Concepts:</b>
 * <ul>
 *   <li><b>Record:</b> A compact class type for carrying immutable data
 *   <li><b>Builder:</b> A separate class that constructs the record step by step
 *   <li><b>Fluent Interface:</b> Methods return the builder itself for method chaining
 *   <li><b>Immutability:</b> Records are immutable by design
 *   <li><b>Validation:</b> Can validate inputs during construction
 * </ul>
 *
 * <p><b>Benefits:</b>
 * <ul>
 *   <li>Records provide built-in toString(), equals(), and hashCode()
 *   <li>Immutability enforced by the language
 *   <li>Less boilerplate than Lombok
 *   <li>Modern Java approach (Java 14+)
 *   <li>Custom builders provide same flexibility as traditional builder pattern
 * </ul>
 *
 * <p><b>Why custom builder for records?</b>
 * <ul>
 *   <li>Records don't support @Builder from Lombok
 *   <li>Records require all fields in constructor
 *   <li>Custom builder provides optional field handling
 *   <li>Enables fluent API design with records
 * </ul>
 *
 * <p><b>Real-World Use Cases:</b>
 * <ul>
 *   <li>Building HTTP requests with multiple optional headers
 *   <li>Creating complex domain value objects
 *   <li>Building immutable configuration objects
 *   <li>Creating data transfer objects (DTOs)
 * </ul>
 *
 * <p><b>Comparison with Lombok:</b>
 * <ul>
 *   <li>Records: Language feature, immutable by design, less setup
 *   <li>Lombok: Annotation processor, more flexible, wider framework support
 * </ul>
 */
package com.example.patterns.creational.builder.records;
