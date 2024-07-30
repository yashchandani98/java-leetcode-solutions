package PrefixSum;

import java.util.*;


/*
*
* Use Prefix Sum technique to solve this question:
*
* In each iteration we will keep on calculating prefix sum for every index and will check if prefixSum - target is present in hashMap,
* if yes we will increase count by the frequency of that prefix sum form the hashMap and count will be our answer.
*
* Intuition behind this approach is:
* sum(i,j) (target) = sum(0,j)(prefixSum) - sum(0,i) (temp)
*
* */
public class Leetcode560SubArraySumEqualsToK {
    public int subarraySum(int[] nums, int target) {
        int count = 0;
        Map<Integer, Integer> sumFrequencies = new HashMap<>();
        int prefixSum = 0;
        sumFrequencies.put(prefixSum,1);
        for(int i=0; i< nums.length;i++){
            prefixSum+=nums[i];
            int temp = prefixSum-target;
            if(sumFrequencies.containsKey(temp)){
                count+=sumFrequencies.get(temp);
            }
            sumFrequencies.put(prefixSum, sumFrequencies.getOrDefault(prefixSum, 0)+1);
        }
        return count;
    }
}
