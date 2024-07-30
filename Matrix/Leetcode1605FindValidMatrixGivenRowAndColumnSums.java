package Matrix;


/*
* We will se two 1-d array to store current sum, currRowSum and currColSum and will update once we update value in the resultant array.
* Logic or intuition: We should always take min of (givenRowSum[i] - currRowSum[i] and givenColSum[j]-currColSum[j]) to play safe and avoid value going out of bond
* in any of the rowSum and columnSum case.
*
* TC: O(m*n)
* SC: O(m+n)
* Note: This was the problem where I created the logic(which is the best one amongst others) by my own intuition. Kudos to me!!
* */
public class Leetcode1605FindValidMatrixGivenRowAndColumnSums {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[] currRowSum = new int[m];
        int[] currColSum = new int[n];
        int[][] result = new int[m][n];
        for(int i=0;i<m;i++)
            for(int j=0; j<n;j++){
                int possibleValue = Math.min(rowSum[i]-currRowSum[i], colSum[j]-currColSum[j]);
                result[i][j] = possibleValue;
                currRowSum[i]+=possibleValue;
                currColSum[j]+=possibleValue;
            }
        return result;
    }
}
