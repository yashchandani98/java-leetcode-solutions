package SlidingWindowAndTwoPointer;

import java.util.*;
public class Leetcode1508RangeSumOfSortedSubarraySums {
    public int rangeSum(int[] nums, int n, int left, int right) {
        List<Integer> subArray = new ArrayList<>();
        int sum = 0, mod = (int) 1e9 + 7;;

        for(int i=0;i<n;i++){
            int currSum=0;
            for(int j=i;j<n;j++){
                currSum+=nums[j];
                subArray.add(currSum);
            }
        }
        Collections.sort(subArray);

        for(int i=left-1; i<=right-1;i++){
            sum=(sum+subArray.get(i)) % mod;
        }
        return sum;
    }
}
