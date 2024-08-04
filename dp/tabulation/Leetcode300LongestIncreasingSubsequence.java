package dp.tabulation;

import java.util.Arrays;
public class Leetcode300LongestIncreasingSubsequence {
    // Bottom Up DP Approach
    public int lengthOfLIS(int[] nums) {
        // Initializing res by 1 since every element could be longest Subsequence
        int res = 1;
        int len = nums.length;
        // 1d array which will store longest subsequence for every element
        int[] lis = new int[len];
        // Initializing by 1 since every element could be longest Subsequence
        Arrays.fill(lis,1);
        for(int i=1;i<len;i++){
            int maxLen = 0;
            for(int j=i-1; j>=0;j--){
                if(nums[j]<nums[i]){
                    // Leverage the subproblem (will take max of the longest subsequence of every element which is stored in lis)
                    maxLen = Math.max(maxLen, lis[j]);
                }
            }
            // Store longest common Subsequence for every element.
            lis[i] = 1+maxLen;
            res = Math.max(res, lis[i]);
        }
        return res;
    }
}
