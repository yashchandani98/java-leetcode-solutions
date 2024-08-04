package Matrix;
import java.util.*;
public class Leetcode994RootingOranges {
    public int orangesRotting(int[][] grid) {
        // Perform BFS to implement algo

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> bfs = new LinkedList<>();
        int freshOranges = 0;
        int time = 0;

        for(int i=0; i<grid.length; i++)
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 2){
                    bfs.offer(new int[]{i,j});
                } else if(grid[i][j] == 1)
                    freshOranges++;
            }
        if(freshOranges == 0) return 0;
        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

        while(!bfs.isEmpty()){
            int size = bfs.size();
            for(int i=0; i<size; i++){
                int[] indices = bfs.poll();
                int row = indices[0];
                int col = indices[1];
                for(int[] direction: directions){
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];
                    if(newRow>=0 && newRow<rows && newCol>=0 && newCol<cols && grid[newRow][newCol] == 1){
                        grid[newRow][newCol] = 2;
                        freshOranges--;
                        bfs.offer(new int[]{newRow, newCol});
                    }
                }
            }
            time++;
        }

        return freshOranges == 0 ? time -1 : -1;

    }
}
