/**
 * Factory Method Design Pattern Implementation
 * 
 * <h2>Pattern Overview</h2>
 * The Factory Method pattern is a creational pattern that provides an interface for creating objects,
 * but lets subclasses or factory methods decide which class to instantiate. This pattern is useful when:
 * <ul>
 *     <li>A class can't anticipate the type of objects it needs to create</li>
 *     <li>A class wants its subclasses to specify the objects it creates</li>
 *     <li>Object creation logic is complex and should be centralized</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *     <li><b>Loose Coupling:</b> Client code doesn't need to know concrete implementation classes</li>
 *     <li><b>Centralized Creation:</b> All object creation logic is in one place</li>
 *     <li><b>Easy to Extend:</b> Adding new payment methods requires minimal changes to client code</li>
 *     <li><b>Single Responsibility:</b> Creation logic is separated from business logic</li>
 * </ul>
 * 
 * <h2>Real-World Use Case: Payment Processing System</h2>
 * This module demonstrates a payment processing system where different payment methods
 * (Credit Card, PayPal, Google Pay) need to be created and processed. The PaymentFactory
 * provides a single point of creation, allowing the application to add new payment methods
 * without changing existing code.
 * 
 * <h2>Structure</h2>
 * <ul>
 *     <li>{@link com.example.patterns.creational.factory.PaymentMethod} - Enum defining available payment methods</li>
 *     <li>{@link com.example.patterns.creational.factory.Payment} - Interface for all payment implementations</li>
 *     <li>{@link com.example.patterns.creational.factory.CreditCardPayment} - Credit card payment implementation</li>
 *     <li>{@link com.example.patterns.creational.factory.PayPalPayment} - PayPal payment implementation</li>
 *     <li>{@link com.example.patterns.creational.factory.GooglePayPayment} - Google Pay payment implementation</li>
 *     <li>{@link com.example.patterns.creational.factory.PaymentFactory} - Factory class for creating payments</li>
 * </ul>
 * 
 * <h2>Example Usage</h2>
 * <pre>
 * // Client code doesn't need to know concrete classes
 * Payment payment = PaymentFactory.createPayment(PaymentMethod.PAY_WITH_CREDIT_CARD);
 * payment.process(100.00, "Order #12345");
 * 
 * // Adding a new payment method only requires:
 * // 1. Create new enum value in PaymentMethod
 * // 2. Create new implementation class
 * // 3. Add case in factory method
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 */
package com.example.patterns.creational.factory;
