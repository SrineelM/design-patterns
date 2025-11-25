// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/LinuxTextBox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Linux-specific implementation of the TextBox interface.
 *
 * <p>This class provides a Linux-style text input box following GTK/Qt conventions.
 *
 * @see TextBox
 */
public class LinuxTextBox implements TextBox {

  private String text;
  private String placeholder;

  /**
   * Constructs a LinuxTextBox with default values.
   */
  public LinuxTextBox() {
    this.text = "";
    this.placeholder = "Enter text...";
  }

  @Override
  public String render() {
    return String.format(
        "[Linux TextBox] - Text: '%s' - Placeholder: '%s' - Rendered with GTK/Qt native styling",
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
    return "LinuxTextBox{" + "text='" + text + '\'' + ", placeholder='" + placeholder + '\'' + '}';
  }
}
