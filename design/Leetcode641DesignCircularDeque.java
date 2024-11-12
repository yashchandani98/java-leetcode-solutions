package design;

public class Leetcode641DesignCircularDeque {
    private static class DoubleLinkedList {
        private int val;
        private DoubleLinkedList prev;
        private DoubleLinkedList next;

        public DoubleLinkedList(int val){
            this.val = val;
        }
    }

    private int totalSize;
    private int currSize;
    private DoubleLinkedList front;
    private DoubleLinkedList rear;

    public Leetcode641DesignCircularDeque(int k) {
        this.totalSize = k;
        this.currSize = 0;
    }

    public boolean insertFront(int value) {
        if(front == null){
            DoubleLinkedList node = new DoubleLinkedList(value);
            front = node;
            rear = node;
        } else {
            if(currSize==totalSize) return false;
            DoubleLinkedList node = new DoubleLinkedList(value);
            node.next = front;
            front.prev = node;
            front = node;
        }
        currSize++;
        return true;
    }

    public boolean insertLast(int value) {
        if(rear == null){
            DoubleLinkedList node = new DoubleLinkedList(value);
            front = node;
            rear = node;
        } else {
            if(currSize==totalSize) return false;
            DoubleLinkedList node = new DoubleLinkedList(value);
            node.prev = rear;
            rear.next = node;
            rear = node;
        }
        currSize++;
        return true;
    }

    public boolean deleteFront() {
        if(front == null) return false;
        if(front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
            if (front != null) front.prev = null;
        }
        currSize--;
        return true;
    }

    public boolean deleteLast() {
        if(rear == null) return false;
        if(front == rear) {
            front = null;
            rear = null;
        } else {
            rear = rear.prev;
            if(rear!=null) rear.next = null;
        }
        currSize--;
        return true;
    }

    public int getFront() {
        if(front == null) return -1;
        return front.val;
    }

    public int getRear() {
        if(rear == null) return -1;
        return rear.val;
    }

    public boolean isEmpty() {
        return currSize ==0 ;
    }

    public boolean isFull() {
        return currSize == totalSize;
    }
}
