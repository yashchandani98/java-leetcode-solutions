package heap;

import java.util.*;
import java.util.stream.Collectors;
public class Leetcode215kthLargestElementInArray {
    /**
     * Brute force approach:
         * Sort an input array and startfrom the right and return the kth element from the last.
         * TC: O(nLogn)
         * SC: O(1)
     * Optimal Approach:
         * Add the array elements in minHeap but maintain the size of k in minHeap. Poll the topmost element
         * if it is smaller than array encountered value. if array size is exactly queals to k. In the end
         * return the topmost element of a minHeap
     * */
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
        pq.addAll(list);

        int res = 0;
        while(k>0){
            res = pq.poll();
            k--;
        }
        return res;
    }
    public int findKthLargestMinHeapApproach(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for(int num: nums){
            if(pq.size()<k){
                pq.offer(num);
            } else {
                if(pq.peek()<num){
                    pq.poll();
                    pq.offer(num);
                }
            }
        }
        return pq.peek();
    }
}
