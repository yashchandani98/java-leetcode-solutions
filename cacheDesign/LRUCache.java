package cacheDesign;

import java.util.*;

public class LRUCache {

    private int capacity;
    private int currentCapacity;
    private Map<Integer, Node> keyToNode = new HashMap<>();

    // Dummy head and tail nodes
    private Node head;
    private Node tail;

    // Constructor to initialize the LRU cache with a given capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.currentCapacity = 0;

        // Create dummy head and tail nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);

        // Connect the dummy nodes
        head.next = tail;
        tail.prev = head;
    }

    // Add a new key-value pair or update an existing key
    public void add(int key, int value) {
        // If key is already in the cache, update it and move to the end (most recently used)
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value;  // Update the value
            removeNode(node);    // Remove the node from its current position
            insertInEnd(node);   // Insert the node at the end
        } else {
            // If the cache is at capacity, remove the least recently used element (head)
            if (currentCapacity == capacity) {
                keyToNode.remove(head.next.key);  // Remove from map
                removeNode(head.next);            // Remove the node from the linked list
                currentCapacity--;               // Decrease current capacity
            }

            // Insert the new node at the end (most recently used)
            Node node = new Node(key, value);
            keyToNode.put(key, node);
            insertInEnd(node);
            currentCapacity++;  // Increase current capacity
        }
    }

    // Get the value of the key, and move the key to the end (most recently used)
    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;  // If key not found, return -1
        }

        Node node = keyToNode.get(key);
        removeNode(node);  // Remove the node from its current position
        insertInEnd(node); // Insert it at the end (most recently used)

        return node.value;
    }

    // Remove a node from the linked list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert a node at the end (tail) of the linked list
    private void insertInEnd(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2); // Set capacity to 2
        cache.add(1, 1);  // Cache is {1=1}
        cache.add(2, 2);  // Cache is {1=1, 2=2}
        System.out.println(cache.get(1));  // Returns 1, Cache is {2=2, 1=1}
        cache.add(3, 3);  // Evicts key 2, Cache is {1=1, 3=3}
        System.out.println(cache.get(2));  // Returns -1 (not found)
        cache.add(4, 4);  // Evicts key 1, Cache is {3=3, 4=4}
        System.out.println(cache.get(1));  // Returns -1 (not found)
        System.out.println(cache.get(3));  // Returns 3
        System.out.println(cache.get(4));  // Returns 4
    }

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
