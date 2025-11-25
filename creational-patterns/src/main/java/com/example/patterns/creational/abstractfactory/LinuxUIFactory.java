// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/LinuxUIFactory.java

package com.example.patterns.creational.abstractfactory;

/**
 * Concrete factory for creating Linux UI components.
 *
 * <p>This factory implements the UIComponentFactory interface and creates a family of Linux-style
 * UI components following GTK and Qt conventions common in Linux desktop environments.
 *
 * <p><b>Usage Example:</b>
 * <pre>
 *   UIComponentFactory factory = new LinuxUIFactory();
 *   Button button = factory.createButton();
 *   Checkbox checkbox = factory.createCheckbox();
 *   TextBox textBox = factory.createTextBox();
 *
 *   System.out.println(button.render());      // Linux-style button (GTK/Qt)
 *   System.out.println(checkbox.render());    // Linux-style checkbox (GTK/Qt)
 *   System.out.println(textBox.render());     // Linux-style text box (GTK/Qt)
 * </pre>
 *
 * @see UIComponentFactory
 * @see LinuxButton
 * @see LinuxCheckbox
 * @see LinuxTextBox
 */
public class LinuxUIFactory implements UIComponentFactory {

  @Override
  public Button createButton() {
    return new LinuxButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new LinuxCheckbox();
  }

  @Override
  public TextBox createTextBox() {
    return new LinuxTextBox();
  }

  @Override
  public String getPlatformName() {
    return "Linux";
  }

  @Override
  public String toString() {
    return "LinuxUIFactory{platform='" + getPlatformName() + "'}";
  }
}
