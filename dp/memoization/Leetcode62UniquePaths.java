package dp.memoization;

import java.util.*;
/**
 DP Memoization Technique
 TC: O(m*n)
 SC: O(m*n)
 */
public class Leetcode62UniquePaths {

    // Tabulation Approach:
    public int uniquePathsTabulation(int m, int n) {
        int[][] tabulation = new int[m+1][n+1];

        for(int i=0; i<=m;i++){
            // fill the value of all the rows with the last column as 0
            tabulation[i][n] = 0;
        }
        for(int j=0; j<=n;j++){
            // fill the value of all the column with the last row as 0
            tabulation[m][j] = 0;
        }

        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1 && j == n-1){
                    // Since starting from finish, there is only 1 possibility hence filling the value as 1
                    tabulation[i][j] = 1;
                } else {
                    // tabulation[i+1][j] -> Down direction, tabulation[i][j+1] -> right direction
                    tabulation[i][j] = tabulation[i+1][j] + tabulation[i][j+1];
                }
            }
        }
        return tabulation[0][0];
    }


    public int uniquePathsMemoization(int m, int n) {
        int[][] memo = new int[m][n];

        for(int i=0; i<m; i++){
            // initialize the grid with -1 since the value for every grid or problem is unknown
            Arrays.fill(memo[i], -1);
        }

        return solve(0, 0, memo);
    }

    private int solve(int i, int j, int[][] memo){
        int m = memo.length, n= memo[0].length;
        if(i == m || j == n){
            // out of bound
            return 0;
        }
        if(i== (m - 1) && j == (n - 1)){
            // destination
            return 1;
        }

        if(memo[i][j] == -1){
            int count = solve(i+1, j, memo) + solve(i, j+1, memo);
            memo[i][j] = count;
        }
        return memo[i][j];
    }

    public int uniquePaths(int m, int n) {
        ArrayList<ArrayList<Integer>> dp = new ArrayList<>(m);
        for(int i=0;i<m;i++){
            dp.add(new ArrayList<>());
            for(int k=0;k<n;k++){
                dp.get(i).add(k, -1);
            }
        }
        return countWays(0,0,m,n, dp);
    }

    private int countWays(int row,  int column, int m, int n, ArrayList<ArrayList<Integer>> dp){
        if(row == m || column == n){
            return 0;
        }
        if(row == m-1 && column == n-1){
            return 1;
        }
        if(dp.get(row).get(column) == -1){
            int count =  countWays(row+1, column, m, n, dp);
            count+=countWays(row, column+1, m, n, dp);
            dp.get(row).set(column, count);
        }
        return dp.get(row).get(column);
    }
}
