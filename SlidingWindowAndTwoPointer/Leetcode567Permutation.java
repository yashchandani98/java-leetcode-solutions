package SlidingWindowAndTwoPointer;

import java.util.Map;
import java.util.HashMap;

/*
* The intuition behind this approach is ti use HashMap to store freq of every character of the current window. We have to check if current window
* char frequency is matching to the given string char frequency. This is a Fixed size sliding window question where left_ptr will be initialized
* to 0 and right_ptr to given string length -1;
* */
public class Leetcode567Permutation {
    public static void main(String[] args) {
        System.out.println(checkInclusion("agvb", "suywgvyuwvgvabgfeuyvw"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if(s2.length()<s1.length()) {
            return false;
        }
        boolean result = false;
        char [] string2Arr = s2.toCharArray();
        char [] string1Arr = s1.toCharArray();
        Map<String, Integer> currWindowCharFreq = constructHashMapWithCharsFreq(string2Arr, 0, s1.length() - 1);
        Map<String, Integer> string1CharFreq = constructHashMapWithCharsFreq(string1Arr, 0, s1.length() - 1);
        int left_ptr = 0, right_ptr = s1.length()-1;
        while(right_ptr<s2.length()) {
            if(currWindowCharFreq.equals(string1CharFreq)) {
                result = true;
                break;
            }else{
                right_ptr++;
                if(right_ptr < s2.length()) {
                    currWindowCharFreq.put(String.valueOf(s2.charAt(right_ptr)), currWindowCharFreq.getOrDefault(String.valueOf(s2.charAt(right_ptr)), 0) + 1);
                    if(currWindowCharFreq.get(String.valueOf(s2.charAt(left_ptr))) == 1) {
                        currWindowCharFreq.remove(String.valueOf(s2.charAt(left_ptr)));
                    } else{
                        currWindowCharFreq.put(String.valueOf(s2.charAt(left_ptr)), currWindowCharFreq.get(String.valueOf(s2.charAt(left_ptr))) - 1);
                    }
                }
                left_ptr++;
            }
        }
        return result;

    }

    private static Map<String, Integer> constructHashMapWithCharsFreq(char[] stringArr, int leftptr, int rightptr) {
        Map<String, Integer> charFreq = new HashMap<>();
        while(leftptr<=rightptr){
            String charConvertedStr = String.valueOf(stringArr[leftptr]);
            if(charFreq.keySet().contains(charConvertedStr)) {
                charFreq.put(charConvertedStr, charFreq.get(charConvertedStr)+1);
            } else{
                charFreq.put(charConvertedStr, 1);
            }
            leftptr++;
        }
        return charFreq;
    }
}
