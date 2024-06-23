package SlidingWindowAndTwoPointer;

import java.util.HashMap;
import java.util.Map;

/*
* The intuition behind this problem is to use Sliding window variable sized algorithm (Two pointer approach). We will use HashMap to store
* Occurrence of every character in the current window and we will use maxFrequency number to calculate if k can be used to make it a perfect substring(current window)
* Formula =  currentwindowsize-maxFrequency <= 2. If yes, calculate current window size else shift left ptr towards right and remove leftptr freq occurrence from hashMap
* */
public class Leetcode424LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("ABABBA", 1));
    }
    public static int characterReplacement(String s, int k) {
        int lptr = 0, rptr = 0, res = 0;
        HashMap<String, Integer> occurenceMap = new HashMap<>();
        char[] charConvertedString = s.toCharArray();

        for(;rptr<s.length();rptr++){
            String currentCharToString = String.valueOf(charConvertedString[rptr]);
            if(occurenceMap.keySet().contains(currentCharToString)) {
                occurenceMap.put(currentCharToString, occurenceMap.get(currentCharToString)+1);
            } else{
                occurenceMap.put(currentCharToString, 1);
            }
            int mostFrequentCharOccurence = getMostFrequentCharacterOccurence(occurenceMap);
            if((rptr-lptr+1)-mostFrequentCharOccurence<=k){
                res = Math.max(res, (rptr-lptr+1));
            } else{
                occurenceMap.put(String.valueOf(charConvertedString[lptr]), occurenceMap.get(String.valueOf(charConvertedString[lptr]))-1);
                lptr++;
            }
        }
        return res;
    }

    private static int getMostFrequentCharacterOccurence(HashMap<String, Integer> charOccurence) {
        int maxFreq = 0;
        for(Map.Entry<String, Integer> occurence : charOccurence.entrySet()){
            if(maxFreq <= occurence.getValue()){
                maxFreq = occurence.getValue();
            }
        }
        return maxFreq;
    }
}
