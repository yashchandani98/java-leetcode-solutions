package SlidingWindowAndTwoPointer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Leetcode76MinimumWindowSubString {

    private static int conditionsFulfilled = 0;
    private static int conditionsFulfilmentNeeded = 0;
    private static Map<Character, Integer> currWindowFreq = new HashMap<>();
    private static Map<Character, Integer> givenStringFreq = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(Objects.equals("YASH", "YASH"));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
    public static String minWindow(String s, String t) {
        if(t == "") {
            return "";
        }
        int lptr = 0, rptr = 0, min_length = Integer.MAX_VALUE;
        String res = "";
        for(char c : t.toCharArray()){
            givenStringFreq.put(c, givenStringFreq.getOrDefault(c, 0) + 1);
        }
        conditionsFulfilmentNeeded = givenStringFreq.size();
        while(rptr<s.length()){
            char c = s.charAt(rptr);
            currWindowFreq.put(c, currWindowFreq.getOrDefault(c, 0) + 1);
            if(givenStringFreq.keySet().contains(c)) {
                if(currWindowFreq.get(c).equals(givenStringFreq.get(c))){
                    conditionsFulfilled++;
                }
            }
            while(conditionsFulfilled == conditionsFulfilmentNeeded)  {
                if(min_length > (rptr - lptr + 1)) {
                    min_length = (rptr - lptr + 1);
                    res = s.substring(lptr, rptr+1);
                }
                currWindowFreq.put(s.charAt(lptr), currWindowFreq.get(s.charAt(lptr)) -1 );
                if(givenStringFreq.keySet().contains(s.charAt(lptr))) {
                    if(currWindowFreq.get(s.charAt(lptr)) < givenStringFreq.get(s.charAt(lptr))){
                        conditionsFulfilled--;
                    }
                }
                lptr++;
            }
            rptr++;
        }
        return res;
    }
}
