// File path: creational-patterns/src/main/java/com/example/patterns/creational/builder/records/UserRecord.java

package com.example.patterns.creational.builder.records;

/**
 * User represented as a sealed record demonstrating immutability.
 *
 * <p>This record demonstrates modern Java (17+) features for creating immutable data types. Records
 * automatically generate constructors, getters, equals(), hashCode(), and toString() methods.
 *
 * <p><b>Record Features:</b>
 * <ul>
 *   <li>Immutable by design - all fields are final
 *   <li>Automatically generates accessor methods (not prefixed with "get")
 *   <li>Automatically generates equals() and hashCode() based on all fields
 *   <li>Automatically generates toString() with all field values
 *   <li>Compact constructor for validation logic
 * </ul>
 *
 * <p><b>Sealed Record Concept:</b>
 * <ul>
 *   <li>A sealed class restricts which classes can extend or implement it
 *   <li>This record only allows itself and explicitly permitted subclasses
 *   <li>Provides better encapsulation and control over the type hierarchy
 * </ul>
 *
 * <p><b>Usage:</b>
 * <pre>
 *   var user = new UserRecord(
 *       "john@example.com",
 *       "John",
 *       "Doe",
 *       "+1-555-0123",
 *       30,
 *       true);
 *
 *   // Access fields directly (no getters needed for primitives)
 *   System.out.println(user.email());     // No get prefix!
 *   System.out.println(user.firstName()); // No get prefix!
 *
 *   // Records automatically implement equals() and hashCode()
 *   var user2 = new UserRecord(...);
 *   if (user.equals(user2)) { ... }
 * </pre>
 *
 * <p><b>Comparison with Traditional Class:</b>
 * <ul>
 *   <li>Record requires: 2 lines of code
 *   <li>Class equivalent would require: 50+ lines of boilerplate
 *   <li>Records automatically handle equals(), hashCode(), toString()
 * </ul>
 *
 * @param email User's email address (immutable, cannot be null internally)
 * @param firstName User's first name (immutable)
 * @param lastName User's last name (immutable)
 * @param phone User's phone number (immutable)
 * @param age User's age (immutable)
 * @param active Whether the user account is active (immutable)
 */
public record UserRecord(
    String email, String firstName, String lastName, String phone, int age, boolean active) {

  /**
   * Compact constructor for validation.
   *
   * <p>This constructor is called automatically when creating a new record instance. It runs
   * before field assignment, allowing for validation logic. If validation fails, an exception
   * should be thrown.
   *
   * @throws IllegalArgumentException if email is null or empty
   * @throws IllegalArgumentException if age is negative
   */
  public UserRecord {
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be negative");
    }
  }

  /**
   * Gets the user's full name.
   *
   * @return the full name, or just the first/last name if one is missing
   */
  public String fullName() {
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
   * Checks if the user profile is complete.
   *
   * @return true if all fields except active are filled, false otherwise
   */
  public boolean isComplete() {
    return firstName != null
        && !firstName.isEmpty()
        && lastName != null
        && !lastName.isEmpty()
        && phone != null
        && !phone.isEmpty()
        && age > 0;
  }

  /**
   * Creates a copy of this record with updated fields (copy-with pattern).
   *
   * <p>Since records are immutable, this method creates a new record with the same fields except
   * for the specified changes. This is a common pattern when you need a "modified" version of
   * an immutable object.
   *
   * @param email the new email (or null to keep current)
   * @param phone the new phone (or null to keep current)
   * @param active the new active status
   * @return a new UserRecord with updated fields
   */
  public UserRecord withUpdates(String email, String phone, boolean active) {
    return new UserRecord(
        email != null ? email : this.email,
        this.firstName,
        this.lastName,
        phone != null ? phone : this.phone,
        this.age,
        active);
  }
}
