package LinkedList;


import java.util.*;
import java.util.stream.Collectors;

public class Leetcode3217DeleteNodesFromLinkedList {
    public ListNode modifiedList(int[] nums, ListNode head) {
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Set<Integer> numsSet = new HashSet<>(numList);

        ListNode prev = null, curr = head;

        while(curr!=null){
            if(curr.val == 995 || curr.val == 872) {
                System.out.println("Prev:"+prev.val);
            }
            if(numsSet.contains(curr.val)){
                if(prev!=null){
                    prev.next = curr.next;
                } else {
                    prev = null;
                    head = curr.next;
                }
            } else {
                prev = curr;
            }

            curr = curr.next;
        }

        return head;
    }
}
