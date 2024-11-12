package cacheDesign;

import java.util.*;
class Node {
    public int key;
    public int value;
    public Node prev;
    public Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

class Item {
    public Node node;
    public long expiresAt;

    public Item(Node node, long expiryInMs) {
        this.node = node;
        this.expiresAt = System.currentTimeMillis() + expiryInMs;
    }
}

class Store {

    private int capacity;
    private Map<Integer, Item> hash;
    private Node head;
    private Node tail;
    private int totalSum;
    private int totalCount;

    public Store(int capacity) {
        this.capacity = capacity;
        this.hash = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value, int expiryInMs) {
        evict();
        if(hash.containsKey(key)) {
            hash.remove(key);
        }
        Node node = new Node(key, value);
        addNode(node);
        Item item = new Item(node, expiryInMs);
        hash.put(key,item);

        if(hash.size() > capacity) {
            Node last = tail.prev;
            hash.remove(last.key);
            deleteNode(last);
        }
    }

    public int get(int key) {
        evict();
        Item item = hash.get(key);
        if(item == null) {
            return -1;
        }
        deleteNode(item.node);
        addNode(item.node);

        return item.node.value;
    }

    public int getAverage() {
        return totalSum / totalCount;
    }

    private void addNode(Node node) {
        Node nextNode = head.next;
        head.next = node;
        node.next = nextNode;
        nextNode.prev = node;
        node.prev = head;
        totalSum += node.value;
        totalCount++;
    }

    private void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        totalSum -= node.value;
        totalCount--;
    }

    private void evict() {
        hash.entrySet().removeIf(entry -> {
            Item item = entry.getValue();
            Boolean expired = item.expiresAt < System.currentTimeMillis();
            if(expired) {
                totalSum -= item.node.value;
                totalCount--;
            }
            return expired;
        });
    }
}

public class TTLAndLRUCache {

    public static void main(String[] args) throws InterruptedException {
        Store store = new Store(2);
        store.put(1,1,1000);
        System.out.println(store.get(1)); // 1
        Thread.sleep(1500);
        System.out.println(store.get(1)); // -1, because of expiry

        store.put(2,2,1000);
        store.put(3,3,1000);
        store.put(4,4,2000);
        store.put(5,5,2000);

        System.out.println(store.get(2)); // -1, because of LRU
        System.out.println(store.get(3)); // 3
        System.out.println(store.get(4)); // 4
        System.out.println(store.get(5)); // 5

        Thread.sleep(1500);

        System.out.println(store.get(3)); // -1, because of expiry
        System.out.println(store.get(4)); // 4

        System.out.println(store.getAverage());



    }
}
