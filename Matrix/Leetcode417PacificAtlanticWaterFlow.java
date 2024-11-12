package Matrix;

import java.util.*;
public class Leetcode417PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];


        for(int i=0;i<m;i++){
            dfs(i, 0, pacific, heights, Integer.MIN_VALUE);
            dfs(i, n-1, atlantic, heights, Integer.MIN_VALUE);
        }

        for(int i=0;i<n;i++){
            dfs(0, i, pacific, heights, Integer.MIN_VALUE);
            dfs(m-1, i, atlantic, heights, Integer.MIN_VALUE);
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;

    }


    private void dfs(int i, int j, boolean[][] ocean, int[][] heights, int prevHeight){
        int m = heights.length;
        int n = heights[0].length;

        if(i<0 || j<0 || i>=m || j>=n || ocean[i][j] || prevHeight>heights[i][j]) {
            return;
        }
        ocean[i][j] = true;
        dfs(i+1, j, ocean, heights, heights[i][j]);
        dfs(i-1, j, ocean, heights, heights[i][j]);
        dfs(i, j+1, ocean, heights, heights[i][j]);
        dfs(i, j-1, ocean, heights, heights[i][j]);
    }
}
