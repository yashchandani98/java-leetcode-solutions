package BinarySearch;

/*
*
*
*
1st Approach is to use Binary Search for 1d array.
- First make this 2d array as 1d array in the rough notebook.
- Then apply binary search in that matrix.
- Calculate row and cols according to the mid index in 1d array for 2d matrix to apply binary search.
- TC: O(logmn)

2nd approach is to use binary search twice on matrix:
- 1st to get the row number.
- 2nd to get the column number using that row number.
* TC: O(logm + logn)
*
*
* */
public class Leetcode74Search2dMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int row = 0, cols = 0, left = 0, right = m*n - 1, mid = 0;
        boolean res = false;
        while(left<=right){
            mid = (left+right)/2;
            row = mid / n;
            cols = mid % n;
            if(matrix[row][cols] == target){
                res = true;
                break;
            }
            if(matrix[row][cols]<target){
                left = mid+1;
            }
            if(matrix[row][cols]>target){
                right = mid - 1;
            }
        }
        return res;
    }

}
