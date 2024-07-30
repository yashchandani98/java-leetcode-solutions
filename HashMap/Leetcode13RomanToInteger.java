package HashMap;

import java.util.*;
public class Leetcode13RomanToInteger {
    /**
     * Create HashMap and stort every roman character to integer value. Parse string from left to right and in case if every subsequent character value is less, add
     * the value to the result else subtract two character values and add it to the final result.
     * Ex: XIV = 10 + (5-1) = 14
     * */
    public int romanToInt(String s) {
        // Create hashMap -> charToInteger

        Map<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('I', 1);
        hashMap.put('V', 5);
        hashMap.put('X', 10);
        hashMap.put('L', 50);
        hashMap.put('C', 100);
        hashMap.put('D', 500);
        hashMap.put('M', 1000);

        int res = 0;

        for(int i=0; i<s.length();i++){
            char curr = s.charAt(i);
            int value = hashMap.get(curr);
            if(i<s.length()-1 && hashMap.get(curr)<hashMap.get(s.charAt(i+1))){
                value = hashMap.get(s.charAt(i+1)) - hashMap.get(curr);
                i++;
            }
            res+=value;
        }
        return res;
    }
}
