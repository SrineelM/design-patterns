// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/WindowsCheckbox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Windows-specific implementation of the Checkbox interface.
 *
 * <p>This class provides a Windows-style checkbox with Windows native appearance and behavior.
 *
 * @see Checkbox
 */
public class WindowsCheckbox implements Checkbox {

  private String label;
  private boolean checked;

  /**
   * Constructs a WindowsCheckbox with default values.
   */
  public WindowsCheckbox() {
    this.label = "Windows Checkbox";
    this.checked = false;
  }

  @Override
  public String render() {
    String checkState = checked ? "[X]" : "[ ]";
    return String.format(
        "[%s Windows Checkbox: %s] - Rendered with Windows native styling", checkState, label);
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
    return "WindowsCheckbox{" + "label='" + label + '\'' + ", checked=" + checked + '}';
  }
}
