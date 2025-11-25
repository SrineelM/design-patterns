// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/TextBox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Abstract TextBox interface representing a UI text input component.
 *
 * <p>This interface defines the contract for text box implementations across different platforms.
 * A text box allows users to input and edit text, with platform-specific rendering and behavior.
 *
 * @see Button
 * @see Checkbox
 */
public interface TextBox {

  /**
   * Renders the text box on the user interface.
   *
   * <p>Displays the text box with platform-specific styling and native look and feel appropriate
   * for the target operating system.
   *
   * @return a String describing what was rendered
   */
  String render();

  /**
   * Retrieves the current text content in the text box.
   *
   * @return the text content
   */
  String getText();

  /**
   * Sets the text content of the text box.
   *
   * @param text the text to set
   */
  void setText(String text);

  /**
   * Retrieves the placeholder text displayed when the text box is empty.
   *
   * @return the placeholder text
   */
  String getPlaceholder();

  /**
   * Sets the placeholder text displayed when the text box is empty.
   *
   * @param placeholder the placeholder text
   */
  void setPlaceholder(String placeholder);
}
