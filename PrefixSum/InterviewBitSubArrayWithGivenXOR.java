package PrefixSum;

import java.util.*;
public class InterviewBitSubArrayWithGivenXOR {
    /**
     * Use prefix SUM (XOR) approach. Store xor of every element of an array in the hashMao with it's count. In every occurence if currentXor ^ B exists in the hashMap,
     * if yes get it's frequency and increment the counter by it's value. and put the currentXor frequency in the hashMap
     * TC: O(n)
     * SC: O(n)
     * */
    public int solve(int[] A, int B) {
        int count = 0;
        Map<Integer, Integer> subArrayXorToCount = new HashMap<>();
        subArrayXorToCount.put(0, 1);
        int currXOR = 0;
        for(int ele: A){
            currXOR  = currXOR ^ ele;
            int requiredXOR = B ^ currXOR;
            if(subArrayXorToCount.containsKey(requiredXOR)){
                count+=subArrayXorToCount.get(requiredXOR);
            }

            if (subArrayXorToCount.containsKey(currXOR)) {
                subArrayXorToCount.put(currXOR, subArrayXorToCount.get(currXOR) + 1);
            } else {
                subArrayXorToCount.put(currXOR, 1);
            }
        }
        return count;
    }
}
