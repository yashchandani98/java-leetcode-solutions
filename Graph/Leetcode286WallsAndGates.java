package Graph;

import java.util.*;

public class Leetcode286WallsAndGates {

    /**
     * Problem: You are given a m x n 2D grid initialized with these three possible values.
     *
     * -1 - A wall or an obstacle.
     * 0 - A gate.
     * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     *
     * Note: This problem is very much similar to rotten oranges in which we have to simultaneously process all the cells at the same time.
     *
     * Approach: Intuition is to start from the gates, and move towards al 4 direction and simultaneously update distance of all the 4 adjacent matrix cells with distance = curr+1;
     * Algorithm: BFS
     * TC: O(m*n)
     * SC: O(m*n) (Queue Size)
     * **/


    public static void wallsAndGates(int[][] rooms){
        int m = rooms.length, n = rooms[0].length;
        int INF = Integer.MAX_VALUE;

        Queue<int[]> que = new LinkedList<>();

        for(int i=0;i<m; i++){
            for(int j=0; j<n; j++){
                if(rooms[i][j] == 0){
                    que.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

        int distance = 0;
        while(!que.isEmpty()){
            int size = que.size();
            distance++;
            for(int i=0;i<size;i++){
                int[] indices = que.poll();
                for(int[] direction: directions){
                    int row = indices[0] + direction[0];
                    int col = indices[1] + direction[1];
                    if(row<0 || col <0 || row>=m || col>=n || rooms[row][col]!=INF || rooms[row][col]==-1 || rooms[row][col]==0) continue;
                    rooms[row][col] = distance;
                    que.offer(new int[]{row, col});
                }
            }

        }
    }

    public static void main(String[] args){
        final int INF = Integer.MAX_VALUE;
        int[][] grid1 = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };
        System.out.println("Test Case 1:");
        printGrid(grid1);
        wallsAndGates(grid1);
        printGrid(grid1);

        // Test Case 2: Grid with No Gates
        int[][] grid2 = {
                {INF, INF},
                {INF, INF}
        };
        System.out.println("Test Case 2:");
        printGrid(grid2);
        wallsAndGates(grid2);
        printGrid(grid2);

        // Test Case 3: Grid with No Empty Rooms
        int[][] grid3 = {
                {0, -1},
                {-1, 0}
        };
        System.out.println("Test Case 3:");
        printGrid(grid3);
        wallsAndGates(grid3);
        printGrid(grid3);

        // Test Case 4: Single Gate in the Middle
        int[][] grid4 = {
                {INF, INF, INF},
                {INF, 0, INF},
                {INF, INF, INF}
        };
        System.out.println("Test Case 4:");
        printGrid(grid4);
        wallsAndGates(grid4);
        printGrid(grid4);

        // Test Case 5: Large Grid with Multiple Gates
        int[][] grid5 = {
                {INF, -1, 0, INF, INF},
                {INF, INF, INF, -1, INF},
                {INF, -1, INF, -1, INF},
                {0, -1, INF, INF, INF},
                {INF, -1, -1, -1, 0}
        };
        System.out.println("Test Case 5:");
        printGrid(grid5);
        wallsAndGates(grid5);
        printGrid(grid5);
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

}
