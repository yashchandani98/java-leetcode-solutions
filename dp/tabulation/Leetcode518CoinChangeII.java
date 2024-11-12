package dp.tabulation;

public class Leetcode518CoinChangeII {
    public int change(int amount, int[] coins) {
        int m = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;  // Base case: There's exactly one way to make amount 0: use no coins.

        for (int i = 0; i < m; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
//        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
}
