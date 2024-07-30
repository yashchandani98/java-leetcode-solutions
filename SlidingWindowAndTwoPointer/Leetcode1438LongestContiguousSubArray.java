package SlidingWindowAndTwoPointer;



import java.util.*;


/*
* Use Sliding Window and Monotonic Deque approach
*
*
* */

public class Leetcode1438LongestContiguousSubArray {
    public int longestSubarray(int[] nums, int limit) {

        int left_ptr = 0, right_ptr = 0;
        Deque<Integer> max_que = new ArrayDeque<Integer>();
        Deque<Integer> min_que = new ArrayDeque<Integer>();
        int res = 0;
        while(right_ptr<nums.length){
            if(max_que.size() == 0 && min_que.size() == 0){
                max_que.offerLast(nums[right_ptr]);
                min_que.offerLast(nums[right_ptr]);
            } else{
                while(min_que.size() > 0 && nums[right_ptr] < min_que.peekLast()){
                    min_que.removeLast();
                }
                min_que.offerLast(nums[right_ptr]);

                while(max_que.size() >0 && nums[right_ptr] > max_que.peekLast()){
                    max_que.removeLast();
                }
                max_que.offerLast(nums[right_ptr]);
            }

            while(max_que.peekFirst() - min_que.peekFirst() > limit){
                if(nums[left_ptr] == max_que.peekFirst()){
                    max_que.removeFirst();
                }
                if(nums[left_ptr] == min_que.peekFirst()){
                    min_que.removeFirst();
                }
                left_ptr++;
            }

            // if(Math.abs(min_que.peekLast()-max_que.peekLast()) <= limit){
            res = Math.max(res, (right_ptr-left_ptr+1));

            // }
            right_ptr++;
        }

        return res;
    }
}
