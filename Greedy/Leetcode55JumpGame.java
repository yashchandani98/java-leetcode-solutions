package Greedy;

public class Leetcode55JumpGame {
    /***
     * Use maxIdx for the tracking purpose. Iteratively update maxIdx to the max it can reach. if currentIdx is not reachable by maxIdx, return False
     * immediately in the iteration.
     * TC: O(n)
     *
     */
    public boolean canJump(int[] nums) {
        if(nums[0] == 0 && nums.length>1) return false;
        int maxidx = 0;
        int len = nums.length;

        for(int i=0; i<nums.length-1; i++) {
            if(i>maxidx){
                return false;
            }
            maxidx = Math.max(maxidx, i+nums[i]);
            if(maxidx >= (len -1)){
                return true;
            }
        }

        return maxidx>=(nums.length-1);
    }
}
