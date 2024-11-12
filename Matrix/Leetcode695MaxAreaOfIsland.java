package Matrix;

public class Leetcode695MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        // dfs(grid, 2, 1, 0);
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1) {
                    int count = 0;
                    count = dfs(grid, i, j, count);
                    res = Math.max(count, res);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int count) {
        int m = grid.length;
        int n = grid[0].length;
        if(i>=m || i<0 || j>=n || j<0 || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;
        count++;

        // int temp = count;

        count+= dfs(grid, i+1, j, 0);

        count+= dfs(grid, i-1, j, 0);

        count+= dfs(grid, i, j+1, 0);

        count+= dfs(grid, i, j-1, 0);

        System.out.println(count);

        return count;
    }
}
