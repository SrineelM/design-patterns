// File path: creational-patterns/src/main/java/com/example/patterns/creational/builder/lombok/UserLombok.java

package com.example.patterns.creational.builder.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * User class demonstrating the Builder pattern with Lombok annotations.
 *
 * <p>This class uses Lombok's @Builder annotation to automatically generate the builder pattern
 * implementation. Lombok significantly reduces boilerplate code while providing a clean, readable
 * API.
 *
 * <p><b>Lombok Annotations:</b>
 * <ul>
 *   <li>@Builder - Generates a nested Builder class with method chaining support
 *   <li>@Getter - Generates getter methods for all fields
 *   <li>@ToString - Generates a proper toString() method
 *   <li>@NonNull - Marks email as required with null-check validation
 * </ul>
 *
 * <p><b>Usage Example:</b>
 * <pre>
 *   // Build with required field only
 *   var user1 = UserLombok.builder()
 *       .email("john@example.com")
 *       .build();
 *
 *   // Build with optional fields
 *   var user2 = UserLombok.builder()
 *       .email("jane@example.com")
 *       .firstName("Jane")
 *       .lastName("Doe")
 *       .phone("+1-555-0123")
 *       .age(28)
 *       .build();
 * </pre>
 *
 * <p><b>Field Descriptions:</b>
 * <ul>
 *   <li>email (required) - User's email address, must not be null
 *   <li>firstName (optional) - User's first name
 *   <li>lastName (optional) - User's last name
 *   <li>phone (optional) - User's phone number
 *   <li>age (optional) - User's age
 *   <li>active (optional) - Whether the user account is active
 * </ul>
 *
 * <p><b>Key Points:</b>
 * <ul>
 *   <li>All fields are final (immutable after construction)
 *   <li>Email field is marked @NonNull for automatic null validation
 *   <li>Lombok generates the builder, getters, and toString automatically
 *   <li>No need to write builder boilerplate code
 * </ul>
 *
 * @see lombok.Builder
 * @see lombok.Getter
 * @see lombok.ToString
 * @see lombok.NonNull
 */
@Builder
@Getter
@ToString
public class UserLombok {

  /** User's email address (required field). This field must not be null. */
  @NonNull private final String email;

  /** User's first name (optional field). Can be null. */
  private final String firstName;

  /** User's last name (optional field). Can be null. */
  private final String lastName;

  /** User's phone number (optional field). Can be null. */
  private final String phone;

  /** User's age (optional field). Can be 0 if not set. */
  private final int age;

  /** Whether the user account is active (optional field). Defaults to false. */
  private final boolean active;

  /**
   * Gets the user's full name by combining first and last name.
   *
   * @return the full name, or just the first/last name if one is missing
   */
  public String getFullName() {
    if (firstName != null && lastName != null) {
      return firstName + " " + lastName;
    } else if (firstName != null) {
      return firstName;
    } else if (lastName != null) {
      return lastName;
    } else {
      return "Unknown";
    }
  }

  /**
   * Checks if the user has all optional fields filled.
   *
   * @return true if all optional fields are set, false otherwise
   */
  public boolean isComplete() {
    return firstName != null && lastName != null && phone != null && age > 0 && active;
  }
}
