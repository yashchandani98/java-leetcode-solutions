package dp.tabulation;

public class Leetcode64MinimumPathSum {
    /***
     Classical dp  bottom up approach. Since we can go right and down. So while going from bottom up, we will add current grid value + Math.min(minPathSum[i+1][j](Down) , minPathSum[i][j+1] (Right)).
     TC:  O(m*n)
     SC: O(m*n)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] minPathSum = new int[m+1][n+1];

        for(int i=0; i<=m; i++){
            minPathSum[i][n] = Integer.MAX_VALUE;
        }
        for(int i=0; i<=n; i++){
            minPathSum[m][i] = Integer.MAX_VALUE;
        }
        minPathSum[m-1][n] = 0;

        // Adding one extra row and column so that when we start from bottom up (Tabulation approach) when we compare the previous path sum, we should get infinity value. Keeping minPathSum[m-1][n] as 0 to cover the base case so that grid[i][j] + min(0, Integer.MAX_VALUE) will get the sum as grid[i][j] since min(0, Integer.MAX_VALUE) will give us the min value as 0. Remember since we are going bottom up, so for us the base case is minPathSum[i][j] where i = m-1, j = n-1.


        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                minPathSum[i][j] =  grid[i][j] + Math.min(minPathSum[i+1][j], minPathSum[i][j+1]);
            }
        }

        return minPathSum[0][0];
    }
}
