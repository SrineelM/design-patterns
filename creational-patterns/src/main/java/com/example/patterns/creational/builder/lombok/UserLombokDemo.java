// File path: creational-patterns/src/main/java/com/example/patterns/creational/builder/lombok/UserLombokDemo.java

package com.example.patterns.creational.builder.lombok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.patterns.common.DesignPattern;

/**
 * Demonstration of the Builder pattern using Lombok annotations.
 *
 * <p>This class showcases various ways to build UserLombok objects using Lombok's automatically
 * generated builder. It demonstrates building with required fields only, adding optional fields,
 * and handling validation.
 *
 * <p><b>Key Demonstrations:</b>
 * <ul>
 *   <li>Building with required fields only (email)
 *   <li>Building with optional fields (first name, last name, phone, age)
 *   <li>Method chaining for fluent API
 *   <li>Validation of required fields (null checks)
 *   <li>Object immutability after creation
 * </ul>
 *
 * @see UserLombok
 * @see DesignPattern
 */
@DesignPattern(
    name = "Builder (Lombok)",
    description = "Constructs complex objects step by step using Lombok's @Builder annotation",
    category = "Creational")
public class UserLombokDemo {
    
    private static final Logger log = LoggerFactory.getLogger(UserLombokDemo.class);

  /**
   * Main method demonstrating the Lombok Builder pattern.
   *
   * <p>Shows multiple examples of building UserLombok objects with different combinations of
   * required and optional fields.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println("=".repeat(70));
    System.out.println("Builder Pattern with Lombok - User Example");
    System.out.println("=".repeat(70));

    // Example 1: Build with required field only
    System.out.println("\nExample 1: Minimal user (required field only)");
    System.out.println("-".repeat(70));

    try {
      var minimalUser =
          UserLombok.builder()
              .email("john.doe@example.com")
              .build();

      System.out.println("Built User:");
      System.out.println("  Email: " + minimalUser.getEmail());
      System.out.println("  Full Name: " + minimalUser.getFullName());
      System.out.println("  Phone: " + minimalUser.getPhone());
      System.out.println("  Age: " + minimalUser.getAge());
      System.out.println("  Active: " + minimalUser.isActive());
      System.out.println("  Is Complete: " + minimalUser.isComplete());
      System.out.println("  toString(): " + minimalUser);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    // Example 2: Build with some optional fields
    System.out.println("\nExample 2: User with some optional fields");
    System.out.println("-".repeat(70));

    var partialUser =
        UserLombok.builder()
            .email("jane.smith@example.com")
            .firstName("Jane")
            .lastName("Smith")
            .phone("+1-555-0123")
            .build();

    System.out.println("Built User:");
    System.out.println("  Email: " + partialUser.getEmail());
    System.out.println("  Full Name: " + partialUser.getFullName());
    System.out.println("  Phone: " + partialUser.getPhone());
    System.out.println("  Age: " + partialUser.getAge());
    System.out.println("  Active: " + partialUser.isActive());
    System.out.println("  Is Complete: " + partialUser.isComplete());
    System.out.println("  toString(): " + partialUser);

    // Example 3: Build with all fields
    System.out.println("\nExample 3: Complete user with all fields");
    System.out.println("-".repeat(70));

    var completeUser =
        UserLombok.builder()
            .email("bob.wilson@example.com")
            .firstName("Bob")
            .lastName("Wilson")
            .phone("+1-555-0456")
            .age(35)
            .active(true)
            .build();

    System.out.println("Built User:");
    System.out.println("  Email: " + completeUser.getEmail());
    System.out.println("  Full Name: " + completeUser.getFullName());
    System.out.println("  Phone: " + completeUser.getPhone());
    System.out.println("  Age: " + completeUser.getAge());
    System.out.println("  Active: " + completeUser.isActive());
    System.out.println("  Is Complete: " + completeUser.isComplete());
    System.out.println("  toString(): " + completeUser);

    // Example 4: Attempting to build without required field (will throw exception)
    System.out.println("\nExample 4: Attempting to build without required email");
    System.out.println("-".repeat(70));

    try {
      var invalidUser = UserLombok.builder().firstName("Invalid").build();
      System.out.println("This should not be printed");
    } catch (NullPointerException e) {
      System.out.println(
          "Caught NullPointerException: Email is required and cannot be null");
      System.out.println("Error: " + e.getMessage());
    }

    // Example 5: Object immutability demonstration
    System.out.println("\nExample 5: Object immutability demonstration");
    System.out.println("-".repeat(70));

    var immutableUser =
        UserLombok.builder()
            .email("alice@example.com")
            .firstName("Alice")
            .lastName("Brown")
            .build();

    System.out.println("Created immutable user: " + immutableUser);
    System.out.println("All fields are final - cannot be modified after creation");
    System.out.println("Attempting to access fields:");
    System.out.println("  Email: " + immutableUser.getEmail());
    System.out.println("  First Name: " + immutableUser.getFirstName());

    // Example 6: Fluent API chaining
    System.out.println("\nExample 6: Fluent API demonstration");
    System.out.println("-".repeat(70));

    var fluentUser =
        UserLombok.builder()
            .email("charlie@example.com")
            .firstName("Charlie")
            .lastName("Davis")
            .phone("+1-555-0789")
            .age(42)
            .active(true)
            .build();

    System.out.println("Built using fluent chaining:");
    System.out.println("  " + fluentUser);

    System.out.println("\n" + "=".repeat(70));
    System.out.println("Lombok Builder Pattern Demonstration Complete");
    System.out.println("=".repeat(70));
    System.out.println("\nKey Takeaways:");
    System.out.println("✓ Lombok's @Builder generates the builder pattern automatically");
    System.out.println("✓ @NonNull ensures required fields are validated");
    System.out.println("✓ Objects are immutable after construction");
    System.out.println("✓ Fluent API provides readable, chainable method calls");
    System.out.println("✓ No need to write builder boilerplate code");
  }
}
