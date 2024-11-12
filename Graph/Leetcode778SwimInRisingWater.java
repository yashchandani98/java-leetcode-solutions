package Graph;

import java.util.*;
public class Leetcode778SwimInRisingWater {

    /**
     * Uses Dijkstra's algorithm to update time
     *
     * */
    public int swimInWater(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if(m == 1 && n == 1) return grid[0][0];
        int[][] time = new int[m][n];
        for(int i = 0; i<m; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
        Set<String> visited = new HashSet<>();
        pq.offer(new int[]{0, 0, grid[0][0]});

        while(!pq.isEmpty()) {
            int[] info = pq.poll();
            int row = info[0];
            int col = info[1];
            int currentTime = info[2];

            String indice = "row:"+row+"col:"+col;
            if(visited.contains(indice)) continue;

            visited.add(indice);

            int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

            for(int[] direct: directions) {
                int newRow = row+direct[0];
                int newCol = col+direct[1];
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n ) continue;

                int newTime = Math.max(currentTime, grid[newRow][newCol]);

                if(newTime<time[newRow][newCol]){
                    time[newRow][newCol] = newTime;
                }

                pq.offer(new int[]{newRow, newCol, newTime});
            }
        }

        return time[m-1][n-1];
    }
}
