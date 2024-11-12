package Arrays;

public class Leetcode152MaxProductSubarray {
    /***
     * If the given array also contains an odd number of negative numbers: Now, an odd number of negative numbers when multiplied result in a negative number. Removal of 1 negative number out of the odd number of negative numbers will leave us with an even number of negatives. Hence the idea is to remove 1 negative number from the result. Now we need to decide which 1 negative number to remove such that the remaining subarray yields the maximum product.
     *
     * For example, the given array is: {3, 2, -1, 4, -6, 3, -2, 6}
     * We will try to remove each possible negative number and check in which case the subarray yields the maximum product.
     * Upon observation, we notice that each chosen negative number divides the array into two parts.
     * The answer will either be the prefix or the suffix of that negative number.
     * To find the answer, we will check all possible prefix subarrays (starting from index 0) and all possible suffix subarrays (starting from index n-1).
     * The maximum product obtained from these prefix and suffix subarrays will be our final answer.
     * If the array contains 0’s as well: We should never consider 0’s in our answer(as considering 0 will always result in 0) and we want to obtain the maximum possible product. So, we will divide the given array based on the location of the 0’s and apply the logic of case 3 for each subarray.
     * TC: O(n)
     * SC: O(1)
     */
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;

        int prefix = 1;
        int suffix = 1;

        for(int i=0; i<nums.length; i++){
            if(prefix == 0){
                prefix = 1;
            }
            if(suffix == 0){
                suffix = 1;
            }
            prefix *= nums[i];
            suffix *= nums[nums.length-i-1];
            res = Math.max(res, Math.max(prefix, suffix));
        }

        return res;
    }
}
