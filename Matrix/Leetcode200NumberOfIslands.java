package Matrix;

import java.util.*;

public class Leetcode200NumberOfIslands {
    Set<String> visited = new HashSet<>();
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                String indice = String.valueOf(i) + String.valueOf(j);
                if(grid[i][j] == '1' && !visited.contains(indice)) {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if(i>=m || i<0 || j>=n || j<0 || grid[i][j] == '0'){
            return;
        }
        String indice = String.valueOf(i) + String.valueOf(j);
        // if(visited.contains(indice)) {
        //     return;
        // }
        // visited.add(indice);
        grid[i][j] = '0';

        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }
}
