package HashMap;

import java.util.*;
public class Leetcode3005CountElementsWithMaximumFrequency {

    /**
     * 1 pass greedy solution
     * */
    public int maxFrequencyElements(int[] nums) {
        int maxFreq = 0;
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num:nums){
            int currFreq = 1;
            if(freq.containsKey(num)){
                currFreq += freq.get(num);
            }
            freq.put(num, currFreq);
            if(currFreq>maxFreq){
                maxFreq = currFreq;
                res = maxFreq;
            } else if (currFreq == maxFreq){
                res+=maxFreq;
            }
        }

        return res;
    }
}
