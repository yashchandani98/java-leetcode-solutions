package LinkedList;

public class Leetcode237DeleteNodeFromLinkedList {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
