package HashMap;

import java.util.*;
public class Leetcode460LFUCache {
    private static class Node{
        private final int key;
        private int value;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private final int cap;
    private int minFreq;
    private final Map<Integer, LinkedList<Node>>  freqMap;
    private final Map<Integer, Integer> keyToFreq;
    private final Map<Integer, Node> cache;


    public Leetcode460LFUCache(int capacity) {
        cap = capacity;
        minFreq = 0;
        freqMap = new HashMap<>();
        keyToFreq = new HashMap<>();
        cache = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        int oldFreq = keyToFreq.get(key);
        int newFreq = oldFreq + 1;

        // Update frequency
        keyToFreq.put(key, newFreq);

        // Update frequency map
        freqMap.get(oldFreq).remove(node);
        if (freqMap.get(oldFreq).isEmpty()) {
            freqMap.remove(oldFreq);
            if (minFreq == oldFreq) {
                minFreq++;
            }
        }

        freqMap.computeIfAbsent(newFreq, k -> new LinkedList<>()).add(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update the value and frequency
            Node node = cache.get(key);
            node.value = value;
            get(key); // Reuse get to update frequency
        } else {
            if (cache.size() >= cap) {
                // Remove the LFU element with the lowest frequency and LRU policy
                LinkedList<Node> minFreqList = freqMap.get(minFreq);
                Node toRemove = minFreqList.removeFirst();
                if (minFreqList.isEmpty()) {
                    freqMap.remove(minFreq);
                }
                cache.remove(toRemove.key);
                keyToFreq.remove(toRemove.key);
            }

            // Add new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            keyToFreq.put(key, 1);
            minFreq = 1;
            freqMap.computeIfAbsent(1, k -> new LinkedList<>()).add(newNode);
        }
    }
}
