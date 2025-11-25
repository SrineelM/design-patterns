/**
 * Abstract Factory Pattern Implementation.
 *
 * <p>The Abstract Factory pattern provides an interface for creating families of related or dependent
 * objects without specifying their concrete classes. This pattern is useful when a system needs to
 * work with multiple families of products that are designed to work together.
 *
 * <p><b>Key Concepts:</b>
 * <ul>
 *   <li><b>Abstract Factory:</b> An interface that declares methods for creating different types of
 *       related products (e.g., UIComponentFactory with methods to create buttons, checkboxes, etc.)
 *   <li><b>Concrete Factory:</b> Implements the abstract factory interface to create product families
 *       specific to a particular variant (e.g., WindowsUIFactory, MacUIFactory)
 *   <li><b>Abstract Product:</b> An interface that defines the contract for a family of products
 *       (e.g., Button interface)
 *   <li><b>Concrete Product:</b> Concrete implementation of abstract product (e.g., WindowsButton,
 *       MacButton)
 * </ul>
 *
 * <p><b>Benefits:</b>
 * <ul>
 *   <li>Ensures products from the same family work together correctly
 *   <li>Centralizes product family creation logic
 *   <li>Makes it easy to add new product families
 *   <li>Promotes loose coupling between client code and concrete product classes
 * </ul>
 *
 * <p><b>Real-World Use Cases:</b>
 * <ul>
 *   <li>Cross-platform UI toolkit (Windows, Mac, Linux UI components)
 *   <li>Database connection factories (MySQL, PostgreSQL, Oracle)
 *   <li>Document rendering engines for different formats (PDF, Word, Excel)
 * </ul>
 *
 * <p><b>Structure:</b>
 * <pre>
 *   UIComponentFactory (interface)
 *       ├── WindowsUIFactory (concrete)
 *       ├── MacUIFactory (concrete)
 *       └── LinuxUIFactory (concrete)
 *
 *   Button, Checkbox, TextBox (interfaces)
 *       ├── Windows*, Mac*, Linux* (concrete)
 *       └── (implementations)
 * </pre>
 */
package com.example.patterns.creational.abstractfactory;
