package dp.tabulation;

import java.util.*;
public class Leetcode72EditDistance {

    /**
     * DP Bottom up approach. Base case is when either of the two string is empty
     *
     * This problem is similar to LCS (Longest common subsequence problem)
     *
     *
     * Video explanation: https://www.youtube.com/watch?v=XYi2-LPrwm4&ab_channel=NeetCode
     *
     * */
    public int minDistance(String word1, String word2) {
        // row indicates word1 indices, col indicates word2 indices
        int m = word1.length(), n = word2.length();
        int[][] minOperations = new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            Arrays.fill(minOperations[i], Integer.MAX_VALUE);
        }

        for(int i=0;i<=m;i++){
            minOperations[i][n] = m - i;
        }

        for(int i=0;i<=n;i++){
            minOperations[m][i] = n - i;
        }



        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(word1.charAt(i) == word2.charAt(j)){
                    minOperations[i][j] = minOperations[i+1][j+1];
                } else {
                    minOperations[i][j] = 1 + Math.min(minOperations[i][j+1], Math.min(minOperations[i+1][j], minOperations[i+1][j+1]));
                }
            }
        }

        return minOperations[0][0];
    }
}
