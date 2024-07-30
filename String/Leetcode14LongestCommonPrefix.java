package String;

import java.util.*;

public class Leetcode14LongestCommonPrefix {
    /**
     * Sorting is needed.
     * TC: O(nlogn) + min_length(strings present in strs), n: len(strs)
     *
     * We will sort the string array so that if common prefix is present, all the strings starting with the same prefix will be bounded in an array and if not
     * the first and last string prefix will be different and for loop will be ended and returns empty string as a common prefix
     *
     * */
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        StringBuilder res = new StringBuilder();
        String firstStr = strs[0];
        String lastStr = strs[strs.length-1];

        for(int i=0; i<Math.min(firstStr.length(), lastStr.length()); i++){
            if(firstStr.charAt(i) == lastStr.charAt(i)){
                res.append(firstStr.charAt(i));
                continue;
            }
            return res.toString();
        }

        return res.toString();

    }
}
