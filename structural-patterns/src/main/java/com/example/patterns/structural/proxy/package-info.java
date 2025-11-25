/**
 * Proxy Pattern Implementation
 * 
 * <p>The Proxy pattern provides a surrogate or placeholder for another object
 * to control access to it. The proxy has the same interface as the real object.
 * 
 * <p><b>Real-world Examples:</b>
 * <ul>
 *   <li>Virtual Proxy - lazy loading of expensive objects</li>
 *   <li>Protection Proxy - access control and authentication</li>
 *   <li>Caching Proxy - cache results of expensive operations</li>
 *   <li>Remote Proxy - represent objects in different address spaces</li>
 *   <li>Spring @Cacheable and AOP proxies</li>
 * </ul>
 * 
 * <p><b>Key Components:</b>
 * <ul>
 *   <li>Subject: Interface for both real object and proxy</li>
 *   <li>RealSubject: The real object being proxied</li>
 *   <li>Proxy: Controls access to the real subject</li>
 * </ul>
 * 
 * @since 1.0.0
 * @version 1.0.0
 */
package com.example.patterns.structural.proxy;
