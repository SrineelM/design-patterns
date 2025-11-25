// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/Button.java

package com.example.patterns.creational.abstractfactory;

/**
 * Abstract Button interface representing a UI button component.
 *
 * <p>This interface defines the contract that all button implementations must follow across
 * different operating systems. It ensures that regardless of the platform, a button will have
 * consistent behavior and rendering capabilities.
 *
 * @see Checkbox
 * @see TextBox
 */
public interface Button {

  /**
   * Renders the button on the user interface.
   *
   * <p>This method is called to display the button with platform-specific styling and behavior.
   * The implementation will vary based on the concrete class (WindowsButton, MacButton, etc.)
   *
   * @return a String describing what was rendered (useful for testing and logging)
   */
  String render();

  /**
   * Retrieves the display name of the button.
   *
   * @return the button's label text
   */
  String getLabel();

  /**
   * Sets the display name of the button.
   *
   * @param label the new label text
   */
  void setLabel(String label);
}
