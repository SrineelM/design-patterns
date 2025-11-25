# Structural Patterns Quick Reference

## When to Use Each Pattern

### 🔌 Adapter
**Use when:** You need to make incompatible interfaces work together
- Integrating legacy systems
- Third-party library integration
- Converting data formats
**Example:** Legacy customer data → Modern API

### 🌉 Bridge
**Use when:** You want abstraction and implementation to vary independently
- Multiple platform support
- Multiple implementation variants
- Avoiding explosion of subclasses
**Example:** Notifications (Email/SMS/Push) × (Alert/Promo/Order)

### 🌲 Composite
**Use when:** You need to treat individual objects and groups uniformly
- Tree structures
- Hierarchical data
- Part-whole relationships
**Example:** Files and Directories, Organization Charts

### 🎨 Decorator
**Use when:** You need to add responsibilities dynamically
- Adding features without subclassing
- Combining behaviors flexibly
- Runtime customization
**Example:** Coffee + Milk + Mocha + Whip

### 🏛️ Facade
**Use when:** You need to simplify a complex subsystem
- Complex workflows
- Multiple subsystem coordination
- Reducing client dependencies
**Example:** Order Processing (Inventory + Payment + Shipping)

### 🪶 Flyweight
**Use when:** You need to support large numbers of similar objects
- Memory optimization
- Sharing common state
- Many fine-grained objects
**Example:** Text Editor (millions of characters sharing fonts)

### 🎭 Proxy
**Use when:** You need to control access to an object
- Lazy loading (Virtual Proxy)
- Access control (Protection Proxy)
- Caching (Caching Proxy)
**Example:** Image lazy loading, Database caching, Document security

---

## Pattern Comparison

| Pattern | Problem | Solution | Key Benefit |
|---------|---------|----------|-------------|
| Adapter | Incompatible interfaces | Convert one interface to another | Integration |
| Bridge | Coupled abstraction/impl | Separate them | Independent evolution |
| Composite | Part-whole hierarchy | Tree structure with uniform interface | Simplicity |
| Decorator | Adding features | Wrap with additional behavior | Flexibility |
| Facade | Complex subsystem | Simplified interface | Ease of use |
| Flyweight | Too many objects | Share common state | Memory efficiency |
| Proxy | Control access | Add indirection layer | Control + features |

---

## Code Examples

### Adapter
```java
// Legacy → Modern
LegacyCustomer legacy = new LegacyCustomer(...);
Customer modern = new CustomerAdapter(legacy);
service.process(modern); // Works!
```

### Bridge
```java
// Notification × Sender
MessageSender sender = new EmailMessageSender();
Notification alert = new SystemAlert(sender, ...);
alert.notify("admin@example.com");
```

### Composite
```java
// Uniform interface for files and directories
Directory root = new Directory("project");
root.add(new File("README.md", 2048));
root.display(""); // Recursive display
```

### Decorator
```java
// Dynamically add features
Beverage coffee = new Espresso();
coffee = new Mocha(coffee);
coffee = new Whip(coffee);
System.out.println(coffee.getCost()); // 2.29
```

### Facade
```java
// One call instead of many
OrderFacade facade = new OrderFacade();
OrderResult result = facade.placeOrder(
    customerId, email, product, ...
); // Handles everything!
```

### Flyweight
```java
// Share character objects
CharacterFactory factory = new CharacterFactory();
Character a1 = factory.getCharacter('a', "Arial");
Character a2 = factory.getCharacter('a', "Arial");
// a1 == a2 (same object!)
```

### Proxy
```java
// Control access
Image image = new ImageProxy("photo.jpg");
// Not loaded yet - instant creation
image.display(); // NOW it loads
```

---

## Cheat Sheet

### Adapter ✓
- **Structure:** Client → Adapter → Adaptee
- **Goal:** Interface conversion
- **Analogy:** Power plug adapter

### Bridge ✓
- **Structure:** Abstraction → Implementation
- **Goal:** Decouple abstraction from implementation
- **Analogy:** Remote control ↔ TV

### Composite ✓
- **Structure:** Component (Leaf | Composite)
- **Goal:** Tree structure, uniform interface
- **Analogy:** File system

### Decorator ✓
- **Structure:** Component → Decorator → Component
- **Goal:** Add responsibilities dynamically
- **Analogy:** Gift wrapping

### Facade ✓
- **Structure:** Facade → Subsystems
- **Goal:** Simplified interface
- **Analogy:** Restaurant (kitchen, bar, service)

### Flyweight ✓
- **Structure:** Factory → Shared Flyweight
- **Goal:** Memory optimization
- **Analogy:** String pooling

### Proxy ✓
- **Structure:** Proxy → RealSubject
- **Goal:** Control access, add functionality
- **Analogy:** Lawyer representing client

---

## Decision Tree

```
Need to work with existing code?
├─ Incompatible interfaces? → ADAPTER
└─ Complex subsystem? → FACADE

Need object composition?
├─ Part-whole hierarchy? → COMPOSITE
└─ Add features dynamically? → DECORATOR

Need performance optimization?
└─ Many similar objects? → FLYWEIGHT

Need flexibility?
├─ Separate abstraction/implementation? → BRIDGE
└─ Control access or add features? → PROXY
```

---

## Spring Boot Integration

### Adapter
```java
@Component
public class LegacySystemAdapter implements ModernService {
    // Adapts legacy to modern
}
```

### Bridge
```java
@Service
public class NotificationService {
    private final MessageSender sender;
    // Constructor injection
}
```

### Composite
```java
@Component
public class MenuComposite implements MenuItem {
    private List<MenuItem> children;
}
```

### Decorator
```java
@Aspect
@Component
public class LoggingAspect {
    @Around("@annotation(Loggable)")
    public Object log(ProceedingJoinPoint pjp) {
        // Decorator via AOP
    }
}
```

### Facade
```java
@Service
public class OrderFacade {
    @Autowired
    private InventoryService inventory;
    @Autowired
    private PaymentService payment;
    // Simplified interface
}
```

### Flyweight
```java
@Service
@Scope("prototype")
public class SharedResource {
    // Shared with @Autowired
}
```

### Proxy
```java
@Cacheable("users")
public User getUser(String id) {
    // Spring creates caching proxy
}

@Lazy
@Autowired
private ExpensiveService service;
// Virtual proxy
```

---

## Common Mistakes

### ❌ Adapter
- Modifying the Adaptee (defeats purpose)
- Over-complicating simple conversions

### ❌ Bridge
- Confusing with Adapter (Bridge is design-time)
- Not separating abstraction/implementation

### ❌ Composite
- Not implementing uniform interface
- Forgetting null checks for operations

### ❌ Decorator
- Deep wrapping causing performance issues
- Making decorator order significant

### ❌ Facade
- Making facade too complex
- Exposing subsystem details

### ❌ Flyweight
- Mixing intrinsic and extrinsic state
- Premature optimization

### ❌ Proxy
- Making proxy too complex
- Forgetting to delegate

---

## Pattern Relationships

**Adapter vs Bridge:**
- Adapter: After classes are designed (retrofit)
- Bridge: Before classes are designed (planning)

**Decorator vs Proxy:**
- Decorator: Adds functionality
- Proxy: Controls access

**Facade vs Adapter:**
- Facade: Simplifies interface
- Adapter: Changes interface

**Composite vs Decorator:**
- Composite: Structure (tree)
- Decorator: Behavior (wrapping)

---

## Testing Tips

### Unit Testing
```java
// Test with mocks
@Test
void testAdapter() {
    LegacyCustomer legacy = mock(LegacyCustomer.class);
    Customer adapter = new CustomerAdapter(legacy);
    assertEquals("CUST-000123", adapter.getId());
}
```

### Integration Testing
```java
// Test facade coordination
@Test
void testOrderFacade() {
    OrderFacade facade = new OrderFacade();
    OrderResult result = facade.placeOrder(...);
    assertTrue(result.success());
}
```

---

## Performance Considerations

| Pattern | Performance Impact | Memory Impact |
|---------|-------------------|---------------|
| Adapter | Minimal | Minimal |
| Bridge | Minimal | Minimal |
| Composite | Depends on tree depth | Linear with nodes |
| Decorator | Slight overhead | Per decorator |
| Facade | Minimal | Minimal |
| Flyweight | **Faster** (cache) | **Much less** |
| Proxy | Varies by type | Minimal |

---

## Summary

**Most Used:** Adapter, Facade, Proxy  
**Most Powerful:** Bridge, Decorator  
**Most Elegant:** Composite  
**Best Optimization:** Flyweight  

**Start Learning With:** Adapter → Facade → Decorator  
**Master Next:** Bridge → Composite → Proxy → Flyweight

---

*Quick Reference v1.0*  
*Part of Design Patterns POC*
