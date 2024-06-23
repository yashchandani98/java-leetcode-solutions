package SlidingWindowAndTwoPointer;


/* 22 June 2024
* We will be greedy here and use two pointer approach, The idea is to use Lptr to the smallest stock value
* and rptr to the largest stock value.
* */

public class Leetcode121BestTimeToBuyStocks {
    public static void main(String[] args) {
        int[] input = {7,6,4,3,1};
        System.out.println(maxProfit(input));
    }

    public static int maxProfit(int[] prices) {
        int lptr = 0, rptr  = 1;
        int maxProfit = 0;
        while(rptr<prices.length){
            if(prices[rptr] < prices[lptr]) {
                lptr = rptr;
                rptr++;
            } else {
                maxProfit = Math.max(maxProfit, prices[rptr] - prices[lptr]);
                rptr++;
            }
        }
        return maxProfit;
    }

}
