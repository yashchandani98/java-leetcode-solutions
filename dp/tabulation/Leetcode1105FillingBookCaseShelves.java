package dp.tabulation;
import java.util.Arrays;

public class Leetcode1105FillingBookCaseShelves {
    /*
    * We will use Bottom up (Tabulation approach) and start solving subproblem first adding towards the original problem.
    * TC: O(n^2)
    * SC:
    *
    * */
    public int minHeightShelves(int[][] books, int shelfWidth) {
        // DP BottomUp Approach

        int[] dp = new int[books.length+1];
        Arrays.fill(dp, 0);

        for(int i=books.length-1; i>=0; i--){
            int currWidth = shelfWidth;
            int maxHeight = 0;
            dp[i] = Integer.MAX_VALUE;
            for(int j =i;j<books.length;j++){
                int width = books[j][0];
                int height = books[j][1];
                if(currWidth<width) break;
                currWidth-=width;
                maxHeight = Math.max(maxHeight, height);
                dp[i] = Math.min(dp[i], dp[j+1]+maxHeight);
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[0];
    }
}
