package MaxHeap;

import java.util.*;

/*
 * - use HashMap to store occurences of all the elements.
 * - Iterate over the hashmap and  use MaxHeap to store element and it's frequency in class data structure and use comparator on the hashmap value.
 * - Fetch top k most frequent elements from the maxHeap
 */
public class Leetcode347TopKMostFrequentElements {
    private static class Frequency {
        int ele;
        int freq;
        public Frequency(int ele, int freq){
            this.ele = ele;
            this.freq = freq;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> hashMap = new HashMap<>();
        // Store occurences
        for(int num:nums){
            hashMap.put(num, hashMap.getOrDefault(num, 0)+1);
        }

        // Sort according to the value of hashMap if values are equal, store according to the lexicographically
        PriorityQueue<Frequency> values = new PriorityQueue<>((a,b)->{
            if(a.freq == b.freq)
                return a.ele-b.ele;
            return b.freq-a.freq;
        });


        // Iterate the hashMap and add the entry and value to the maxHeap
        for(Map.Entry<Integer, Integer> entry: hashMap.entrySet()){
            values.offer(new Frequency(entry.getKey(), entry.getValue()));
        }

        // Fetch top k most frequent elements

        while(k>0){
            res.add(values.poll().ele);
            k--;
        }


        // Convert List to int[] array
        return res.stream().mapToInt(Integer::new).toArray();
    }
}
