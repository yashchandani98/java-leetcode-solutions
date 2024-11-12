package dp.memoization;

import java.util.*;

public class Leetcode63UniquePathsII {
    /**
     * Brute force approach, for every cell, we will try to solve this probelm by visiting down and right cells (basically DFS approach) until we reach the
     * destination by avoiding obstacles, if we reach increase the count.
     * TC: O(2^(M+n))
     * SC: O(m*n) (Recursion tree)
     * Bottom up DP approach.
     * TC: O(m*n), SC: O(m*n)
     *
     * TOP Down DP Approach (Recursion with memoization):
     *
     * */

    public int uniquePathsWithObstaclesMemoization(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for(int i=0; i<m; i++){
            Arrays.fill(dp[i], -1);
        }

        return dfs(0,0,dp, obstacleGrid);
    }

    private int dfs(int i, int j, int[][] dp, int[][] obstacleGrid){
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if(i == m || j == n || obstacleGrid[i][j] == 1){
            return 0;
        }
        if( i == m-1 && j == n-1){
            return 1;
        }
        if(dp[i][j] == -1){
            dp[i][j] = dfs(i+1, j, dp, obstacleGrid) + dfs(i, j+1, dp, obstacleGrid);
        }
        return dp[i][j];
    }
    public int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<m; i++){
            dp[i][n] = 0;
        }

        for(int i=0; i<n; i++){
            dp[m][i] = 0;
        }

        // Bottom-up dp

        for(int i = m-1; i>=0; i--){
            for(int j = n-1; j>=0; j--){
                if(i==(m-1) && j==(n-1) && obstacleGrid[i][j]!=1){
                    dp[i][j] = 1;
                }
                else if(obstacleGrid[i][j]!=1){
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }
        for(int i = 0; i<m; i++){
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[0][0];
    }
}
