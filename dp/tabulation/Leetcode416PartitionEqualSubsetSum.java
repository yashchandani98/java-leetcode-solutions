package dp.tabulation;

public class Leetcode416PartitionEqualSubsetSum {
    /**
     *
     * Use DP Tabulation Approach. First we calculate sum of all the elements, if sum is odd, it's not possible to divide the array into two halves, if it is even, we
     * will try to find if it is possible to get the sum equals to sum(array) / 2 if yes then it's possible to divide array into two halves. Now this problem is broken down
     * into the problem where we will try to find if it is possible to get the subset sum equals to half of the whole array sum. column represents sum and row represents
     * array element. Similar to coin change problem
     *
     * */
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        int m = nums.length;

        for(int num : nums){
            totalSum+=num;
        }

        if(totalSum % 2 != 0) return false;
        boolean[][] isPartitionPossible = new boolean[m+1][(totalSum/2)+1];

        totalSum/=2;

        for(int i=0;i<=totalSum;i++){
            // in the row if we observe, if the element value is 0 (i value), any element in the array (j value) can't form the sum i.e. the value is False
            isPartitionPossible[0][i] = false;
        }

        for(int i=0;i<=m;i++){
            // in the column if we observe, if the sum is 0 (j value), any element in the array (i value) can form the sum i.e. the value is True
            isPartitionPossible[i][0] = true;
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=totalSum; j++){
                if(j>=nums[i-1]){
                    isPartitionPossible[i][j] = isPartitionPossible[i-1][j-nums[i-1]] || isPartitionPossible[i-1][j];
                } else {
                    isPartitionPossible[i][j] = isPartitionPossible[i-1][j];
                }
            }
        }

        return isPartitionPossible[m][totalSum];

    }
}
