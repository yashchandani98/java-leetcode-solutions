package SlidingWindowAndTwoPointer;


/*
* Kadane's Algorithm: Keep on iterating an array and keep on updating maxsum with currsum.
* TC: O(n)
* */
public class Leetcode53MaxSubArray {
    public int maxSubArray(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int maxSum = Integer.MIN_VALUE;
        int left_ptr=0, right_ptr = 0;
        int currSum = 0;
        for(;right_ptr<nums.length;right_ptr++){
            currSum = currSum + nums[right_ptr];
            maxSum = Math.max(maxSum, currSum);
            if(currSum<0){
                //Reset phase
                currSum = 0;
                left_ptr = right_ptr+1;
            }
        }
        return maxSum;
    }
}
