/**
 * Builder Pattern Implementation using Lombok.
 *
 * <p>The Builder pattern is used to construct complex objects step by step. This module demonstrates
 * the Builder pattern using Lombok annotations, which significantly reduces boilerplate code.
 *
 * <p><b>Key Concepts:</b>
 * <ul>
 *   <li><b>Builder:</b> A class that constructs the target object step by step
 *   <li><b>Fluent Interface:</b> Methods return the builder itself for method chaining
 *   <li><b>Immutability:</b> Once built, the object is immutable (no setters)
 *   <li><b>Validation:</b> Can validate inputs during construction
 * </ul>
 *
 * <p><b>Benefits:</b>
 * <ul>
 *   <li>Makes code more readable and maintainable
 *   <li>Handles optional and required fields gracefully
 *   <li>Reduces constructor overloading
 *   <li>Enables fluent API design
 *   <li>Lombok reduces boilerplate significantly
 * </ul>
 *
 * <p><b>Lombok Annotations Used:</b>
 * <ul>
 *   <li>@Builder - Generates the builder pattern implementation
 *   <li>@Getter - Generates getter methods for all fields
 *   <li>@ToString - Generates toString() method
 *   <li>@NonNull - Marks fields as required (null check at runtime)
 * </ul>
 *
 * <p><b>Real-World Use Cases:</b>
 * <ul>
 *   <li>Building HTTP requests with multiple optional headers
 *   <li>Creating complex configuration objects
 *   <li>Building database query objects (SQL builder)
 *   <li>Creating immutable domain objects
 * </ul>
 *
 * <p><b>Comparison with Records:</b>
 * <ul>
 *   <li>Lombok: More flexible, supports mutable objects, wider framework support
 *   <li>Records: Immutable by design, less boilerplate (Java 14+), modern approach
 * </ul>
 */
package com.example.patterns.creational.builder.lombok;
