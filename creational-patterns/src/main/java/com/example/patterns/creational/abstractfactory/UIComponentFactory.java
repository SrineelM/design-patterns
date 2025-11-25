// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/UIComponentFactory.java

package com.example.patterns.creational.abstractfactory;

/**
 * Abstract Factory interface for creating UI components.
 *
 * <p>This interface defines the contract that all concrete factories must implement. Each concrete
 * factory (WindowsUIFactory, MacUIFactory, LinuxUIFactory) will create a family of related UI
 * components (Button, Checkbox, TextBox) that are designed to work together and maintain
 * consistency with a specific operating system's look and feel.
 *
 * <p>The Abstract Factory pattern ensures that once you create components using one factory, all
 * components will be from the same family, preventing mismatched UI elements.
 *
 * <p><b>Usage Pattern:</b>
 * <pre>
 *   UIComponentFactory factory = new WindowsUIFactory();
 *   Button button = factory.createButton();
 *   Checkbox checkbox = factory.createCheckbox();
 *   TextBox textBox = factory.createTextBox();
 *   // All components are Windows-style
 * </pre>
 *
 * @see WindowsUIFactory
 * @see MacUIFactory
 * @see LinuxUIFactory
 */
public interface UIComponentFactory {

  /**
   * Creates a Button component appropriate for the target platform.
   *
   * <p>The returned button will have platform-specific styling and behavior. For example, a Windows
   * button will look and behave differently from a Mac button, even though both implement the same
   * Button interface.
   *
   * @return a new Button instance specific to this factory's platform
   */
  Button createButton();

  /**
   * Creates a Checkbox component appropriate for the target platform.
   *
   * <p>The returned checkbox will reflect the native appearance and interaction patterns of the
   * target operating system.
   *
   * @return a new Checkbox instance specific to this factory's platform
   */
  Checkbox createCheckbox();

  /**
   * Creates a TextBox component appropriate for the target platform.
   *
   * <p>The returned text box will be styled and behave according to the conventions of the target
   * operating system.
   *
   * @return a new TextBox instance specific to this factory's platform
   */
  TextBox createTextBox();

  /**
   * Retrieves the name of the platform this factory creates components for.
   *
   * @return the platform name (e.g., "Windows", "Mac", "Linux")
   */
  String getPlatformName();
}
