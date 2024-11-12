package HashMap;

import java.util.*;

public class Leetcode1TwoSum {
    /**
     * Use HashMap to store element and their indexes, iterate over an array if remaining = target - num(current element) found in keySet of hashMap,
     * return it's index else put the array element along with it's index.
     * TC: O(n)
     * SC: O(n)
     *
     * */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        int idx = 0;

        for(int num:nums){
            int remaining = target-num;
            if(hashMap.keySet().contains(remaining)){
                return new int[]{idx, hashMap.get(remaining)};
            } else {
                hashMap.put(num, idx);
            }
            idx++;
        }
        return new int[]{};
    }
}
