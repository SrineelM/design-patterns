package com.example.patterns.structural.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Real database implementation.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class RealDatabase implements Database {
    
    private int queryCount = 0;
    
    @Override
    public List<String> query(String sql) {
        queryCount++;
        System.out.printf("[DATABASE] Executing query: %s%n", sql);
        
        // Simulate slow database query
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Return simulated results
        List<String> results = new ArrayList<>();
        results.add("Result 1 for: " + sql);
        results.add("Result 2 for: " + sql);
        results.add("Result 3 for: " + sql);
        
        System.out.printf("[DATABASE] Query returned %d results%n", results.size());
        return results;
    }
    
    @Override
    public String getStatistics() {
        return String.format("Total queries executed: %d", queryCount);
    }
}
