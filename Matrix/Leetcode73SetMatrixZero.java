package Matrix;

import java.util.*;
/*
* Here tht intuition is to get the row and col number of the original zero and make all non-zero numbers in the matrix to zero and keep on adding the
* indices of the non-zero numbers to the Hashset so that in the iteration we should not pick the elements(which were originally non-zero) to make zero.
* */
public class Leetcode73SetMatrixZero {
    public static void main(String[] args){

    }
    Set<String> indices = new HashSet<>();
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                String rowColKey = i + "-" +j;
                if(matrix[i][j] == 0 && !indices.contains(rowColKey)){
                    setRowAndColZero(i, j, matrix);
                    indices.add(rowColKey);
                }
            }
        }
    }

    private void setRowAndColZero(int row, int col, int[][] matrix){
        int m = matrix.length, n = matrix[0].length;

        // Let's set col to 0
        for(int i=0; i<m; i++){
            String rowColKey = i + "-" +col;
            if(matrix[i][col] !=0 && !indices.contains(rowColKey)){
                matrix[i][col] = 0;
                indices.add(rowColKey);
            }
        }

        for(int i=0; i<n; i++){
            String rowColKey = row + "-" + i;
            if(matrix[row][i]!=0 && !indices.contains(rowColKey)){
                matrix[row][i] = 0;
                indices.add(rowColKey);
            }
        }
    }
}
