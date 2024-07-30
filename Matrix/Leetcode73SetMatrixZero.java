package Matrix;

import java.util.*;
/*
* Here tht intuition is to get the row and col number of the original zero and make all non-zero numbers in th ematrix to zero and keep on adding the
* indices of the non-zero numbers to the Hashset so that in the iteration we should not pick the elements(which were originally non-zero) to make zero.
* */
public class Leetcode73SetMatrixZero {
    public static void main(String[] args){

    }
    private Set<LinkedList<Integer>> rowColIndices;
    public void setZeroes(int[][] matrix) {
        rowColIndices = new HashSet<>();
        for(int i =0; i< matrix.length; i++){
            for(int j=0; j< matrix[i].length; j++){
                LinkedList<Integer> currentRowAndCols = new LinkedList<>();
                currentRowAndCols.add(i);
                currentRowAndCols.add(j);
                if(matrix[i][j] == 0 && !rowColIndices.contains(currentRowAndCols)){
                    setMatrixZeros(i, j, matrix);
                }
            }
        }
    }

    private void setMatrixZeros(int row, int column, int[][] matrix){
        for(int i=0; i< matrix[0].length;i++){
            LinkedList<Integer> currentRowAndCols = new LinkedList<>();
            currentRowAndCols.add(row);
            currentRowAndCols.add(i);
            if(matrix[row][i] !=0){
                matrix[row][i] = 0;
                rowColIndices.add(currentRowAndCols);
            }
        }
        for(int i=0; i< matrix.length;i++){
            LinkedList<Integer> currentRowAndCols = new LinkedList<>();
            currentRowAndCols.add(i);
            currentRowAndCols.add(column);
            if(matrix[i][column] != 0){
                matrix[i][column] = 0;
                rowColIndices.add(currentRowAndCols);
            }
        }
    }
}
