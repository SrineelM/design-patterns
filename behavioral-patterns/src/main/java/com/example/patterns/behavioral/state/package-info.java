/**
 * State Design Pattern Implementation.
 * 
 * <h2>Intent</h2>
 * <p>Allow an object to alter its behavior when its internal state changes.
 * The object will appear to change its class.</p>
 * 
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Order processing (New → Paid → Shipped → Delivered)</li>
 *   <li>Document workflow (Draft → Review → Approved → Published)</li>
 *   <li>TCP connection states</li>
 *   <li>Vending machine states</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *   <li>Localizes state-specific behavior</li>
 *   <li>Makes state transitions explicit</li>
 *   <li>State objects can be shared</li>
 * </ul>
 * 
 * <h2>Implementations in this Package</h2>
 * <ul>
 *   <li><b>Vanilla (Traditional) Implementation:</b> 
 *       Manual state management using enum and interface pattern</li>
 *   <li><b>Spring State Machine Implementation:</b> 
 *       Framework-based approach with Spring State Machine library</li>
 * </ul>
 * 
 * <h3>Vanilla Implementation Classes:</h3>
 * <ul>
 *   <li>{@code VanillaOrderState} - State interface</li>
 *   <li>{@code VanillaOrderStateEnum} - Concrete state implementations</li>
 *   <li>{@code VanillaOrderContext} - Context that maintains current state</li>
 * </ul>
 * 
 * <h3>Spring State Machine Implementation Classes:</h3>
 * <ul>
 *   <li>{@code StateMachineOrderStates} - State enum</li>
 *   <li>{@code StateMachineOrderEvents} - Event enum</li>
 *   <li>{@code StateMachineOrderConfig} - State machine configuration</li>
 *   <li>{@code StateMachineOrderService} - Service using the state machine</li>
 * </ul>
 * 
 * <p>This package demonstrates both traditional Java and Spring Framework
 * approaches to implementing the State pattern.</p>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since 1.0
 */
package com.example.patterns.behavioral.state;
