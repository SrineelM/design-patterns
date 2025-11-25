// File path: creational-patterns/src/main/java/com/example/patterns/creational/abstractfactory/UIFactorySelector.java

package com.example.patterns.creational.abstractfactory;

/**
 * Utility class for selecting the appropriate UI factory based on operating system.
 *
 * <p>This class demonstrates how to dynamically select the correct concrete factory implementation
 * based on the runtime environment. In real applications, Spring's @Conditional annotation could
 * be used to inject the appropriate factory at startup.
 *
 * <p><b>How it works:</b>
 * <ul>
 *   <li>Detects the operating system from system properties
 *   <li>Returns the corresponding concrete factory implementation
 *   <li>Client code uses the factory interface without knowing which concrete implementation is
 *       returned
 * </ul>
 *
 * <p><b>Real-world Spring Configuration Example:</b>
 * <pre>
 *   @Configuration
 *   public class UIConfig {
 *
 *       @Bean
 *       @ConditionalOnProperty(name = "os.name", havingValue = "Windows*")
 *       public UIComponentFactory windowsUIFactory() {
 *           return new WindowsUIFactory();
 *       }
 *
 *       @Bean
 *       @ConditionalOnProperty(name = "os.name", havingValue = "*Mac*")
 *       public UIComponentFactory macUIFactory() {
 *           return new MacUIFactory();
 *       }
 *
 *       @Bean
 *       @ConditionalOnProperty(name = "os.name", havingValue = "*Linux*")
 *       public UIComponentFactory linuxUIFactory() {
 *           return new LinuxUIFactory();
 *       }
 *   }
 * </pre>
 *
 * @see UIComponentFactory
 * @see WindowsUIFactory
 * @see MacUIFactory
 * @see LinuxUIFactory
 */
public class UIFactorySelector {

  /**
   * Selects and returns the appropriate UIComponentFactory based on the current operating system.
   *
   * <p>This method reads the system property "os.name" and instantiates the correct factory. If
   * the OS is unknown, it defaults to Windows factory.
   *
   * <p><b>Supported Operating Systems:</b>
   * <ul>
   *   <li>Windows (contains "Windows")
   *   <li>Mac (contains "Mac")
   *   <li>Linux (contains "Linux")
   * </ul>
   *
   * @return the appropriate UIComponentFactory for the current OS
   * @see System#getProperty(String)
   */
  public static UIComponentFactory getFactory() {
    String osName = System.getProperty("os.name").toLowerCase();

    if (osName.contains("windows")) {
      return new WindowsUIFactory();
    } else if (osName.contains("mac")) {
      return new MacUIFactory();
    } else if (osName.contains("linux")) {
      return new LinuxUIFactory();
    } else {
      // Default to Windows for unknown OS
      return new WindowsUIFactory();
    }
  }

  /**
   * Selects a factory based on an explicit platform name.
   *
   * <p>This method is useful for testing or when you want to explicitly specify which factory to
   * use regardless of the current operating system.
   *
   * @param platformName the name of the platform ("Windows", "Mac", or "Linux")
   * @return the requested UIComponentFactory
   * @throws IllegalArgumentException if the platform name is not recognized
   */
  public static UIComponentFactory getFactory(String platformName) {
    return switch (platformName.toLowerCase()) {
      case "windows" -> new WindowsUIFactory();
      case "mac" -> new MacUIFactory();
      case "linux" -> new LinuxUIFactory();
      default -> throw new IllegalArgumentException("Unknown platform: " + platformName);
    };
  }
}
