package dp.tabulation;

import java.util.Arrays;
public class Leetcode55JumpGame {
    /**
     * There are two approaches here:
     * DP Approach: Create a 1d dp similar to nums length and initialize all the positions to False except dp[nums.length -1]. Make it true and start an iteration
     * from behind, update ith position in dp array using or between all the values from i+1 cell to the max cell position it reaches. In the end return dp[0]
     * TC:O(n^2)
     * SC:O(n)
     * */
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        Arrays.fill(dp, false);

        dp[nums.length -1] = true;

        for(int i =nums.length-2; i>=0; i--) {
            int steps = nums[i];
            for(int j=1; j<=steps; j++){
                if((i+j)<nums.length){
                    dp[i] = dp[i+j] || dp[i];
                    if(dp[i]) break;
                }
            }
        }
        return dp[0];
    }
}
