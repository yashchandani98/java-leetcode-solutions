package Greedy;

import java.util.*;

public class NumberOfCoins {
    public static void main(String[] args) {


        ArrayList < Integer > ans = new ArrayList < > ();
//        int V = 49;
//        int coins[] = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
//        int V = 30;
//        int coins[] = {25,10,5};
//        int V = 15;
//        int coins[] = {4};

//        int V = 39;
//        int coins[] = {9,2,11,5,14,17,8,18};

        int V = 26;
        int coins[] = {3,7,6,11,8};

        int n = coins.length;
//        for (int i = n - 1; i >= 0; i--) {
//            while (V >= coins[i]) {
//                V -= coins[i];
//                ans.add(coins[i]);
//            }
//        }
//        System.out.println("The minimum number of coins is "+ans.size());
//        System.out.println("The coins are ");
//        for (int i = 0; i < ans.size(); i++) {
//            System.out.print(" " + ans.get(i));
//        }
        System.out.println(minCoins(coins, coins.length, V));

    }


    public static int minCoins(int coins[], int M, int V)
    {
        Arrays.sort(coins);
        int minCount = Integer.MAX_VALUE;
        int retainV = V;

        for(int i=coins.length-1; i>=0;i--){
            int denom = coins[i];
            V = retainV;
            if(denom == V){
                minCount = 1;
                break;
            }
            int prevMinCount = minCount;
            if(denom<V){
                int j = i;
                int count = 0;
                while(j>=0 && V>=coins[j]){
                    V -= coins[j];
                    count++;
                    while(j>=0 && V<coins[j])
                        j--;
                }
                minCount = Math.min(minCount, count);
            }
            if(V!=0){
                minCount = prevMinCount;
            }
//            System.out.println(V);

        }
        System.out.println(Arrays.toString(coins));

        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

/*DP approach*/
    public static int solve(int n,int x,int num[],int dp[][])
    {
        if(n==0)
        {
            if(x%num[0]==0)
                return x/num[0];
            else
                return (int) Math.pow(10, 9);
        }

        if(dp[n][x]!=-1)
        {
            return dp[n][x];
        }
        int nt=solve(n-1,x,num,dp);
        int ta=(int) Math.pow(10, 9);
        if(x>=num[n])
            ta=1+solve(n,x-num[n],num,dp);
        return dp[n][x]=Math.min(nt,ta);

    }

//    public int minCoins(int num[], int n, int x) {
//        // Your code goes here
//        int dp[][]=new int[n][x+1];
//        for (int row[] : dp)
//            Arrays.fill(row, -1);
//
//        int a=solve(n-1,x,num,dp);
//        if(a>=(int) Math.pow(10, 9))
//            return -1;
//        return a;
//    }
}
