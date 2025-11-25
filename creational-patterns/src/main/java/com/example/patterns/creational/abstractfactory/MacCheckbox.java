// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/MacCheckbox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Mac-specific implementation of the Checkbox interface.
 *
 * <p>This class provides a Mac-style checkbox with rounded appearance typical of macOS designs.
 *
 * @see Checkbox
 */
public class MacCheckbox implements Checkbox {

  private String label;
  private boolean checked;

  /**
   * Constructs a MacCheckbox with default values.
   */
  public MacCheckbox() {
    this.label = "Mac Checkbox";
    this.checked = false;
  }

  @Override
  public String render() {
    String checkState = checked ? "[X]" : "[ ]";
    return String.format(
        "[%s Mac Checkbox: %s] - Rendered with macOS Aqua styling", checkState, label);
  }

  @Override
  public boolean isChecked() {
    return checked;
  }

  @Override
  public void setChecked(boolean checked) {
    this.checked = checked;
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
    return "MacCheckbox{" + "label='" + label + '\'' + ", checked=" + checked + '}';
  }
}
