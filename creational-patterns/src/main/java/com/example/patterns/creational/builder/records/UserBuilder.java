// File path: creational-patterns/src/main/java/com/example/patterns/creational/builder/records/UserBuilder.java

package com.example.patterns.creational.builder.records;

/**
 * Custom Builder for UserRecord.
 *
 * <p>Since Java Records cannot use Lombok's @Builder annotation, this custom builder implements
 * the builder pattern for UserRecord. This builder allows constructing UserRecord objects with
 * optional fields using a fluent API.
 *
 * <p><b>Key Design Points:</b>
 * <ul>
 *   <li>All fields are optional in the builder (with defaults)
 *   <li>Email is required and validated during build()
 *   <li>Builder returns itself for method chaining
 *   <li>build() method validates and constructs the final record
 * </ul>
 *
 * <p><b>Usage Example:</b>
 * <pre>
 *   // Build with required field only
 *   var user1 = new UserBuilder()
 *       .email("john@example.com")
 *       .build();
 *
 *   // Build with optional fields
 *   var user2 = new UserBuilder()
 *       .email("jane@example.com")
 *       .firstName("Jane")
 *       .lastName("Doe")
 *       .phone("+1-555-0123")
 *       .age(28)
 *       .active(true)
 *       .build();
 * </pre>
 *
 * @see UserRecord
 */
public class UserBuilder {

  /** User's email address (required). */
  private String email;

  /** User's first name (optional, defaults to null). */
  private String firstName;

  /** User's last name (optional, defaults to null). */
  private String lastName;

  /** User's phone number (optional, defaults to null). */
  private String phone;

  /** User's age (optional, defaults to 0). */
  private int age;

  /** Whether the user account is active (optional, defaults to false). */
  private boolean active;

  /**
   * Constructs a new UserBuilder with default values.
   *
   * <p>All fields are initialized to their default values (null for objects, 0 for int, false for
   * boolean). The builder is in an incomplete state until email is set and build() is called.
   */
  public UserBuilder() {
    this.email = null;
    this.firstName = null;
    this.lastName = null;
    this.phone = null;
    this.age = 0;
    this.active = false;
  }

  /**
   * Sets the user's email address.
   *
   * <p>Email is a required field. This method must be called before calling build().
   *
   * @param email the user's email address
   * @return this builder for method chaining
   */
  public UserBuilder email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Sets the user's first name.
   *
   * @param firstName the user's first name
   * @return this builder for method chaining
   */
  public UserBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Sets the user's last name.
   *
   * @param lastName the user's last name
   * @return this builder for method chaining
   */
  public UserBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Sets the user's phone number.
   *
   * @param phone the user's phone number
   * @return this builder for method chaining
   */
  public UserBuilder phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Sets the user's age.
   *
   * @param age the user's age
   * @return this builder for method chaining
   * @throws IllegalArgumentException if age is negative
   */
  public UserBuilder age(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be negative");
    }
    this.age = age;
    return this;
  }

  /**
   * Sets whether the user account is active.
   *
   * @param active true if the account should be active, false otherwise
   * @return this builder for method chaining
   */
  public UserBuilder active(boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Builds and returns the UserRecord.
   *
   * <p>This method validates that all required fields are set and constructs the final UserRecord
   * object. Email is required and must be set before calling this method.
   *
   * @return a new UserRecord with the configured values
   * @throws IllegalArgumentException if email is null or empty
   */
  public UserRecord build() {
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email is required and cannot be null or empty");
    }

    return new UserRecord(email, firstName, lastName, phone, age, active);
  }

  /**
   * Gets the current state of the builder as a string.
   *
   * @return a string representation of the builder state
   */
  @Override
  public String toString() {
    return "UserBuilder{"
        + "email='"
        + email
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", age="
        + age
        + ", active="
        + active
        + '}';
  }
}
