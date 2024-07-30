package Stack;

import java.util.*;

public class Leetcode146LRUCache {
    Map<Integer, Integer> cache;
    Set<Integer> keys;
    int totalCapacity;

    public Leetcode146LRUCache(int capacity) {
        cache = new HashMap<>();
        keys = new LinkedHashSet<>();
        totalCapacity = capacity;
    }

    public int get(int key) {
        if(!cache.keySet().contains(key)){
            return -1;
        }
        keys.remove(key);
        keys.add(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            keys.remove(key);
        }
        else if(cache.size() == totalCapacity){
            int poppedKey = keys.iterator().next();
            keys.remove(poppedKey);
            cache.remove(poppedKey);
        }
        cache.put(key, value);
        keys.add(key);
    }
}
