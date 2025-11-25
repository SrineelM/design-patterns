/**
 * Command Pattern Implementation.
 * 
 * <h2>Intent</h2>
 * <p>Encapsulate a request as an object, thereby letting you parameterize clients
 * with different requests, queue or log requests, and support undoable operations.</p>
 * 
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Undo/Redo functionality in text editors</li>
 *   <li>Transaction management (commit/rollback)</li>
 *   <li>Job queues and task scheduling</li>
 *   <li>GUI buttons and menu items</li>
 *   <li>Macro recording and playback</li>
 * </ul>
 * 
 * <h2>Benefits</h2>
 * <ul>
 *   <li>Decouples the object that invokes the operation from the one that performs it</li>
 *   <li>Easy to add new commands without changing existing code</li>
 *   <li>Commands can be assembled into composite commands</li>
 *   <li>Supports undo/redo operations</li>
 * </ul>
 * 
 * <h2>Implementation</h2>
 * <p>This package demonstrates a document editor with commands for opening,
 * saving, and closing documents, including undo functionality.</p>
 * 
 * @since 1.0
 * @author Design Patterns Demo
 */
package com.example.patterns.behavioral.command;
