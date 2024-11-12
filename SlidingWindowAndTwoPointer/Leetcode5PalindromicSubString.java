package SlidingWindowAndTwoPointer;

public class Leetcode5PalindromicSubString {
    /**
     *
     * Brute Force approach: **The brute force approach is to use Two pointer approach and we will try for the biggest length of the string and gradually move towards the lowest strign length to find longest palindromic string.
     * TC: O((n^3)
     * Better Approach: Expand from centres**
     *
     * For every index in String, traverse towards left and right using two pointers as much as you can to find the longest palindromic string. Do it separately for even and dd string.
     *
     * TC: O(n^2)
     * */

    public String longestPalindromeApproach1(String s) {
        /*
        * Brute force approach
        * */
        if(s.length()==1) return s;
        int[] indices = {0,0};

        for(int i=s.length(); i>0;i--){
            for(int j=0; j<s.length()-i; j++){
                if(checkifPalindrome(j, j+i, s)){
                    indices = new int[]{j, j+i};
                    return s.substring(indices[0], indices[1]+1);
                }
            }
        }

        return s.substring(indices[0], indices[1]+1);
    }

    private boolean checkifPalindrome(int start, int end, String s){
        // int len = end-start+1;
        while(start<=end){
            if(s.charAt(start) == s.charAt(end)){
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
    public String longestPalindrome(String s) {
        /**
         Expand from centers approach
         */

        int[] indicesWithLen = {0,0,0};
        for(int i=0;i<s.length();i++){
            // For handling odd length palindrome
            int left_ptr = i, right_ptr = i;
            while(left_ptr>=0 && right_ptr<s.length() && s.charAt(left_ptr) == s.charAt(right_ptr)){
                int currlen = right_ptr-left_ptr+1;
                if(currlen>indicesWithLen[2]){
                    indicesWithLen = new int[]{left_ptr, right_ptr, currlen};
                }
                left_ptr-=1;
                right_ptr+=1;
            }
            // For handling even length palindrome
            left_ptr = i;
            right_ptr = i+1;
            while(left_ptr>=0 && right_ptr<s.length() && s.charAt(left_ptr) == s.charAt(right_ptr)){
                int currlen = right_ptr-left_ptr+1;
                if(currlen>indicesWithLen[2]){
                    indicesWithLen = new int[]{left_ptr, right_ptr, currlen};
                }
                left_ptr-=1;
                right_ptr+=1;
            }
        }
        return s.substring(indicesWithLen[0], indicesWithLen[1]+1);
    }
}
