package HashMap;

import java.util.*;

public class Leetcode1636SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int num: nums){
            hashMap.put(num, hashMap.getOrDefault(num, 0)+1);
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(hashMap.entrySet());

        // Step 3: Sort the list based on the values of the entries
        Collections.sort(entryList, (a, b) -> {
            // Compare based on the values (occurrence counts)
            int compare = Integer.compare(a.getValue(), b.getValue()); // Descending order by value

            // If values are equal, compare by keys (ascending order by key)
            if (compare == 0) {
                return Integer.compare(b.getKey(), a.getKey());
            } else {
                return compare;
            }
        });

        int idx = 0;
        for(Map.Entry<Integer, Integer> entry: entryList){
            int freq = entry.getValue();
            while(freq>0){
                nums[idx++] = entry.getKey();
                freq--;
            }
        }

        return nums;

    }
}
