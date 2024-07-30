package LinkedList;

public class Leetcode2AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode head = new ListNode();
        ListNode iter = head.next;
        ListNode prev = head;
        // inside while loop this will be considered as remainder
        int currSum = 0;
        while(temp1!=null && temp2!=null){
            if(iter==null){
                iter = new ListNode();
                prev.next = iter;
                if(head.next==null)
                    head.next = iter;
            }
            currSum = currSum+temp1.val+temp2.val;
            if(currSum>9){
                int remainder = currSum % 10;
                int quotient = currSum / 10;
                iter.val = remainder;
                currSum = quotient;
            } else{
                iter.val = currSum;
                currSum = 0;
            }
            prev = iter;
            iter = iter.next;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        if(temp1!=null)
            while(temp1!=null || currSum!=0){
                System.out.println(currSum);
                if(currSum==0){
                    prev.next = temp1;
                    break;
                }
                if(iter==null){
                    iter = new ListNode();
                    prev.next = iter;
                }
                if(temp1!=null)
                    currSum = currSum+temp1.val;
                if(currSum>9){
                    int remainder = currSum % 10;
                    int quotient = currSum / 10;
                    iter.val = remainder;
                    currSum = quotient;
                } else{
                    iter.val = currSum;
                    currSum = 0;
                }
                prev = iter;
                iter = iter.next;
                if(temp1!=null)
                    temp1 = temp1.next;
            }
        while(temp2!=null || currSum!=0){
            System.out.println(currSum);
            if(currSum==0){
                prev.next = temp2;
                break;
            }
            if(iter==null){
                iter = new ListNode();
                prev.next = iter;
            }
            if(temp2!=null)
                currSum = currSum+temp2.val;
            if(currSum>9){
                int remainder = currSum % 10;
                int quotient = currSum / 10;
                iter.val = remainder;
                currSum = quotient;
            } else{
                iter.val = currSum;
                currSum = 0;
            }
            prev = iter;
            iter = iter.next;
            if(temp2!=null)
                temp2 = temp2.next;
        }
        return head.next;
    }
}
