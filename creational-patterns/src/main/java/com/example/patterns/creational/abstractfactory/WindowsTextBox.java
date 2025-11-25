// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/WindowsTextBox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Windows-specific implementation of the TextBox interface.
 *
 * <p>This class provides a Windows-style text input box with Windows native appearance.
 *
 * @see TextBox
 */
public class WindowsTextBox implements TextBox {

  private String text;
  private String placeholder;

  /**
   * Constructs a WindowsTextBox with default values.
   */
  public WindowsTextBox() {
    this.text = "";
    this.placeholder = "Enter text...";
  }

  @Override
  public String render() {
    return String.format(
        "[Windows TextBox] - Text: '%s' - Placeholder: '%s' - Rendered with Windows native styling",
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
    return "WindowsTextBox{"
        + "text='"
        + text
        + '\''
        + ", placeholder='"
        + placeholder
        + '\''
        + '}';
  }
}
