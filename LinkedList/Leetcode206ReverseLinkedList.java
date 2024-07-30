package LinkedList;

public class Leetcode206ReverseLinkedList {



    /*
    *
    *
    *
    Pointer reversal approach:
    public ListNode reverseList(ListNode head) {
        return dfs(head, null);
    }

    private ListNode dfs(ListNode head, ListNode newHead) {
        if(head == null) {
            return newHead;
        }
        ListNode temp = head.next;
        head.next = newHead;
        return dfs(temp, head);
    }
    *
    *
    *Update value approach:
    * */
    private ListNode newHead;
    public ListNode reverseList(ListNode head) {
        if(head != null){
            reverse(head, head);
        }
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode traverse){
        if(traverse==null){
            newHead = new ListNode();
            return newHead;
        }
        ListNode temp = reverse(head, traverse.next);
        temp.val = traverse.val;
        if(traverse!=head){
            temp.next = new ListNode();
        }
        return temp.next;
    }
}
