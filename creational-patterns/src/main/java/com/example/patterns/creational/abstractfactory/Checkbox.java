// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/Checkbox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Abstract Checkbox interface representing a UI checkbox component.
 *
 * <p>This interface defines the contract for checkbox implementations across different platforms.
 * A checkbox allows users to select or deselect an option, with appearance and behavior varying
 * by operating system.
 *
 * @see Button
 * @see TextBox
 */
public interface Checkbox {

  /**
   * Renders the checkbox on the user interface.
   *
   * <p>This method displays the checkbox with platform-specific styling. The visual representation
   * will differ between Windows, Mac, and Linux implementations to maintain native look and feel.
   *
   * @return a String describing what was rendered
   */
  String render();

  /**
   * Checks if the checkbox is currently selected.
   *
   * @return true if checked, false otherwise
   */
  boolean isChecked();

  /**
   * Sets the checked state of the checkbox.
   *
   * @param checked the new state
   */
  void setChecked(boolean checked);

  /**
   * Retrieves the text label associated with the checkbox.
   *
   * @return the label text
   */
  String getLabel();

  /**
   * Sets the text label for the checkbox.
   *
   * @param label the label text
   */
  void setLabel(String label);
}
