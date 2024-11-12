package Simulation;

import java.util.*;
public class Leetcode2326SpiralMatrixIV {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] result = new int[m][n];
        for(int i=0;i<m;i++)
            Arrays.fill(result[i], -1);
        ListNode iter = head;

        String RIGHT = "Right", LEFT = "Left", UP = "Up", DOWN = "Down";
        String direction = RIGHT;
        int currRow = 0, currCol = 0;
        Map<String, int[]> directions = new HashMap<>();

        directions.put(RIGHT, new int[]{0,1});
        directions.put(LEFT, new int[]{0,-1});
        directions.put(UP, new int[]{-1,0});
        directions.put(DOWN, new int[]{1,0});

        while(iter!=null){
            result[currRow][currCol] = iter.val;
            if(direction == RIGHT){
                if(currCol == n-1 || result[currRow][currCol+1] !=-1) direction = DOWN;
            }
            else if(direction == DOWN){
                if(currRow == m-1 || result[currRow+1][currCol] !=-1) direction = LEFT;
            }
            else if(direction == LEFT){
                if(currCol == 0 || result[currRow][currCol-1] !=-1) direction = UP;
            }
            else if(direction == UP){
                if(currRow == 0 || result[currRow-1][currCol] !=-1) direction = RIGHT;
            }
            currRow += directions.get(direction)[0];
            currCol += directions.get(direction)[1];
            iter=iter.next;
        }

        return result;
    }
}
