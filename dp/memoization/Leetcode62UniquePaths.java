package dp.memoization;

import java.util.*;
/**
 DP Memoization Technique
 TC: O(m*n)
 SC: O(m*n)
 */
public class Leetcode62UniquePaths {
    public int uniquePaths(int m, int n) {
        ArrayList<ArrayList<Integer>> dp = new ArrayList<ArrayList<Integer>>(m);
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
