package HashMap;

import java.util.*;
public class Leetcode3043FindLengthOFLongestCommonPrefix {

    /**
     * We will use hashSet to generate every possinle prefix of every integer in arr2. We will then iterate through arr1 and check if any of it's prefix is present in the hashset, them
     * calculate it's length by int len = (int)Math.log10(num) + 1
     * TC: O(n)
     * SC: O(n)
     * */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixesArr2Set = new HashSet<>();
        int result = 0;
        for(int num: arr2) {
            while(num>0) {
                prefixesArr2Set.add(num);
                num = num / 10;
            }
        }

        for(int num: arr1) {
            while(num>0){
                if(prefixesArr2Set.contains(num)){
                    int count = (int) Math.log10(num) + 1;
                    result = Math.max(result, count);
                    break;
                }
                num = num / 10;
            }
        }

        return result;
    }
}
