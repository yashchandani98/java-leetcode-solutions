package Recursion;

import java.util.*;
public class Codingninjas01Knapsack {

    /**
     * We will iterate through every items and have two choice, to either include it or exclude it. Even though it will be solved using dp because using
     * recursion and backtracking it has the exponential time complexity of O(2^n). In the end we will return maximumValue from the method recursively
     * TC: O(2^n)
     * */

    public static int maxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w) {
        // Recursive and Backtracking approach:

        return solve(0, 0, values, weights, n, w);
    }

    private static int solve(int itemIdx, int totalValue, ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w){
        if(itemIdx>=n || w <=0){
            // base case
            return totalValue;
        }

        int valueWithoutItem = solve(itemIdx + 1, totalValue, values, weights, n, w);
        int valueWithItem = 0;
        if(weights.get(itemIdx)<=w){
            valueWithItem = solve(itemIdx+1, totalValue+values.get(itemIdx), values, weights, n, w-weights.get(itemIdx));
        }

        return Math.max(valueWithoutItem, valueWithItem);


    }
}
