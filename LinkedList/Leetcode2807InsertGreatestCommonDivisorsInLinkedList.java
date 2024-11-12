package LinkedList;

public class Leetcode2807InsertGreatestCommonDivisorsInLinkedList {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode prev = null, curr = head;

        while(curr!=null){
            if(prev == null){
                prev = curr;
            } else {
                int temp = gcd(prev.val, curr.val);
                prev.next = new ListNode(temp, curr);
                prev = curr;
            }

            curr = curr.next;
        }

        return head;
    }

    private int gcd(int number1, int number2) {
        int divisor = 1;
        int res = 1;
        while(divisor<=Math.min(number1, number2)){
            if(number1% (divisor) == 0 && number2 % (divisor) == 0){
                res = divisor;
            }
            divisor++;
        }
        return res;
    }
}
