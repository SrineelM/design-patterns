// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/MacTextBox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Mac-specific implementation of the TextBox interface.
 *
 * <p>This class provides a Mac-style text input box with rounded corners and Mac native appearance.
 *
 * @see TextBox
 */
public class MacTextBox implements TextBox {

  private String text;
  private String placeholder;

  /**
   * Constructs a MacTextBox with default values.
   */
  public MacTextBox() {
    this.text = "";
    this.placeholder = "Enter text...";
  }

  @Override
  public String render() {
    return String.format(
        "[Mac TextBox] - Text: '%s' - Placeholder: '%s' - Rendered with Mac native styling (rounded)",
        text, placeholder);
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String getPlaceholder() {
    return placeholder;
  }

  @Override
  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }

  @Override
  public String toString() {
    return "MacTextBox{" + "text='" + text + '\'' + ", placeholder='" + placeholder + '\'' + '}';
  }
}
