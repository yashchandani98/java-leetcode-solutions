package MergeSort;

import java.util.*;


public class Leetcode23MergeKSortedLists {

    /**
     * Approach: Divide and Conquer. Divide the whole List into 2 parts at each iteration. Sort and merge these two list at each iteration.
     * Ex:
     *
     *      [ [1,2,8,9], [4,5,5], [1,2,8,6], [7,9,10,11] ]
     *          \            /          \            /
     *           \          /            \          /
     *            \        /              \        /
     *          [1,2,4,5,5,8,9]         [1,2,6,7,8,9,10,11]
     *              \                        /
                     \                     /
     *                \                   /
     *           [1,1,2,2,4,5,5,6,7,8,8,9,9,10,11]
     *
     *
     * We will Iterate the whole list
     * */
    public ListNode mergeKLists(ListNode[] lists) {

        List<ListNode> listNodes = new ArrayList<>();

        Arrays.stream(lists).forEach(listNodes::add);


        while(listNodes.size()>1){
            List<ListNode> tempList = new ArrayList<>();

            for(int i=0; i<listNodes.size(); i+=2){
                if( i+1 < listNodes.size()){
                    tempList.add(mergeList(listNodes.get(i), listNodes.get(i+1)));
                } else {
                    tempList.add(listNodes.get(i));
                }
            }
            listNodes = tempList;
        }

        return !listNodes.isEmpty() ? listNodes.get(0) : null;
    }

    private ListNode mergeList(ListNode headA, ListNode headB){
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while(headA!=null && headB!=null){
            if(headA.val<=headB.val){
                tail.next = headA;
                headA = headA.next;
            } else {
                tail.next = headB;
                headB = headB.next;
            }
            tail = tail.next;
        }

        if(headA!=null){
            tail.next = headA;
        }

        if(headB!=null){
            tail.next = headB;
        }

        return dummy.next;
    }


    static class ListNode {
        int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
