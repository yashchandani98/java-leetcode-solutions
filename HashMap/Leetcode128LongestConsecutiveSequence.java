package HashMap;

import java.util.*;
import java.util.stream.Collectors;

/*
*
* Intuition
We will store occurence count of every element in a HashMap. Then over iterating an array we will check if current element is
* the starting element of possible subsequence, if yes we will check if the possible consecutive integer
* is present in the HashMap by searching in the hashmap which will take O(1) as the time complexity and we will update
* the count with the result if currenctCount is greater than previous Count.
*
* More possible approaches:
     * We could have also used hashSet instead of hashMap
     * We could have also sorted the array and  iterate over an array to find sequence. TC:O(nlogn) + O(n), SC: O(1)
* */
public class Leetcode128LongestConsecutiveSequence {
    // TC: O(n), SC: O(n)
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        int count=0;
        int res = 0;
        // Store frequency of every element in hashMap
        for(int num: nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for(int num: nums){
            // Check if current number is starting value of the possible ConsecutiveSequence present in an array
            if(!freq.containsKey(num-1)){
                count = 1;
                int nextConsecutiveInteger = num+1;
                // Check for nextConsecutiveInteger present in an array
                while(freq.containsKey(nextConsecutiveInteger)){
                    count++;
                    nextConsecutiveInteger++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public int longestConsecutiveHashSetApproach(int[] nums) {
        Set<Integer> hashSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int result = 0;
        for(int num: nums){
            if(!hashSet.contains(num-1)){
                int count = 1;
                int temp = num;
                while(hashSet.contains(temp+1)){
                    count++;
                    temp++;
                }
                result = Math.max(result, count);
            }
        }

        return result;
    }
}
