# Gang of Four Design Patterns - Java 17 & Spring Boot 3.x

A comprehensive implementation of all 23 Gang of Four (GoF) design patterns using Java 17 features and Spring Boot 3.2.x core framework.

## 📋 Project Overview

This project demonstrates industry-standard implementations of creational, structural, and behavioral design patterns with:
- **Java 17** features (records, sealed classes, pattern matching, switch expressions)
- **Spring Boot 3.2.x** core framework (no web/REST dependencies)
- **Real-world examples** with beginner-friendly documentation
- **Comprehensive JUnit 5 + Mockito tests** for each pattern
- **Multi-module Gradle** structure for clear separation

## 🏗️ Project Structure

```
design-patterns/
├── common/                      # Shared interfaces and utilities
├── creational-patterns/         # 5 Creational patterns
│   ├── singleton/              # Enum-based singleton
│   ├── factory/                # Factory Method with payment processors
│   ├── abstractfactory/        # Abstract Factory with UI components
│   ├── builder/                
│   │   ├── lombok/            # Builder with Lombok annotations
│   │   └── records/           # Builder with Java Records
│   └── prototype/             # Prototype with document cloning
├── structural-patterns/         # 7 Structural patterns
│   ├── adapter/               # Adapter with MapStruct
│   ├── bridge/                # Bridge for platform abstraction
│   ├── composite/             # Composite tree structure
│   ├── decorator/             # Decorator with Spring AOP
│   ├── facade/                # Facade for subsystem access
│   ├── flyweight/             # Flyweight for object sharing
│   └── proxy/                 # Proxy with caching & lazy loading
└── behavioral-patterns/         # 11 Behavioral patterns
    ├── chainofresponsibility/ # Handler chain
    ├── command/               # Command with undo/redo
    ├── iterator/              # Custom iterator
    ├── mediator/              # Mediator with Spring events
    ├── memento/               # Memento with records
    ├── observer/              # Observer with @EventListener
    ├── state/
    │   ├── vanilla/          # Traditional state machine
    │   └── statemachine/     # Spring State Machine
    ├── strategy/              # Strategy with Spring DI
    ├── templatemethod/        # Template Method pattern
    ├── visitor/               # Visitor with pattern matching
    └── interpreter/           # Expression interpreter
```

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **Gradle 8.5+** (wrapper included)
- **IDE** with Lombok support (IntelliJ IDEA, Eclipse, VS Code)

### Building the Project

```bash
# Build all modules
gradle build

# Build without tests
gradle build -x test

# Clean and build
gradle clean build
```

### Running Tests

```bash
# Run all tests
gradle test

# Run tests for specific module
gradle :creational-patterns:test
gradle :structural-patterns:test
gradle :behavioral-patterns:test

# Run tests with coverage
gradle test jacocoTestReport
```

## 📚 Design Patterns Catalog

### Creational Patterns (5)

| Pattern | Purpose | Key Classes | Spring Integration |
|---------|---------|-------------|-------------------|
| **Singleton** | Ensure only one instance exists | `DatabaseConnection` | `@Component` (singleton scope) |
| **Factory Method** | Create objects without specifying exact class | `PaymentFactory`, `Payment` | `@Bean` factory methods |
| **Abstract Factory** | Create families of related objects | `UIComponentFactory` | `@Profile` for environment-specific factories |
| **Builder** | Construct complex objects step-by-step | `UserLombok`, `UserRecord` | `@ConfigurationProperties` |
| **Prototype** | Clone existing objects | `WordDocument`, `DocumentPrototypeRegistry` | `@Scope("prototype")` |

### Structural Patterns (7)

| Pattern | Purpose | Key Classes | Spring Integration |
|---------|---------|-------------|-------------------|
| **Adapter** | Make incompatible interfaces work together | MapStruct adapters | Custom converters |
| **Bridge** | Decouple abstraction from implementation | Bridge interfaces | Dependency injection |
| **Composite** | Compose objects into tree structures | Composite components | Hierarchical beans |
| **Decorator** | Add behavior dynamically | Decorator wrappers | `@Aspect`, AOP |
| **Facade** | Simplified interface to complex subsystem | Facade classes | `@Service` layer |
| **Flyweight** | Share fine-grained objects efficiently | Flyweight factory | Singleton beans, caching |
| **Proxy** | Control access to another object | Proxy classes | `@Cacheable`, `@Async` |

### Behavioral Patterns (11)

| Pattern | Purpose | Key Classes | Spring Integration |
|---------|---------|-------------|-------------------|
| **Chain of Responsibility** | Pass request along handler chain | Handler chain | Filter chains, interceptors |
| **Command** | Encapsulate request as object | Command interface | `@EventListener` |
| **Iterator** | Sequential access to elements | Custom iterator | Stream API |
| **Mediator** | Encapsulate object interactions | Mediator interface | `ApplicationEventPublisher` |
| **Memento** | Capture and restore object state | Memento records | JPA auditing |
| **Observer** | Define one-to-many dependency | Observer interface | `@EventListener`, `@Async` |
| **State** | Change behavior based on state | State interfaces | Spring State Machine |
| **Strategy** | Define interchangeable algorithms | Strategy interface | `@Qualifier`, Map injection |
| **Template Method** | Define algorithm skeleton | Abstract template | `JdbcTemplate`, `RestTemplate` |
| **Visitor** | Separate algorithm from structure | Visitor interface | Pattern matching |
| **Interpreter** | Define grammar and interpreter | Expression tree | SpEL integration |

## 💡 Key Features

### Java 17 Features Used

- **Records**: Immutable data carriers for DTOs, mementos
- **Sealed Classes**: Controlled inheritance hierarchies
- **Pattern Matching**: Type checking in switch and instanceof
- **Switch Expressions**: Cleaner factory selection
- **Text Blocks**: Multi-line string literals
- **var Keyword**: Local variable type inference

### Spring Boot Integration

- **Core Spring**: Dependency injection, bean lifecycle management
- **Spring AOP**: Aspect-oriented programming for decorators/proxies
- **Spring Events**: Observer pattern with `@EventListener`
- **Spring State Machine**: Advanced state management
- **MapStruct**: Bean mapping for adapters
- **Lombok**: Reduced boilerplate code

### Testing Strategy

- **JUnit 5**: Modern testing framework with annotations
- **Mockito**: Mocking framework for unit tests
- **AssertJ**: Fluent assertion library
- **@Nested**: Organized test structure
- **@ParameterizedTest**: Data-driven tests
- **>80% Coverage**: Comprehensive test suites

## 📖 Documentation

Each pattern includes:
- **package-info.java**: Pattern explanation and intent
- **JavaDoc comments**: Detailed class and method documentation
- **README files**: Usage examples and best practices
- **Test cases**: Demonstrating pattern usage

## 🎯 Best Practices Demonstrated

1. **SOLID Principles**: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
2. **Clean Code**: Meaningful names, small methods, clear intent
3. **DRY (Don't Repeat Yourself)**: Code reuse through patterns
4. **Separation of Concerns**: Multi-module structure
5. **Dependency Injection**: Loose coupling with Spring
6. **Immutability**: Records and final fields where appropriate
7. **Fail-Fast**: Input validation and exception handling

## 🔧 Dependencies

```gradle
- Spring Boot: 3.2.2
- Spring State Machine: 4.0.1
- MapStruct: 1.6.3
- Lombok: 1.18.30
- JUnit Jupiter: Managed by Spring Boot
- Mockito: Managed by Spring Boot
- AssertJ: Managed by Spring Boot
```

## 📝 Module Details

### Common Module
Shared interfaces, annotations, and utilities used across all patterns:
- `@DesignPattern` annotation
- `PatternException` base exception
- Common utilities and helpers

### Creational Patterns Module
Object creation mechanisms:
- Flexible instantiation
- Abstraction of creation process
- Control over object lifecycle

### Structural Patterns Module
Object composition and relationships:
- Class and object composition
- Interface adaptation
- Simplified complex structures

### Behavioral Patterns Module
Communication between objects:
- Algorithms and responsibilities
- Object collaboration
- Flow of control

## 🤝 Contributing

This is an educational project demonstrating design patterns. Feel free to:
- Study the implementations
- Use as reference for your projects
- Suggest improvements
- Add more examples

## 📚 References

- **Gang of Four Book**: "Design Patterns: Elements of Reusable Object-Oriented Software"
- **Spring Framework Documentation**: https://spring.io/projects/spring-framework
- **Java 17 Features**: https://openjdk.org/projects/jdk/17/
- **Effective Java (3rd Edition)**: Joshua Bloch

## ⚖️ License

This project is for educational purposes. No copyright issues - all code is original implementation based on public GoF patterns.

## 📞 Support

For questions or issues:
1. Check the JavaDoc documentation in each pattern
2. Review the test cases for usage examples
3. Refer to package-info.java files for pattern explanations

---

**Built with ❤️ for learning Gang of Four Design Patterns with modern Java and Spring Boot**
