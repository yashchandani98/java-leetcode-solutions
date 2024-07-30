package LinkedList;

import java.util.*;

public class Leetcode160IntersectionOfTwoLinkedList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> hashSet = new HashSet<ListNode>();
        ListNode temp = headA;
        while(temp!=null){
            hashSet.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while(temp!=null){
            if(hashSet.contains(temp)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}
