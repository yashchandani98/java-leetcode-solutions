package dp.tabulation;


import java.util.*;
public class Codingninjas01Knapsack {

    /**
     * DP Tabulation approach.
     * Problem: https://www.naukri.com/code360/problems/1072980
     * Refer: https://www.youtube.com/watch?v=8LusJS5-AGo&ab_channel=TusharRoy-CodingMadeSimple
     *
     * TC: O(w*n) w = given weight, n = number of items
     * */

    public static int maxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w) {
        int[][] profits = new int[n+1][w+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=w;j++){
                // Here we are referring to index-1 position since we are starting from 1st index for both row and column
                int currWeight = weights.get(i-1);
                int currValue = values.get(i-1);
                if(currWeight<=j){
                    profits[i][j] = Math.max(currValue+profits[i-1][j-currWeight], profits[i-1][j]);
                } else {
                    profits[i][j] = profits[i-1][j];
                }
            }
        }
        return profits[n][w];
    }
}
