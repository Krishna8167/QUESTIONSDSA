// Author: Kris Shar
// Topic: Advanced DSA + System Design
// Program: Thread-Safe LRU Cache using LinkedHashMap in Java
// Description: Demonstrates cache eviction policy, concurrency safety, and data access simulation.

import java.util.*;

class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> cacheMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public synchronized V get(K key) {
        if (!cacheMap.containsKey(key)) {
            System.out.println("❌ Cache Miss: " + key);
            return null;
        }
        System.out.println("✅ Cache Hit: " + key);
        return cacheMap.get(key);
    }

    public synchronized void put(K key, V value) {
        cacheMap.put(key, value);
        System.out.println("🗂️  Inserted: " + key + " → " + value);
    }

    public synchronized void display() {
        System.out.println("📦 Current Cache: " + cacheMap);
    }
}

public class LRUCacheSystem {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "Apple");
        cache.put(2, "Banana");
        cache.put(3, "Cherry");
        cache.display();

        cache.get(2);  // access Banana
        cache.get(1);  // access Apple
        cache.put(4, "Date"); // Cherry will be evicted

        cache.display();

        cache.get(3);  // Cache miss
        cache.get(4);  // Cache hit
    }
}
