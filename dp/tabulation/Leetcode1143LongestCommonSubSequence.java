package dp.tabulation;

import java.util.*;
public class Leetcode1143LongestCommonSubSequence {

    /**
     * Since we have two data components (2 strings) for the comparison, we should ideally be using 2d grid or matricx which contains our result.
     * Column indices indicate 1st string indexes or characters
     * Row indices indicates 2nd string indexes or characters.
     *
     * Intuition: We will start either from reverse or from the start indexes.
     * Steps included:
     *  - We will have 2d dp grid with starting value as 0. We will have one extra row and one extra column to compute previous sub-problem.
     *  - In case the characters of both row index and col index match, we will fill it's value as 1 + dp[i+1][j+1] (Diagonally) (Reason being if we go diagonally, we
     *      will try to compare next characters from both the string as the current character of both the string match
     *      )
     *
 *      - In case the characters of both row and col index don't match, we will fill it's value as max(dp[i+1][j], dp[i][j+1])
     *      (j+1=>Looking for next right value in the right cell and i+1=> Looking for next down cell) Going towards right cell or down cell indicates we will try to
     *      match the characters by 1st iterating the next character in string 1(j+1)(Column) and then iterating the next character in string2 (i+1)(Row).
     *
     *   - In the end return the computed answer present in dp[0][0] (Reverse order) or dp[m-1][n-1] (Sequential order)
     *   Note: In case we are going reverse order, we use the previous state or previous sub problem as dp[i+1][j+1] but in case of the sequential order,
     *      we should use the previous state or previous sub-problem as dp[i-1][j-1]
     *
     * Youtube explanation: https://www.youtube.com/watch?v=Ua0GhsJSlWM&ab_channel=NeetCode
     *
     *
     * */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            Arrays.fill(dp[i] , 0);
        }

        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(Objects.equals(text1.charAt(i), text2.charAt(j))){
                    dp[i][j] = 1 + dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        return dp[0][0];
    }
}
