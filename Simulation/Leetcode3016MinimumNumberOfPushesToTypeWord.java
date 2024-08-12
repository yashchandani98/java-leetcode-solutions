package Simulation;

import java.util.*;
public class Leetcode3016MinimumNumberOfPushesToTypeWord {
    private static class Pair{
        private final char ch;
        private int count;
        public Pair(char ch){
            this.ch = ch;
            this.count = 1;
        }
        public void increment() {
            this.count++;
        }
    }

    /**
     * Use maxheap to sort char freq according to their frequencies and allocate the chars to nuumber 2-9 in the incremental positions accordingly.
     * */
    public int minimumPushes(String word) {
        if(word.length()<=8){
            return word.length();
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((one, two) -> {
            // Compare counts in descending order
            return Integer.compare(two.count, one.count);
        });
        Map<Character, Pair> hashMap = new HashMap<>();

        for(char ch: word.toCharArray()){
            if(hashMap.containsKey(ch)){
                Pair pr = hashMap.get(ch);
                pr.increment();
            } else {
                Pair pr = new Pair(ch);
                hashMap.put(ch, pr);
                // pq.offer(pr);
            }
        }

        for(Pair item: hashMap.values()){
            pq.offer(item);
        }

        // Allocation according to the string char frequency starts here

        int[] dialedNumbersCurrentCapacity = new int[10]; // 0 and 1  index values will be 0 and will never be modified

        Map<Character, int[]> charToNumberWithPos = new HashMap<>();

        int currNumber = 2;
        while(!pq.isEmpty()){
            Pair item = pq.poll();
            if(currNumber==10){
                currNumber = 2;
            }
            charToNumberWithPos.put(item.ch, new int[]{currNumber,++dialedNumbersCurrentCapacity[currNumber]});
            currNumber++;
        }

        int res = 0;
        for(char ch: word.toCharArray()){
            res+=charToNumberWithPos.get(ch)[1];
        }
        return res;
    }
}
