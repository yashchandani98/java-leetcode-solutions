package Arrays;

public class Leetcode48RotateImage {
    public void rotate(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        // Rotation of matrix can be achieved using Transpose(i.e. swapping row values with column values except diagonal) and Reverse all the rows in the matrix.
        for(int i=0; i<row; i++){
            for(int j=0; j<i;j++){
                // Swapping row and column value except diagonal value
                if( i!=j ){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        // Reversing all the rows. i.e matrix[i][j] and matrix[i][col-j-1] values are exchenaged for every row;
        for(int i=0; i<row; i++){
            for(int j=0; j<col/2; j++){
                int temp = matrix[i][col-j-1];
                matrix[i][col-j-1] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}
