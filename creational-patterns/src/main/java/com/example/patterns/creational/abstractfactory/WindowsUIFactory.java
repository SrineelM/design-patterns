// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/WindowsUIFactory.java

package com.example.patterns.creational.abstractfactory;

/**
 * Concrete factory for creating Windows UI components.
 *
 * <p>This factory implements the UIComponentFactory interface and creates a family of Windows-style
 * UI components. All components created by this factory will share a consistent Windows look and
 * feel, ensuring a cohesive user interface.
 *
 * <p><b>Usage Example:</b>
 * <pre>
 *   UIComponentFactory factory = new WindowsUIFactory();
 *   Button button = factory.createButton();
 *   Checkbox checkbox = factory.createCheckbox();
 *   TextBox textBox = factory.createTextBox();
 *
 *   System.out.println(button.render());      // Windows-style button
 *   System.out.println(checkbox.render());    // Windows-style checkbox
 *   System.out.println(textBox.render());     // Windows-style text box
 * </pre>
 *
 * @see UIComponentFactory
 * @see WindowsButton
 * @see WindowsCheckbox
 * @see WindowsTextBox
 */
public class WindowsUIFactory implements UIComponentFactory {

  @Override
  public Button createButton() {
    return new WindowsButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new WindowsCheckbox();
  }

  @Override
  public TextBox createTextBox() {
    return new WindowsTextBox();
  }

  @Override
  public String getPlatformName() {
    return "Windows";
  }

  @Override
  public String toString() {
    return "WindowsUIFactory{platform='" + getPlatformName() + "'}";
  }
}
