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
        int currSum = 0;
        for (int num : nums) {
            currSum = currSum + num;
            maxSum = Math.max(maxSum, currSum);
            if (currSum < 0) {
                //Reset phase
                currSum = 0;
            }
        }
        return maxSum;
    }
}
