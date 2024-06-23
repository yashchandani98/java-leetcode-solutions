package SlidingWindowAndTwoPointer;

import java.util.HashSet;
import java.util.Set;

/*
* We will use here two pointer approach lptr and rptr. We will keep on shifting lptr as soon as we get to know
* if duplicate character is present in the hashset and also we will remove lptr char from the hashset.
* We will use Set as a data structure all the elements
* */
public class Leetcode3LongestSubStringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));
    }
    public static int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) {
            return 0;
        }
        if(s.length() == 1) {
            return 1;
        }
        int maxSubStringSize = 0;
        int lptr = 0, rptr;
        char[] sChars = s.toCharArray();
        Set<String> subStringCharacters = new HashSet<>();
        for (rptr=0;rptr<s.length();rptr++){
            while (subStringCharacters.contains(String.valueOf(sChars[rptr]))) {
                System.out.println("set:"+subStringCharacters);
                subStringCharacters.remove(String.valueOf(sChars[lptr]));
                lptr++;
            }
            subStringCharacters.add(String.valueOf(sChars[rptr]));
            maxSubStringSize = Math.max(maxSubStringSize, rptr - lptr + 1);
        }
        return maxSubStringSize;
    }
}
