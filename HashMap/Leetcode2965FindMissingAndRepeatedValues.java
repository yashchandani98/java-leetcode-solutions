package HashMap;

import java.util.*;
public class Leetcode2965FindMissingAndRepeatedValues {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        Map<Integer, Integer> count = new HashMap<>();
        int[] res = new int[2];

        for(int i=0; i<grid.length*grid.length; i++){
            count.put(i+1, 0);
        }

        for(int[] item: grid){
            for(int el: item){
                count.put(el, count.getOrDefault(el, 0) + 1);
            }
        }

        for(Map.Entry<Integer, Integer> item: count.entrySet()){
            if(item.getValue() == 0){
                res[1] = item.getKey();
            } else if(item.getValue() > 1){
                res[0] = item.getKey();
            }
        }
        return res;
    }
}
