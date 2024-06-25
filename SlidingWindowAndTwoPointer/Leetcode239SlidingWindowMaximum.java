package SlidingWindowAndTwoPointer;

import java.util.*;

/**
 The intuition behind this approach is to use fixed sliding window of size k. In each iteration of shifting window towards right, we have to add the largest element in that window in result array.
 We can use two approach here:
 - Approach 1 PriorityQueue: This will give us time complexity of O(nlogn) because in each iteration, we will add and remove element and this will cost  O(logn). and getting top element(maxHeap or max element) will take O(1).
 - Approach 2 Monotonic Deque : This will give us tc of O(n). reason being for every n operation we will use deque to remove and add element and since it's double ended queue it will take O(1) to add and remove element and always the queue will be using property of monotopic (Decreasing order from head to tail) (Preferred DS)
 */
public class Leetcode239SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        System.out.println(maxSlidingWindowApproach2(arr, 3));
    }
    private static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static int[] maxSlidingWindowApproach1(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int lptr = 0, rptr = 0;
        for(;rptr<nums.length;rptr++){
            if(rptr - lptr < k-1){
                pq.add(nums[rptr]);
            } else{
                pq.add(nums[rptr]);
                res.add(pq.peek());
                pq.remove(nums[lptr]);
                lptr++;
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] maxSlidingWindowApproach2(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<Integer> dq = new ArrayDeque<>();
        int rptr = 0;
        for(;rptr<nums.length;rptr++){
            while(!dq.isEmpty() && nums[dq.getLast()] <= nums[rptr]) {
                dq.removeLast();
            }
            dq.addLast(rptr);
            if(dq.getFirst() == rptr-k) {
                dq.removeFirst();
            }

            if(rptr>=k-1){
                res.add(nums[dq.getFirst()]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
