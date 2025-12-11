package com.example.patterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of the Mediator pattern.
 * 
 * <p>This demo shows how the Mediator pattern reduces coupling between components
 * by having them communicate through a central mediator object instead of directly
 * with each other. This simplifies the communication logic and makes the system
 * easier to maintain and extend.</p>
 * 
 * <p><b>Key Takeaways:</b></p>
 * <ul>
 *   <li>Components don't reference each other directly - they only know the mediator</li>
 *   <li>Reduces coupling between components (loose coupling)</li>
 *   <li>Centralizes complex communication logic in one place</li>
 *   <li>Easy to add new components without modifying existing ones</li>
 *   <li>Mediator can become a "god object" if not carefully designed</li>
 * </ul>
 * 
 * <h3>Real-World Analogy:</h3>
 * <p>Think of an air traffic control tower at an airport. Pilots don't communicate
 * directly with each other - they all communicate through the control tower. The tower
 * coordinates all the planes, preventing collisions and managing traffic flow. Without
 * the tower, each pilot would need to track and communicate with every other plane!</p>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class MediatorDemo {
    
    private static final Logger log = LoggerFactory.getLogger(MediatorDemo.class);
    
    /**
     * Runs the Mediator pattern demonstration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        log.info("=== Mediator Pattern Demonstration ===\n");
        
        // Note: This demo shows the concept without Spring's ApplicationEventPublisher
        // In a real Spring application, you would use dependency injection
        
        log.info("Simulating a chat room where users communicate through a mediator");
        log.info("Users don't know about each other - they only know the chat room\n");
        log.info("=".repeat(60));
        
        // Create a simple mediator (simulated without Spring)
        SimpleChatMediator chatRoom = new SimpleChatMediator();
        
        // Scenario 1: Users joining the chat
        log.info("\nScenario 1: Users Joining the Chat Room");
        log.info("-".repeat(60));
        chatRoom.registerUser("Alice");
        chatRoom.registerUser("Bob");
        chatRoom.registerUser("Charlie");
        log.info("Current users: {}\n", chatRoom.getUserCount());
        
        // Scenario 2: Public messages
        log.info("\nScenario 2: Public Messages");
        log.info("-".repeat(60));
        chatRoom.sendPublicMessage("Alice", "Hello everyone!");
        chatRoom.sendPublicMessage("Bob", "Hi Alice! How are you?");
        chatRoom.sendPublicMessage("Charlie", "Good morning!");
        
        // Scenario 3: Private messages
        log.info("\n\nScenario 3: Private Messages");
        log.info("-".repeat(60));
        chatRoom.sendPrivateMessage("Alice", "Bob", "Want to grab coffee later?");
        chatRoom.sendPrivateMessage("Bob", "Alice", "Sure! 3pm works?");
        
        // Scenario 4: User trying to send message before joining
        log.info("\n\nScenario 4: Unregistered User Attempt");
        log.info("-".repeat(60));
        chatRoom.sendPublicMessage("David", "Hello?");
        log.info("(David is not registered, so message is rejected)");
        
        // Scenario 5: Private message to non-existent user
        log.info("\n\nScenario 5: Message to Non-Existent User");
        log.info("-".repeat(60));
        chatRoom.sendPrivateMessage("Alice", "Eve", "Are you there?");
        log.info("(Eve is not registered, so message is rejected)");
        
        // Scenario 6: User leaving
        log.info("\n\nScenario 6: User Leaving the Chat");
        log.info("-".repeat(60));
        chatRoom.unregisterUser("Charlie");
        log.info("Current users: {}\n", chatRoom.getUserCount());
        
        // Scenario 7: More conversation
        log.info("\nScenario 7: Continued Conversation");
        log.info("-".repeat(60));
        chatRoom.sendPublicMessage("Alice", "Charlie left!");
        chatRoom.sendPublicMessage("Bob", "See you later, Charlie!");
        
        // Key Benefits Summary
        log.info("\n\n=== Key Benefits ===");
        log.info("✓ Users don't need to know about each other");
        log.info("✓ Adding new users doesn't affect existing users");
        log.info("✓ Communication logic is centralized in the mediator");
        log.info("✓ Easy to add new message types or rules");
        log.info("✓ Reduces dependencies between components");
        
        log.info("\n\n=== Pattern Structure ===");
        log.info("Without Mediator:");
        log.info("  User1 ←→ User2 ←→ User3 ←→ User4");
        log.info("  (Everyone needs to know everyone - messy!)");
        log.info("\nWith Mediator:");
        log.info("       [ChatRoom Mediator]");
        log.info("      /    |    |    \\");
        log.info("  User1 User2 User3 User4");
        log.info("  (Users only know the mediator - clean!)");
        
        log.info("\n\n=== When to Use ===");
        log.info("• Components need to communicate but shouldn't be tightly coupled");
        log.info("• Communication logic is complex and scattered");
        log.info("• You want to reuse components in different contexts");
        log.info("• GUI components (buttons, text fields, etc.) coordinating");
        log.info("• Chat systems, air traffic control, workflow engines");
    }
    
    /**
     * Simple chat mediator for demonstration (without Spring dependencies).
     * In a real application, use ChatRoomMediator with Spring's event system.
     */
    private static class SimpleChatMediator {
        private final java.util.Set<String> users = new java.util.HashSet<>();
        
        public boolean registerUser(String username) {
            if (users.add(username)) {
                log.info("[SYSTEM] {} joined the chat", username);
                return true;
            }
            log.warn("[SYSTEM] Username {} is already taken", username);
            return false;
        }
        
        public void unregisterUser(String username) {
            if (users.remove(username)) {
                log.info("[SYSTEM] {} left the chat", username);
            }
        }
        
        public void sendPublicMessage(String sender, String content) {
            if (!users.contains(sender)) {
                log.warn("[SYSTEM] User {} is not registered", sender);
                return;
            }
            log.info("[PUBLIC] {}: {}", sender, content);
        }
        
        public void sendPrivateMessage(String sender, String recipient, String content) {
            if (!users.contains(sender)) {
                log.warn("[SYSTEM] Sender {} is not registered", sender);
                return;
            }
            if (!users.contains(recipient)) {
                log.warn("[SYSTEM] Recipient {} is not registered", recipient);
                return;
            }
            log.info("[PRIVATE] {} → {}: {}", sender, recipient, content);
        }
        
        public int getUserCount() {
            return users.size();
        }
    }
}
