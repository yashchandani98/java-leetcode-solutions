package Combinations;

import java.util.*;

public class Leetcode763partitionLabels {
    public static void main(String[] args){
        Set<Set> hash2 = new HashSet<>();
        Set<Integer> hash = new HashSet<>();
        hash.add(9);
        hash.add(10);
        hash2.add(hash);
        Set<Integer> hash3 = new HashSet<>();
        hash3.add(10);
        hash3.add(9);
        System.out.println(hash2);
        System.out.println(hash3);
        System.out.println(hash2.contains(hash3));
//        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
    public static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<Integer>();
        Map<Character, PriorityQueue<Integer>> charIndices = new HashMap<>();
        int idx = 0;
        for(char ch : s.toCharArray()) {
            if(charIndices.containsKey(ch)) {
                PriorityQueue<Integer> pq = charIndices.get(ch);
                pq.add(idx);
                charIndices.put(ch, pq);
            } else {
                PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                pq.add(idx);
                charIndices.put(ch, pq);
            }
            idx++;
        }

        int lptr = 0, currIndex = 0, pivot = charIndices.get(s.charAt(0)).poll();
        Set<Character> hashSet = new HashSet<>();
        hashSet.add(s.charAt(0));

        while(currIndex<s.length()){
            if(currIndex >= pivot){
                res.add(pivot-lptr+1);
                if(currIndex == s.length() - 1){
                    // Index out of bound phase
                    break;
                } else {
                    // Reset phase
                    if(currIndex == pivot){
                        currIndex++;
                    }
                    char ch =s.charAt(currIndex);
                    lptr = currIndex;
                    pivot = charIndices.get(ch).poll();
                    hashSet.clear();
                    hashSet.add(ch);
                }
            }
            char ch = s.charAt(currIndex);
            if(!hashSet.contains(ch)){
                hashSet.add(ch);
                int maxIndex = charIndices.get(ch).poll();
                if(maxIndex>pivot) {
                    pivot = maxIndex;
                }
            }
            currIndex++;
        }
        if(pivot+1 == currIndex){
            // when we get last character as a separate partition
            res.add(1);
        }
        return res;
    }
}
