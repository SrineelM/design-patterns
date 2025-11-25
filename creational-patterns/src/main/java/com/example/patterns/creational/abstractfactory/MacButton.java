// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/MacButton.java

package com.example.patterns.creational.abstractfactory;

/**
 * Mac-specific implementation of the Button interface.
 *
 * <p>This class provides a concrete implementation of a button designed for macOS platforms.
 * Mac buttons are typically rounded with a soft appearance and smooth interactions that reflect
 * Apple's design philosophy.
 *
 * @see Button
 */
public class MacButton implements Button {

  private String label;

  /**
   * Constructs a MacButton with a default label.
   */
  public MacButton() {
    this.label = "Mac Button";
  }

  @Override
  public String render() {
    return String.format(
        "[Mac Button: %s] - Rendered with Mac native styling (rounded corners, soft shadows)",
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
    return "MacButton{" + "label='" + label + '\'' + '}';
  }
}
