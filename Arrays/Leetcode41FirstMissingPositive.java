package Arrays;

public class Leetcode41FirstMissingPositive {

    /**
     * The approach here is to mark the presence of element a negative number
     * TC: O(3n)
     * SC: O(1)
     * */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for(int i=0; i<nums.length; i++) {
            nums[i] = Math.max(0, nums[i]);
        }

        for(int i=0; i<nums.length; i++) {
            int val = Math.abs(nums[i]);
            if(1<=val && val<=len){
                if(nums[val-1] > 0) {
                    nums[val-1]  = nums[val-1] * -1;
                } else if(nums[val-1] == 0) {
                    nums[val-1] = -1 * (len+1);
                }
            }
        }


        for(int i=1; i<=nums.length; i++) {
            if(nums[i-1] >=0 ) return i;
        }

        return len+1;

    }}
