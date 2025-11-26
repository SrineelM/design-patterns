// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/UIApplication.java

package com.example.patterns.creational.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.patterns.common.DesignPattern;

/**
 * Application demonstrating the Abstract Factory pattern.
 *
 * <p>This class shows a real-world example of how to use the Abstract Factory pattern to create
 * cross-platform UI components. The application doesn't need to know which specific UI factory is
 * being used; it simply uses the factory interface to create components.
 *
 * <p><b>Key Benefits Demonstrated:</b>
 * <ul>
 *   <li>The application code is independent of concrete UI implementations
 *   <li>Adding a new platform only requires a new concrete factory and product classes
 *   <li>The factory selection can be changed without modifying application code
 * </ul>
 *
 * <p><b>Typical Usage Flow:</b>
 * <ol>
 *   <li>Select appropriate factory (based on OS or configuration)
 *   <li>Use factory to create a family of related components
 *   <li>All components work together seamlessly
 * </ol>
 *
 * @see UIComponentFactory
 * @see UIFactorySelector
 * @see DesignPattern
 */
@DesignPattern(
    name = "Abstract Factory",
    description = "Provides interface for creating families of related objects",
    category = "Creational")
public class UIApplication {
    
    private static final Logger log = LoggerFactory.getLogger(UIApplication.class);

  private final UIComponentFactory factory;
  private final Button button;
  private final Checkbox checkbox;
  private final TextBox textBox;

  /**
   * Constructs a UIApplication with the specified factory.
   *
   * <p>This constructor demonstrates how the application uses the factory to create a family of
   * related UI components. Notice that the application doesn't care about concrete classes;
   * it only works with the factory interface.
   *
   * @param factory the UIComponentFactory to use for creating components
   */
  public UIApplication(UIComponentFactory factory) {
    this.factory = factory;
    this.button = factory.createButton();
    this.checkbox = factory.createCheckbox();
    this.textBox = factory.createTextBox();

    // Configure the components
    button.setLabel("Click Me");
    checkbox.setLabel("I agree to the terms");
    textBox.setPlaceholder("Enter your name...");
  }

  /**
   * Renders all UI components created by this application.
   *
   * <p>This method demonstrates that all components are from the same family and work together
   * consistently.
   *
   * @return a formatted string showing all rendered components
   */
  public String renderUI() {
    StringBuilder output = new StringBuilder();
    output.append("=== UI Application (Platform: ")
        .append(factory.getPlatformName())
        .append(") ===\n");
    output.append(button.render()).append("\n");
    output.append(checkbox.render()).append("\n");
    output.append(textBox.render()).append("\n");
    return output.toString();
  }

  /**
   * Demonstrates user interaction with the UI components.
   *
   * @return a string describing the user interaction
   */
  public String simulateUserInteraction() {
    StringBuilder output = new StringBuilder();
    output.append("\n--- User Interactions ---\n");
    output.append("User clicked button: ").append(button.getLabel()).append("\n");

    checkbox.setChecked(true);
    output.append("User checked checkbox: ").append(checkbox.getLabel()).append("\n");

    textBox.setText("John Doe");
    output.append("User entered text: ").append(textBox.getText()).append("\n");

    output.append(renderUI());
    return output.toString();
  }

  /**
   * Gets the factory being used by this application.
   *
   * @return the UIComponentFactory
   */
  public UIComponentFactory getFactory() {
    return factory;
  }

  /**
   * Main method demonstrating the Abstract Factory pattern in action.
   *
   * <p>This example shows how the same application code can work with different UI families
   * without modification.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println("=".repeat(60));
    System.out.println("Abstract Factory Pattern - Cross-Platform UI Example");
    System.out.println("=".repeat(60));

    // Create applications for each platform
    var platforms = new String[] {"Windows", "Mac", "Linux"};

    for (String platform : platforms) {
      try {
        // Select the appropriate factory based on platform
        UIComponentFactory factory = UIFactorySelector.getFactory(platform);

        // Create application with the selected factory
        var app = new UIApplication(factory);

        // Render and interact
        System.out.println("\n" + app.renderUI());
        System.out.println(app.simulateUserInteraction());
        System.out.println("-".repeat(60));

      } catch (IllegalArgumentException e) {
        System.err.println("Error: " + e.getMessage());
      }
    }

    System.out.println("\n" + "=".repeat(60));
    System.out.println("Demonstration Complete");
    System.out.println("=".repeat(60));

    // Demonstrate factory auto-detection based on current OS
    System.out.println("\nAuto-detected Factory for Current OS:");
    UIComponentFactory autoFactory = UIFactorySelector.getFactory();
    System.out.println("Factory: " + autoFactory);
    var autoApp = new UIApplication(autoFactory);
    System.out.println(autoApp.renderUI());
  }
}
