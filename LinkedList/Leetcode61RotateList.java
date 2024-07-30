package LinkedList;

public class Leetcode61RotateList {
    /**
     We will calculate times i.e. k%(size of a linked list) and will rotate linkedlist these many times
     Just a tip but whenever you see rotation type position swapping , remember there will be a pattern
     and it can be used to optimize your code. In this case you don't have to count k rotations every time ,
     you just have to calculate k%n rotations ,where is n is number of nodes.
     */
    public ListNode rotateRight(ListNode head, int k) {
        ListNode newHead = head;
        ListNode treverse = head;
        if(k==0 || head == null || head.next == null)
            return head;
        int n =0;
        while(treverse!=null){
            //Calculate Size of linkedList;
            n++;
            treverse = treverse.next;
        }
        int times = k % n;
        while(times!=0){
            while(head!=null){
                if(head.next.next == null){
                    ListNode temp = newHead;
                    newHead = head.next;
                    newHead.next = temp;
                    head.next = null;
                    head = newHead;
                    break;
                } else{
                    head = head.next;
                }
            }
            times--;
        }
        return newHead;
    }
}
