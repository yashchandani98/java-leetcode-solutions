package dp.memoization;

import java.util.*;

public class Leetcode309BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        Map<String, Integer> cache = new HashMap<>();

        return solve(0, true, cache, prices);
    }


    private int solve(int dayIdx, boolean buy, Map<String, Integer> cache,int[] prices){
        if(dayIdx>=prices.length){
            return 0;
        }

        String cacheKey = String.valueOf(dayIdx) + String.valueOf(buy);

        if(cache.containsKey(cacheKey)){
            return cache.get(cacheKey);
        }

        int profit = 0;

        int maxProfit = 0;

        if(buy){
            profit = solve(dayIdx+1, false, cache, prices);
            profit -= prices[dayIdx];
            maxProfit = Math.max(profit, maxProfit);
        } else {
            // cooldown i.e. dayIdx+2
            profit = solve(dayIdx+2, true, cache, prices);
            profit += prices[dayIdx];
            maxProfit = Math.max(profit, maxProfit);
        }

        // Again cooldown i.e. don't do any transaction
        profit = solve(dayIdx+1, buy, cache, prices);
        profit = Math.max(profit, maxProfit);

        cache.put(cacheKey, profit);

        return profit;

    }
}
