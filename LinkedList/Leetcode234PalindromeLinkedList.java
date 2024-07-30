package LinkedList;

import java.util.*;
public class Leetcode234PalindromeLinkedList {
    /*
    * Approach: Using Stack Data Structure
    * TC: O(N), SC: O(N/2) ≈ O(N)
    * */
    public boolean isPalindromeUsingStack(ListNode head) {
        /**
         Length of the linkedlist need to be calculated first
         */

        int len =0;
        ListNode temp = head;
        while(temp!=null){
            len++;
            temp = temp.next;
        }
        temp = head;
        Stack<Integer> st = new Stack<Integer>();

        int idx = 0;
        boolean operation_push = true;
        boolean isPalinDrome = true;
        while(temp!=null){
            idx++;
            if(operation_push){
                st.push(temp.val);
            }
            else if(len%2==0){
                if(temp.val != st.peek()){
                    isPalinDrome = false;
                    break;
                }
                st.pop();
            }
            else if(len%2!=0){
                if(idx == ((len+1)/2)+1)
                    st.pop();
                if(temp.val != st.peek()){
                    isPalinDrome = false;
                    break;
                }
                st.pop();
            }
            if((len%2==0 && idx == len/2) || (len%2!=0 && idx==(len+1)/2))
                operation_push = false;
            temp = temp.next;
        }
        return isPalinDrome;
    }


    /*
     * Approach: Using Stack Data Structure
     * TC: O(N), SC: O(1)
     * Maintain global pointer and after calling every function recursively, iterate the global pointer and check
     * if global pointer and curren head values are same and return an answer
     * */

    ListNode curr;
    public boolean isPalindromeUsingRecursion(ListNode head) {
        /**
         Using recursion
         */
        curr = head;
        return helper(head);
    }

    private boolean helper(ListNode head){
        if(head == null)
            return true;
        boolean ans = helper(head.next) && curr.val == head.val;
        curr = curr.next;
        return ans;
    }
}
