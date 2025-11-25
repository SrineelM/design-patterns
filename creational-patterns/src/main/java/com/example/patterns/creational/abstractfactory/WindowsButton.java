// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/WindowsButton.java

package com.example.patterns.creational.abstractfactory;

/**
 * Windows-specific implementation of the Button interface.
 *
 * <p>This class provides a concrete implementation of a button designed for Windows platforms.
 * It will render using Windows-style appearance with appropriate visual cues and behavior patterns
 * that Windows users expect.
 *
 * @see Button
 */
public class WindowsButton implements Button {

  private String label;

  /**
   * Constructs a WindowsButton with a default label.
   */
  public WindowsButton() {
    this.label = "Windows Button";
  }

  @Override
  public String render() {
    return String.format(
        "[Windows Button: %s] - Rendered with Windows native styling (rectangular, thin border)",
        label);
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return "WindowsButton{" + "label='" + label + '\'' + '}';
  }
}
