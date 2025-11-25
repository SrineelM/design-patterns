// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/LinuxButton.java

package com.example.patterns.creational.abstractfactory;

/**
 * Linux-specific implementation of the Button interface.
 *
 * <p>This class provides a concrete implementation of a button designed for Linux platforms.
 * Linux buttons typically follow GTK or Qt styling conventions, which emphasize clarity and
 * consistency across desktop environments.
 *
 * @see Button
 */
public class LinuxButton implements Button {

  private String label;

  /**
   * Constructs a LinuxButton with a default label.
   */
  public LinuxButton() {
    this.label = "Linux Button";
  }

  @Override
  public String render() {
    return String.format(
        "[Linux Button: %s] - Rendered with Linux native styling (GTK/Qt convention)",
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
    return "LinuxButton{" + "label='" + label + '\'' + '}';
  }
}
