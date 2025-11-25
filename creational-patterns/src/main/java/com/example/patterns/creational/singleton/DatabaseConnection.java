package com.example.patterns.creational.singleton;

import com.example.patterns.common.DesignPattern;

/**
 * Database Connection Singleton Implementation using Enum.
 * 
 * This is the BEST PRACTICE approach for implementing Singleton in Java 17+.
 * The enum ensures thread-safety and prevents reflection/serialization attacks.
 * 
 * Key Advantages:
 * 1. Thread-safe by design (guaranteed by JVM)
 * 2. Prevents reflection instantiation
 * 3. Handles serialization correctly
 * 4. Only one instance guaranteed
 * 5. Simple and elegant syntax
 * 
 * Real-world use case:
 * Database connections, logging instances, cache managers, configuration holders.
 * 
 * Usage Example:
 * <pre>
 * DatabaseConnection db = DatabaseConnection.INSTANCE;
 * db.executeQuery("SELECT * FROM users");
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Singleton",
    category = "Creational",
    description = "Ensures a class has only one instance with global access point"
)
public enum DatabaseConnection {
    
    /**
     * Single instance of DatabaseConnection.
     * Created when enum is first loaded by JVM.
     */
    INSTANCE;
    
    private static final int DEFAULT_POOL_SIZE = 10;
    private int poolSize;
    private boolean connected;
    
    /**
     * Constructor called once when enum is initialized.
     * This happens exactly once in the JVM lifetime.
     */
    DatabaseConnection() {
        this.poolSize = DEFAULT_POOL_SIZE;
        this.connected = false;
        // Initialize connection pool here
    }
    
    /**
     * Simulates opening database connection.
     * Thread-safe because enum initialization is thread-safe.
     */
    public void connect() {
        if (!connected) {
            this.connected = true;
            System.out.println("Database connected with pool size: " + poolSize);
        }
    }
    
    /**
     * Simulates closing database connection.
     * Thread-safe because enum instance is immutable.
     */
    public void disconnect() {
        if (connected) {
            this.connected = false;
            System.out.println("Database disconnected");
        }
    }
    
    /**
     * Executes a query using the singleton connection.
     *
     * @param query the SQL query to execute
     * @return simulated query result
     */
    public String executeQuery(String query) {
        if (!connected) {
            throw new IllegalStateException("Database not connected");
        }
        return "Result of: " + query;
    }
    
    /**
     * Gets the current pool size.
     *
     * @return the connection pool size
     */
    public int getPoolSize() {
        return poolSize;
    }
    
    /**
     * Sets the connection pool size.
     *
     * @param poolSize the new pool size
     */
    public void setPoolSize(int poolSize) {
        if (poolSize > 0) {
            this.poolSize = poolSize;
        }
    }
    
    /**
     * Checks if database is connected.
     *
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
}
