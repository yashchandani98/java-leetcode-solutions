package Simulation;

import java.util.*;
public class Leetcode885SpiralMatrixIII {

    /**
     * Just focus on the Direction and Speed of the spiral flow
     * */
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] response = new int[rows*cols][2];
        int[][] result = new int[rows][cols];

        List<int[]> visitedIndices = new ArrayList<>();

        int spiralSpeed = 0;

        int i = rStart, j = cStart;

        // possible directions : nw(starting direction), se, sw, ne
        String dir = "nw";

        int ele = 1;
        while(visitedIndices.size()<rows*cols){
            if(dir == "nw"){
                dir = "ne";
                int times = ++spiralSpeed;
                while(times-- >0){
                    if(i>=0 && j>=0 && i<rows && j<cols && result[i][j] == 0){
                        visitedIndices.add(new int[]{i, j});
                        result[i][j++] = ele++;
                    } else {
                        j++;
                    }
                }
            } else if(dir == "ne"){
                dir = "se";
                int times = spiralSpeed;
                while(times-- >0){
                    if(i>=0 && j>=0 && i<rows && j<cols && result[i][j] == 0){
                        visitedIndices.add(new int[]{i, j});
                        result[i++][j] = ele++;
                    } else {
                        i++;
                    }
                }
            } else if(dir == "se"){
                dir = "sw";
                int times = ++spiralSpeed;
                System.out.println(spiralSpeed);
                while(times-- >0){
                    if(i>=0 && j>=0 && i<rows && j<cols && result[i][j] == 0){
                        visitedIndices.add(new int[]{i, j});
                        result[i][j--] = ele++;
                    } else {
                        j--;
                    }
                }
            } else if(dir == "sw"){
                dir = "nw";
                int times = spiralSpeed;
                while(times-- >0){
                    if(i>=0 && j>=0 && i<rows && j<cols && result[i][j] == 0){
                        visitedIndices.add(new int[]{i, j});
                        result[i--][j] = ele++;
                    } else {
                        i--;
                    }
                }
            }
        }
        int idx = 0;
        for(int[] indices: visitedIndices){
            response[idx++] = indices;
            System.out.println(Arrays.toString(indices));
        }
        return response;
    }
}
