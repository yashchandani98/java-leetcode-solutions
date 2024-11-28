package dp.memoization;


import java.util.*;
public class Leetcode714BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        Map<String, Integer> cache = new HashMap<>();

        return solve(0, true, cache, prices, fee);
    }

    private int solve(int dayIdx, boolean buy, Map<String, Integer> cache, int[] prices, int fee){
        if(dayIdx>=prices.length){
            return 0;
        }

        String cacheKey = String.valueOf(dayIdx) + String.valueOf(buy);

        if(cache.containsKey(cacheKey)){
            return cache.get(cacheKey);
        }

        int profit = 0, maxProfit = 0;

        if(buy){
            profit = solve(dayIdx+1, false, cache, prices, fee);
            profit -= prices[dayIdx];
            maxProfit = Math.max(profit, maxProfit);
        } else {
            profit = solve(dayIdx+1, true, cache, prices, fee);
            profit += prices[dayIdx];
            profit -= fee;
            maxProfit = Math.max(profit, maxProfit);
        }

        profit = solve(dayIdx+1, buy, cache, prices, fee);
        maxProfit = Math.max(profit, maxProfit);

        profit = Math.max(profit, maxProfit);

        cache.put(cacheKey, profit);

        return profit;

    }

}
