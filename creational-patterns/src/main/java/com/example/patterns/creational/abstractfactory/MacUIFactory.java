// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/MacUIFactory.java

package com.example.patterns.creational.abstractfactory;

/**
 * Concrete factory for creating Mac UI components.
 *
 * <p>This factory implements the UIComponentFactory interface and creates a family of Mac-style UI
 * components with rounded corners and soft appearance typical of macOS applications.
 *
 * <p><b>Usage Example:</b>
 * <pre>
 *   UIComponentFactory factory = new MacUIFactory();
 *   Button button = factory.createButton();
 *   Checkbox checkbox = factory.createCheckbox();
 *   TextBox textBox = factory.createTextBox();
 *
 *   System.out.println(button.render());      // Mac-style button (rounded)
 *   System.out.println(checkbox.render());    // Mac-style checkbox (rounded)
 *   System.out.println(textBox.render());     // Mac-style text box (rounded)
 * </pre>
 *
 * @see UIComponentFactory
 * @see MacButton
 * @see MacCheckbox
 * @see MacTextBox
 */
public class MacUIFactory implements UIComponentFactory {

  @Override
  public Button createButton() {
    return new MacButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new MacCheckbox();
  }

  @Override
  public TextBox createTextBox() {
    return new MacTextBox();
  }

  @Override
  public String getPlatformName() {
    return "Mac";
  }

  @Override
  public String toString() {
    return "MacUIFactory{platform='" + getPlatformName() + "'}";
  }
}
