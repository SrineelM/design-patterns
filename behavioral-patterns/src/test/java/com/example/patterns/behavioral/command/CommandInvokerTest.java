package com.example.patterns.behavioral.command;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Command pattern implementation.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Command Pattern Tests")
class CommandInvokerTest {
    
    @Mock
    private Command mockCommand;
    
    private CommandInvoker invoker;
    
    @BeforeEach
    void setUp() {
        invoker = new CommandInvoker();
    }
    
    @Nested
    @DisplayName("Command Execution Tests")
    class CommandExecutionTests {
        
        @Test
        @DisplayName("Should execute command")
        void shouldExecuteCommand() {
            // Arrange & Act
            invoker.executeCommand(mockCommand);
            
            // Assert
            verify(mockCommand).execute();
        }
        
        @Test
        @DisplayName("Should add command to history after execution")
        void shouldAddCommandToHistory_afterExecution() {
            // Arrange & Act
            invoker.executeCommand(mockCommand);
            
            // Assert
            assertThat(invoker.canUndo()).isTrue();
        }
    }
    
    @Nested
    @DisplayName("Undo Tests")
    class UndoTests {
        
        @Test
        @DisplayName("Should undo last command")
        void shouldUndoLastCommand() {
            // Arrange
            invoker.executeCommand(mockCommand);
            
            // Act
            invoker.undo();
            
            // Assert
            verify(mockCommand).undo();
        }
        
        @Test
        @DisplayName("Should not undo when history is empty")
        void shouldNotUndo_whenHistoryIsEmpty() {
            // Act
            invoker.undo();
            
            // Assert
            verify(mockCommand, never()).undo();
        }
        
        @Test
        @DisplayName("Should undo multiple commands in reverse order")
        void shouldUndoMultipleCommands_inReverseOrder() {
            // Arrange
            Command command1 = mock(Command.class);
            Command command2 = mock(Command.class);
            invoker.executeCommand(command1);
            invoker.executeCommand(command2);
            
            // Act
            invoker.undo(); // Should undo command2
            invoker.undo(); // Should undo command1
            
            // Assert
            verify(command2).undo();
            verify(command1).undo();
        }
    }
    
    @Nested
    @DisplayName("Redo Tests")
    class RedoTests {
        
        @Test
        @DisplayName("Should redo last undone command")
        void shouldRedoLastUndoneCommand() {
            // Arrange
            invoker.executeCommand(mockCommand);
            invoker.undo();
            reset(mockCommand); // Reset to verify redo call specifically
            
            // Act
            invoker.redo();
            
            // Assert
            verify(mockCommand).execute();
        }
        
        @Test
        @DisplayName("Should not redo when redo stack is empty")
        void shouldNotRedo_whenRedoStackIsEmpty() {
            // Act
            invoker.redo();
            
            // Assert
            verify(mockCommand, never()).execute();
        }
        
        @Test
        @DisplayName("Should clear redo stack when new command is executed")
        void shouldClearRedoStack_whenNewCommandExecuted() {
            // Arrange
            Command command1 = mock(Command.class);
            Command command2 = mock(Command.class);
            invoker.executeCommand(command1);
            invoker.undo();
            
            // Act
            invoker.executeCommand(command2); // This should clear redo stack
            
            // Assert
            assertThat(invoker.canRedo()).isFalse();
        }
    }
    
    @Nested
    @DisplayName("Concrete Command Tests")
    class ConcreteCommandTests {
        
        @Test
        @DisplayName("Write text command should append text to document")
        void writeTextCommand_shouldAppendText() {
            // Arrange
            Document document = new Document();
            document.open("test.txt");
            WriteTextCommand command = new WriteTextCommand(document, "Hello World");
            
            // Act
            command.execute();
            
            // Assert
            String content = document.getContent();
            assertThat(content).contains("Hello World");
        }
        
        @Test
        @DisplayName("Open document command should open document")
        void openDocumentCommand_shouldOpenDocument() {
            // Arrange
            Document document = new Document();
            OpenDocumentCommand command = new OpenDocumentCommand(document, "test.txt");
            
            // Act
            command.execute();
            
            // Assert
            assertThat(document.isOpen("test.txt")).isTrue();
        }
        
        @Test
        @DisplayName("Close document command should close document")
        void closeDocumentCommand_shouldCloseDocument() {
            // Arrange
            Document document = new Document();
            document.open("test.txt");
            CloseDocumentCommand command = new CloseDocumentCommand(document, "test.txt");
            
            // Act
            command.execute();
            
            // Assert
            assertThat(document.isOpen("test.txt")).isFalse();
        }
    }
}
