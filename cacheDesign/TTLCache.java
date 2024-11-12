package cacheDesign;

import java.util.*;

public class TTLCache {

    private int capacity;
    private Map<String, CacheEntry> cache;  // The cache stores key and corresponding entry
    private Map<String, Long> expirationTimes; // Stores expiration times for each key

    // Constructor: Initialize the cache with a given capacity
    public TTLCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.expirationTimes = new HashMap<>();
    }

    // A CacheEntry class to store both value and TTL
    private static class CacheEntry {
        String value;
        long ttl;  // Time-to-live in seconds

        CacheEntry(String value, long ttl) {
            this.value = value;
            this.ttl = ttl;
        }
    }

    // Set a key-value pair in the cache with a TTL (Time-to-live) in seconds
    public void set(String key, String value, long ttl) {
        long expirationTime = System.currentTimeMillis() + ttl * 1000; // Convert ttl to milliseconds
        CacheEntry entry = new CacheEntry(value, ttl);
        cache.put(key, entry);
        expirationTimes.put(key, expirationTime);
    }

    // Get the value of the key from the cache
    public String get(String key) {
        // Check if the key exists and if it has expired
        if (!cache.containsKey(key)) {
            return null;  // Key doesn't exist
        }

        long currentTime = System.currentTimeMillis();
        long expirationTime = expirationTimes.get(key);

        // Check if the value is expired
        if (currentTime > expirationTime) {
            // The cache has expired
            cache.remove(key);
            expirationTimes.remove(key);
            return null;  // Return null as the cache has expired
        }

        // If it is valid, return the value
        return cache.get(key).value;
    }

    // Delete a key from the cache (optional method for eviction)
    public void delete(String key) {
        cache.remove(key);
        expirationTimes.remove(key);
    }

    // Check if the cache contains a given key
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    // Main method to test the TTL Cache
    public static void main(String[] args) throws InterruptedException {
        TTLCache cache = new TTLCache(5);  // Create a cache with 5 seconds TTL

        // Test case 1: Set key-value pair with TTL 2 seconds
        cache.set("key1", "value1", 2);
        System.out.println(cache.get("key1")); // Should return "value1"

        // Test case 2: Wait for 3 seconds and try to get the value again (should expire)
        Thread.sleep(3000);
        System.out.println(cache.get("key1")); // Should return null (expired)

        // Test case 3: Set another key-value pair with TTL 4 seconds
        cache.set("key2", "value2", 4);
        System.out.println(cache.get("key2")); // Should return "value2"

        // Test case 4: Wait for 2 seconds and check the cache (key2 should still be valid)
        Thread.sleep(2000);
        System.out.println(cache.get("key2")); // Should return "value2"

        // Test case 5: Set a key with TTL 0 (permanent)
        cache.set("key3", "value3", 0);
        System.out.println(cache.get("key3")); // Should return "value3" (TTL is 0, never expires)

        // Test case 6: Set another key, then delete a key and try to retrieve it
        cache.set("key4", "value4", 3);
        cache.delete("key4");
        System.out.println(cache.get("key4")); // Should return null (deleted)
    }
}

