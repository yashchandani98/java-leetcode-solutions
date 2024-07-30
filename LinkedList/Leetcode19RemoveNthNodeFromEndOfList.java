package LinkedList;

public class Leetcode19RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        n = removeNode(head, n);
        System.out.println(n);
        if(n==0){
            return head.next;
        }
        return head;
    }
    private int removeNode(ListNode traverse, int n){
        if(traverse==null){
            return n;
        }
        n = removeNode(traverse.next, n);
        // System.out.println(n);
        if(n == 0){
            traverse.next = traverse.next.next;
        }
        n--;
        return  n;
    }
}
