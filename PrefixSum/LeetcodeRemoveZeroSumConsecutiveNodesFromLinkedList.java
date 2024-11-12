package PrefixSum;

public class LeetcodeRemoveZeroSumConsecutiveNodesFromLinkedList {
    /**
     * Use prefixSum technique and HashMap to store the current node with it's prefix Sum. In the next iteration, again perform prefixSum and check if the same prefixSum
     * is present in the hashMap that means from the node next to the current to the mapped node need to be eliminated.. Note: Don't break the loop here as there might be more
     * consecutive subsequences whose sum is equals to zero, we have to eliminate those as well.
     * Edge case: If the currPrefixSum is equals to zero then all the nodes until the current node need to be eliminated. Update head to the curr.next
     * TC: O(2n)
     * SC: O(n)
     * */
    public ListNode removeZeroSumSublists(ListNode head) {

        /***
         use prefixSum technique + HashMap to solve this
         */
        Map<Integer, ListNode> prefixSumToNode = new HashMap<>();
        int currSum = 0;
        ListNode iter = head;

        while(iter!=null){
            currSum+=iter.val;
            prefixSumToNode.put(currSum, iter);
            iter = iter.next;
        }

        iter = head;
        currSum = 0;
        while(iter!=null){
            currSum+=iter.val;
            System.out.println(currSum);
            if(currSum == 0){
                head = iter.next;
            }
            else if(prefixSumToNode.containsKey(currSum) && prefixSumToNode.get(currSum)!=iter) {
                iter.next = prefixSumToNode.get(currSum).next;
            }
            iter = iter.next;
        }

        return head;



    }
}
