package PrefixSum;

import java.util.*;
public class GeeksForGeeksLargestSubArrayWith0Sum {
    int maxLen(int arr[], int n)
    {
        // Use prefix sum approach here

        Map<Integer, Integer> preSumToIdx = new HashMap<>();

        int result = 0;

        int currSum = 0;
        for(int i=0; i<n; i++){
            currSum+=arr[i];
            if(currSum == 0){
                result = i+1;
            } else {
                if(preSumToIdx.keySet().contains(currSum)){
                    result = Math.max(result, i - preSumToIdx.get(currSum));
                } else {
                    preSumToIdx.put(currSum, i);
                }
            }
        }
        return result;
    }
}
