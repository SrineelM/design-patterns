package com.example.patterns.creational.abstractfactory;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Abstract Factory pattern implementation.
 */
@DisplayName("Abstract Factory Pattern - UI Component Factory Tests")
class UIComponentFactoryTest {
    
    @Nested
    @DisplayName("Windows Factory Tests")
    class WindowsFactoryTests {
        
        private UIComponentFactory factory;
        
        @BeforeEach
        void setUp() {
            factory = new WindowsUIFactory();
        }
        
        @Test
        @DisplayName("Should create Windows button")
        void shouldCreateWindowsButton() {
            // Act
            Button button = factory.createButton();
            
            // Assert
            assertThat(button).isInstanceOf(WindowsButton.class);
            assertThat(button.render()).contains("Windows");
        }
        
        @Test
        @DisplayName("Should create Windows checkbox")
        void shouldCreateWindowsCheckbox() {
            // Act
            Checkbox checkbox = factory.createCheckbox();
            
            // Assert
            assertThat(checkbox).isInstanceOf(WindowsCheckbox.class);
            assertThat(checkbox.render()).contains("Windows");
        }
        
        @Test
        @DisplayName("Should create Windows text box")
        void shouldCreateWindowsTextBox() {
            // Act
            TextBox textBox = factory.createTextBox();
            
            // Assert
            assertThat(textBox).isInstanceOf(WindowsTextBox.class);
            assertThat(textBox.render()).contains("Windows");
        }
    }
    
    @Nested
    @DisplayName("Mac Factory Tests")
    class MacFactoryTests {
        
        private UIComponentFactory factory;
        
        @BeforeEach
        void setUp() {
            factory = new MacUIFactory();
        }
        
        @Test
        @DisplayName("Should create Mac button")
        void shouldCreateMacButton() {
            // Act
            Button button = factory.createButton();
            
            // Assert
            assertThat(button).isInstanceOf(MacButton.class);
            assertThat(button.render()).contains("Mac");
        }
        
        @Test
        @DisplayName("Should create Mac checkbox")
        void shouldCreateMacCheckbox() {
            // Act
            Checkbox checkbox = factory.createCheckbox();
            
            // Assert
            assertThat(checkbox).isInstanceOf(MacCheckbox.class);
            assertThat(checkbox.render()).contains("Mac");
        }
        
        @Test
        @DisplayName("Should create Mac text box")
        void shouldCreateMacTextBox() {
            // Act
            TextBox textBox = factory.createTextBox();
            
            // Assert
            assertThat(textBox).isInstanceOf(MacTextBox.class);
            assertThat(textBox.render()).contains("Mac");
        }
    }
    
    @Nested
    @DisplayName("Linux Factory Tests")
    class LinuxFactoryTests {
        
        private UIComponentFactory factory;
        
        @BeforeEach
        void setUp() {
            factory = new LinuxUIFactory();
        }
        
        @Test
        @DisplayName("Should create Linux button")
        void shouldCreateLinuxButton() {
            // Act
            Button button = factory.createButton();
            
            // Assert
            assertThat(button).isInstanceOf(LinuxButton.class);
            assertThat(button.render()).contains("Linux");
        }
        
        @Test
        @DisplayName("Should create Linux checkbox")
        void shouldCreateLinuxCheckbox() {
            // Act
            Checkbox checkbox = factory.createCheckbox();
            
            // Assert
            assertThat(checkbox).isInstanceOf(LinuxCheckbox.class);
            assertThat(checkbox.render()).contains("Linux");
        }
        
        @Test
        @DisplayName("Should create Linux text box")
        void shouldCreateLinuxTextBox() {
            // Act
            TextBox textBox = factory.createTextBox();
            
            // Assert
            assertThat(textBox).isInstanceOf(LinuxTextBox.class);
            assertThat(textBox.render()).contains("Linux");
        }
    }
    
    @Nested
    @DisplayName("UI Application Tests")
    class UIApplicationTests {
        
        @Test
        @DisplayName("Should create consistent UI family with Windows factory")
        void shouldCreateConsistentUIFamily_withWindowsFactory() {
            // Arrange
            UIComponentFactory factory = new WindowsUIFactory();
            
            // Act
            UIApplication app = new UIApplication(factory);
            String output = app.renderUI();
            
            // Assert
            assertThat(output).contains("Windows");
            assertThat(output).contains("Button");
            assertThat(output).contains("Checkbox");
            assertThat(output).contains("TextBox");
        }
        
        @Test
        @DisplayName("Should create consistent UI family with Mac factory")
        void shouldCreateConsistentUIFamily_withMacFactory() {
            // Arrange
            UIComponentFactory factory = new MacUIFactory();
            
            // Act
            UIApplication app = new UIApplication(factory);
            String output = app.renderUI();
            
            // Assert
            assertThat(output).contains("Mac");
        }
    }
    
    @Nested
    @DisplayName("Component Behavior Tests")
    class ComponentBehaviorTests {
        
        @Test
        @DisplayName("Should set and get button label")
        void shouldSetAndGetButtonLabel() {
            // Arrange
            UIComponentFactory factory = new WindowsUIFactory();
            Button button = factory.createButton();
            
            // Act
            button.setLabel("Click Me");
            
            // Assert
            assertThat(button.getLabel()).isEqualTo("Click Me");
        }
        
        @Test
        @DisplayName("Should check and uncheck checkbox")
        void shouldCheckAndUncheckCheckbox() {
            // Arrange
            UIComponentFactory factory = new MacUIFactory();
            Checkbox checkbox = factory.createCheckbox();
            
            // Act
            checkbox.setChecked(true);
            boolean checked1 = checkbox.isChecked();
            checkbox.setChecked(false);
            boolean checked2 = checkbox.isChecked();
            
            // Assert
            assertThat(checked1).isTrue();
            assertThat(checked2).isFalse();
        }
        
        @Test
        @DisplayName("Should set text box placeholder")
        void shouldSetTextBoxPlaceholder() {
            // Arrange
            UIComponentFactory factory = new LinuxUIFactory();
            TextBox textBox = factory.createTextBox();
            
            // Act
            textBox.setPlaceholder("Enter text...");
            
            // Assert
            assertThat(textBox.getPlaceholder()).isEqualTo("Enter text...");
        }
    }
}
