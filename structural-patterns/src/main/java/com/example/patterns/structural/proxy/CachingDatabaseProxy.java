package com.example.patterns.structural.proxy;

import com.example.patterns.common.DesignPattern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Caching proxy for database (Proxy).
 * 
 * <p>This is a Caching Proxy - it caches query results to avoid
 * repeated expensive database calls for the same query.
 * 
 * <p><b>Benefits:</b>
 * <ul>
 *   <li>Dramatically improves performance for repeated queries</li>
 *   <li>Reduces database load</li>
 *   <li>Transparent caching - clients don't know about it</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Proxy (Caching)",
    category = "Structural",
    description = "Caches database query results to improve performance"
)
public class CachingDatabaseProxy implements Database {
    
    private final RealDatabase realDatabase;
    private final Map<String, List<String>> cache;
    private int cacheHits = 0;
    private int cacheMisses = 0;
    
    /**
     * Creates a caching database proxy.
     */
    public CachingDatabaseProxy() {
        this.realDatabase = new RealDatabase();
        this.cache = new HashMap<>();
        System.out.println("[CACHE PROXY] Created with empty cache");
    }
    
    @Override
    public List<String> query(String sql) {
        // Check cache first
        if (cache.containsKey(sql)) {
            cacheHits++;
            System.out.printf("[CACHE PROXY] Cache HIT for: %s%n", sql);
            return cache.get(sql);
        }
        
        // Cache miss - query real database
        cacheMisses++;
        System.out.printf("[CACHE PROXY] Cache MISS for: %s%n", sql);
        List<String> results = realDatabase.query(sql);
        
        // Store in cache
        cache.put(sql, results);
        System.out.printf("[CACHE PROXY] Cached results for: %s%n", sql);
        
        return results;
    }
    
    @Override
    public String getStatistics() {
        return String.format("""
            Cache Statistics:
            - Cache Hits: %d
            - Cache Misses: %d
            - Cache Hit Ratio: %.1f%%
            - Cached Queries: %d
            %s
            """,
            cacheHits,
            cacheMisses,
            getCacheHitRatio(),
            cache.size(),
            realDatabase.getStatistics()
        );
    }
    
    /**
     * Calculates cache hit ratio.
     *
     * @return hit ratio as percentage
     */
    private double getCacheHitRatio() {
        int total = cacheHits + cacheMisses;
        return total == 0 ? 0.0 : (cacheHits * 100.0) / total;
    }
    
    /**
     * Clears the cache.
     */
    public void clearCache() {
        cache.clear();
        System.out.println("[CACHE PROXY] Cache cleared");
    }
}
