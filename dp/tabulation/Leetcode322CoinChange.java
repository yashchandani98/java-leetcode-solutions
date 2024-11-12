package dp.tabulation;
import java.util.*;
public class Leetcode322CoinChange {

    /**
     * Use Bottom up dp approach (Bottom up dp approach doesn't mean traversing in the reverse 2 d matrix and fill it. It means that solving all the sub-problems first
     * and combining those sub-problems to solve a problem).
     *
     * This problem pattern is similar 0/1 knapsack problem.
     * TC: O(N*amount)
     * SC: O(N*amount)
     * n = coins
     *
     * */
    public int coinChange(int[] coins, int amount) {
        int m = coins.length;
        int[][] dp = new int[m+1][amount+1];

        for (int j = 1; j <= amount; j++) {
            // We initialize it to Integer.MAX_VALUE indicating it's not possible to achieve j amount with i=0 coin value.
            dp[0][j] = Integer.MAX_VALUE;
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=amount;j++){
                if (j >= coins[i - 1]) {
                    /* It means that using i coin value and j - coin[i] value (Denomination) , it's not possible to achieve j amount. Hence  copying previous coin's
                     value */
                    if (dp[i][j - coins[i - 1]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }


        return dp[m][amount] > amount ? -1 : dp[m][amount];


    }

    public int coinChange1ddp(int[] coins, int amount) {
        // using 1 d dp

        int[] dp = new int[amount+1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=1; i<=amount; i++){
            for(int coin: coins){
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
}
