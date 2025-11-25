package com.example.patterns.behavioral.command;

import com.example.patterns.common.DesignPattern;

/**
 * The Command interface defines the contract for all commands.
 * 
 * <p>In the Command pattern, commands are objects that encapsulate all the information
 * needed to perform an action or trigger an event. This includes:</p>
 * <ul>
 *   <li>The method to call</li>
 *   <li>The object that owns the method (receiver)</li>
 *   <li>The parameters to pass</li>
 * </ul>
 * 
 * <h3>Key Methods:</h3>
 * <ul>
 *   <li>{@link #execute()}: Performs the command's action</li>
 *   <li>{@link #undo()}: Reverses the command's action</li>
 *   <li>{@link #getDescription()}: Returns a human-readable description</li>
 * </ul>
 * 
 * <h3>Real-world Analogy:</h3>
 * <p>Think of a restaurant order. The order slip is like a Command object - it contains
 * all the information needed (what to cook, for whom, special instructions). The waiter
 * doesn't need to know how to cook; they just pass the command to the kitchen.</p>
 * 
 * @since 1.0
 */
@DesignPattern(
    name = "Command",
    category = "Behavioral",
    description = "Encapsulates a request as an object with execute() and undo() methods"
)
public interface Command {
    
    /**
     * Executes the command.
     * 
     * <p>This method performs the primary action of the command. For example:</p>
     * <ul>
     *   <li>OpenCommand: Opens a document</li>
     *   <li>SaveCommand: Saves a document</li>
     *   <li>DeleteCommand: Deletes text</li>
     * </ul>
     */
    void execute();
    
    /**
     * Undoes the command's effect.
     * 
     * <p>This method reverses what {@link #execute()} did. Not all commands
     * need to support undo (e.g., a "Print" command), but many do.</p>
     * 
     * <p>For commands that don't support undo, this method can throw an
     * UnsupportedOperationException or simply do nothing.</p>
     */
    void undo();
    
    /**
     * Returns a human-readable description of this command.
     * 
     * <p>Useful for logging, debugging, and displaying command history to users.</p>
     * 
     * @return A description of what this command does
     */
    String getDescription();
}
