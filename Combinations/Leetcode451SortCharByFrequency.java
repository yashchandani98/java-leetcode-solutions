package Combinations;

import java.util.*;
/*
* We will use maxHeap(PriorityQueue) and HashMap to solve this question
* */
public class Leetcode451SortCharByFrequency {
    class CharFrequency {
        char ch;
        int freq;
        public CharFrequency(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }
    }

    public String frequencySort(String s) {
        Map<Character,Integer> charOccurence = new HashMap<>();
        PriorityQueue<CharFrequency> charFrequencies = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for(char ch: s.toCharArray()){
            charOccurence.put(ch, charOccurence.getOrDefault(ch, 0)+1);
        }
        for(Map.Entry<Character,Integer> entrySet: charOccurence.entrySet()){
            charFrequencies.add(new CharFrequency(entrySet.getKey(), entrySet.getValue()));
        }
        StringBuilder res = new StringBuilder();
        while(!charFrequencies.isEmpty()){
            CharFrequency item = charFrequencies.poll();
            int count = item.freq;
            while(count>0){
                res.append(String.valueOf(item.ch));
                count--;
            }
        }
        return res.toString();
    }
}
