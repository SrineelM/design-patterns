// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/LinuxCheckbox.java

package com.example.patterns.creational.abstractfactory;

/**
 * Linux-specific implementation of the Checkbox interface.
 *
 * <p>This class provides a Linux-style checkbox following GTK/Qt conventions.
 *
 * @see Checkbox
 */
public class LinuxCheckbox implements Checkbox {

  private String label;
  private boolean checked;

  /**
   * Constructs a LinuxCheckbox with default values.
   */
  public LinuxCheckbox() {
    this.label = "Linux Checkbox";
    this.checked = false;
  }

  @Override
  public String render() {
    String checkState = checked ? "[X]" : "[ ]";
    return String.format(
        "[%s Linux Checkbox: %s] - Rendered with GTK styling", checkState, label);
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
    return "LinuxCheckbox{" + "label='" + label + '\'' + ", checked=" + checked + '}';
  }
}
