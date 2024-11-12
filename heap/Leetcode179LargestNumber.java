package heap;

import java.util.*;
public class Leetcode179LargestNumber {
    /**
     * - Use maxHeap to store the number in the format such that it's string's lexicographically larger than the other subsequent numbers in a string format
     * - Use custom comparator and compare [str(nums1) + str(nums2)] with [str(nums2) + str(nums2)] and store it in decreasing lexicographical order
     * - We could have used  sorting approach as well with the same TC and SC.
     * TC: O(nlogn) + O(n)
     * SC: O(n)
     * */
    public String largestNumber(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer num1, Integer num2) -> {
            String s1 = num1.toString() + num2.toString();
            String s2 = num2.toString() + num1.toString();

            return s2.compareTo(s1);
        });

        for(int num: nums){
            pq.offer(num);
        }

        if(pq.peek() == 0) return "0";

        StringBuilder result = new StringBuilder();

        while(!pq.isEmpty()){
            result.append(pq.poll());
        }
        return result.toString();
    }
}
