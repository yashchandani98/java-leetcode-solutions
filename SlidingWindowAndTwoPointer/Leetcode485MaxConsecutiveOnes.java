package SlidingWindowAndTwoPointer;


/*
* Use Two pointer approach and keep on calculating result as soon as right_ptr encounters 1 else assign left_ptr as right_ptr;
*
* */
public class Leetcode485MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        /**
         Two pointer approach
         */

        int left_ptr = 0;
        int right_ptr = 0;
        int res = 0;

        while(left_ptr<=right_ptr){
            if(nums[right_ptr] == 1){
                res = Math.max(res, right_ptr-left_ptr+1);
                if(right_ptr<nums.length-1){
                    right_ptr++;
                } else{
                    break;
                }
            } else{
                if(right_ptr<nums.length-1)
                    right_ptr++;
                else
                    break;
                left_ptr = right_ptr;
            }
        }
        return res;

    }
}
