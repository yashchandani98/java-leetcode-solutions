package Graph;

public class Leetcode1020NumberOfEnclaves {

    /**
     * (Boundary or edge  traversal) Just traverse from the edges of the matrix and mark all it's adjacent nodes to 0 and traverse those nodes as well using dfs approach. If a node's value is 0
     * just return from the dfs function.
     *
     * In the end, traverse the matrix again and count number of 1's
     *
     * TC: O(m*n)
     * SC: O(1)
     * */

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n= grid[0].length, count = 0;

        for(int j=0;j<m;j++){
            // Traversing from the 1st row to the last row while keeping column as contstant as 0 and n-1
            dfs(j, 0, grid);
            dfs(j, n-1, grid);
        }

        for(int i=0;i<n;i++){
            // Traversing from the 1st column to the last column while keeping row as contstant as 0 and m-1
            dfs(0, i, grid);
            dfs(m-1, i, grid);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    count++;
                }
            }
        }

        return count;
    }



    private void dfs(int i, int j , int[][] grid){
        int m = grid.length, n= grid[0].length;
        if((i<0 || j<0 || i>=m || j>=n)){
            return;
        }
        if(grid[i][j] == 0) return;
        grid[i][j] = 0;
        dfs(i+1, j, grid);
        dfs(i-1, j, grid);
        dfs(i, j+1, grid);
        dfs(i, j-1, grid);
        return;
    }
}
