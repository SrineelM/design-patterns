package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The CommandInvoker is responsible for executing and managing commands.
 * 
 * <p>This is the "Invoker" in the Command pattern. It:</p>
 * <ul>
 *   <li>Executes commands</li>
 *   <li>Maintains command history for undo</li>
 *   <li>Manages redo functionality</li>
 *   <li>Can log commands for auditing</li>
 * </ul>
 * 
 * <h3>Invoker's Role:</h3>
 * <p>The Invoker doesn't know what the commands do - it just knows how to execute
 * them and manage the undo/redo stacks. This separation allows you to:</p>
 * <ul>
 *   <li>Add new commands without changing the invoker</li>
 *   <li>Reuse the same invoker for different applications</li>
 *   <li>Easily add features like macro recording or command batching</li>
 * </ul>
 * 
 * <h3>Undo/Redo Mechanism:</h3>
 * <pre>
 * Execute Command1 → History: [Cmd1]
 * Execute Command2 → History: [Cmd1, Cmd2]
 * Undo            → History: [Cmd1], Redo: [Cmd2]
 * Undo            → History: [], Redo: [Cmd2, Cmd1]
 * Redo            → History: [Cmd1], Redo: [Cmd2]
 * Execute Command3 → History: [Cmd1, Cmd3], Redo: [] (cleared!)
 * </pre>
 * 
 * @since 1.0
 */
@Service
public class CommandInvoker {
    
    private static final Logger log = LoggerFactory.getLogger(CommandInvoker.class);
    
    /**
     * Stack of executed commands for undo operations.
     * We use a Deque (Double-Ended Queue) as a stack for efficient operations.
     */
    private final Deque<Command> commandHistory = new ArrayDeque<>();
    
    /**
     * Stack of undone commands for redo operations.
     */
    private final Deque<Command> redoStack = new ArrayDeque<>();
    
    /**
     * Maximum size of the undo history to prevent memory issues.
     */
    private static final int MAX_HISTORY_SIZE = 100;
    
    /**
     * Executes a command and adds it to the history.
     * 
     * <p>When a new command is executed, the redo stack is cleared because
     * you can't redo something after you've taken a new action.</p>
     * 
     * <h3>Example:</h3>
     * <pre>
     * invoker.executeCommand(new OpenDocumentCommand(doc, "file.txt"));
     * invoker.executeCommand(new WriteTextCommand(doc, "Hello"));
     * invoker.undo(); // Undoes the write
     * invoker.executeCommand(new SaveDocumentCommand(doc)); // Clears redo!
     * </pre>
     * 
     * @param command The command to execute
     */
    public void executeCommand(Command command) {
        log.info("Executing command: {}", command.getDescription());
        
        command.execute();
        
        // Add to history
        commandHistory.push(command);
        
        // Clear redo stack - once you take a new action, you can't redo old undos
        if (!redoStack.isEmpty()) {
            log.debug("Clearing redo stack ({} commands)", redoStack.size());
            redoStack.clear();
        }
        
        // Limit history size to prevent memory issues
        if (commandHistory.size() > MAX_HISTORY_SIZE) {
            commandHistory.removeLast();
        }
        
        log.info("Command history size: {}", commandHistory.size());
    }
    
    /**
     * Undoes the last executed command.
     * 
     * <p>The undone command is moved to the redo stack so it can be re-executed.</p>
     * 
     * @return true if undo was successful, false if there's nothing to undo
     */
    public boolean undo() {
        if (commandHistory.isEmpty()) {
            log.warn("Cannot undo - command history is empty");
            return false;
        }
        
        Command command = commandHistory.pop();
        log.info("Undoing command: {}", command.getDescription());
        
        command.undo();
        redoStack.push(command);
        
        log.info("Undo successful. History size: {}, Redo stack size: {}", 
                 commandHistory.size(), redoStack.size());
        return true;
    }
    
    /**
     * Redoes the last undone command.
     * 
     * <p>The redone command is moved back to the command history.</p>
     * 
     * @return true if redo was successful, false if there's nothing to redo
     */
    public boolean redo() {
        if (redoStack.isEmpty()) {
            log.warn("Cannot redo - redo stack is empty");
            return false;
        }
        
        Command command = redoStack.pop();
        log.info("Redoing command: {}", command.getDescription());
        
        command.execute();
        commandHistory.push(command);
        
        log.info("Redo successful. History size: {}, Redo stack size: {}", 
                 commandHistory.size(), redoStack.size());
        return true;
    }
    
    /**
     * Gets the command history for display or debugging.
     * 
     * @return A copy of the command history
     */
    public Deque<Command> getCommandHistory() {
        return new ArrayDeque<>(commandHistory);
    }
    
    /**
     * Clears all command history and redo stack.
     * 
     * <p>Useful when starting a new session or clearing memory.</p>
     */
    public void clearHistory() {
        log.info("Clearing command history ({} commands) and redo stack ({} commands)", 
                 commandHistory.size(), redoStack.size());
        commandHistory.clear();
        redoStack.clear();
    }
    
    /**
     * Checks if undo is available.
     * 
     * @return true if there are commands to undo
     */
    public boolean canUndo() {
        return !commandHistory.isEmpty();
    }
    
    /**
     * Checks if redo is available.
     * 
     * @return true if there are commands to redo
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
}
