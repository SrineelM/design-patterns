/**
 * Memento Pattern Implementation.
 * 
 * <h2>Intent</h2>
 * <p>Without violating encapsulation, capture and externalize an object's internal
 * state so that the object can be restored to this state later.</p>
 * 
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Undo/Redo functionality in text editors</li>
 *   <li>Database transaction rollback</li>
 *   <li>Game save points and checkpoints</li>
 *   <li>Configuration snapshots</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *   <li>Preserves encapsulation boundaries</li>
 *   <li>Simplifies the originator (no need to track history)</li>
 *   <li>Can provide multiple save points</li>
 * </ul>
 * 
 * <h2>Implementation</h2>
 * <p>This package demonstrates a text editor with undo/redo using Java 17 records
 * as immutable mementos.</p>
 * 
 * @since 1.0
 * @author Design Patterns Demo
 */
package com.example.patterns.behavioral.memento;
