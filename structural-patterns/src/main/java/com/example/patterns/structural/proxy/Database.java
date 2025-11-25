package com.example.patterns.structural.proxy;

import java.util.List;

/**
 * Subject interface for database operations.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface Database {
    
    /**
     * Queries the database.
     *
     * @param sql the SQL query
     * @return query results
     */
    List<String> query(String sql);
    
    /**
     * Gets database statistics.
     *
     * @return statistics information
     */
    String getStatistics();
}
