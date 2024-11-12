package heap;

import java.util.*;

public class Leetcode347TopKMostFrequentElements {
    /**
     * Brute force approach:
     *  use HashMap to store occurences of all the elements.
     * - Iterate over the hashmap and  use MaxHeap to store element and it's frequency in class data structure and use comparator on the hashmap value.
     * - Fetch top k most frequent elements from the maxHeap
     * TC: O(nlogn),
     * SC: O(n)
     *
     * Bucket Sort approach:
     * - use hashmap to store every number's count
     * - Then create an array of size equivalent to the length of the original array in which index states count and value will be an arrayList of numbers
     *  whose count is similar to index, Iterate from the right of the new array and fetch top k elements.
     *
     * TC: O(n)
     * SC: O(n)
     *
     */
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

    public int[] topKFrequentMinHeap(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> elemToCount = new HashMap<>();
        List<Integer>[] buckets = new ArrayList[nums.length+1];

        for(int num: nums){
            elemToCount.put(num, elemToCount.getOrDefault(num, 0)+1);
        }

        for(int i = 0; i<buckets.length; i++){
            buckets[i] = new ArrayList<>();
        }

        for(Map.Entry<Integer, Integer> entry: elemToCount.entrySet()){
            buckets[entry.getValue()].add(entry.getKey());
        }

        // i>0 because we are not storing any elemet at index = 0
        int idx = 0;
        for(int i=buckets.length-1; i>0; i--){
            List<Integer> bucket = buckets[i];

            for(int num: bucket){
                if(k ==0) break;
                res[idx++] = num;
                k--;
            }
            if(k ==0) break;
        }

        return res;
    }



    public int[] topKFrequentBucketSort(int[] nums, int k) {
        /***

         Use bucket sort
         */


        Map<Integer, Integer> numToCount = new HashMap<>();
        int[] result = new int[k];

        for(int num: nums){
            numToCount.put(num, numToCount.getOrDefault(num, 0)+1);
        }


        ArrayList<Integer>[] countToValues = new ArrayList[nums.length+1];

        for (int i = 0; i < countToValues.length; i++) {
            countToValues[i] = new ArrayList<>();
        }


        for(Map.Entry<Integer, Integer> entry: numToCount.entrySet()){
            countToValues[entry.getValue()].add(entry.getKey());
        }

        int idx = nums.length;


        System.out.println(Arrays.toString(countToValues));

        while(k>0){
            List<Integer> values = countToValues[idx--];
            if(values == null) continue;
            for(int value: values){
                result[k-1] = value;
                k--;
                if(k==0) break;
            }
        }
        return result;
    }
}
