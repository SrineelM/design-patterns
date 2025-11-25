/**
 * Flyweight Pattern Implementation
 * 
 * <p>The Flyweight pattern uses sharing to support large numbers of fine-grained
 * objects efficiently. It minimizes memory usage by sharing as much data as possible
 * with similar objects.
 * 
 * <p><b>Real-world Examples:</b>
 * <ul>
 *   <li>Character rendering in text editors (share font objects)</li>
 *   <li>Icon caching in file browsers</li>
 *   <li>String pooling in Java</li>
 *   <li>Database connection pooling</li>
 * </ul>
 * 
 * <p><b>Key Components:</b>
 * <ul>
 *   <li>Flyweight: Interface for flyweight objects</li>
 *   <li>ConcreteFlyweight: Implements flyweight interface and stores intrinsic state</li>
 *   <li>FlyweightFactory: Creates and manages flyweight objects</li>
 *   <li>Client: Maintains references to flyweights and computes extrinsic state</li>
 * </ul>
 * 
 * @since 1.0.0
 * @version 1.0.0
 */
package com.example.patterns.structural.flyweight;
