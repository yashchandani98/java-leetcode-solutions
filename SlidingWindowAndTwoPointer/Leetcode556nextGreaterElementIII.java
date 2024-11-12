package SlidingWindowAndTwoPointer;

import java.util.Arrays;
public class Leetcode556nextGreaterElementIII {
    /**
     *
     * Brute force approach:
     * We will generate all the permutations of a current number by rearranging all the digits and sort them
     * then we will traverse to all the numbers and return the next greatest number once the given number is found
     * in the list.
     * TC: O(n!*n) => Extra n will be the length of an arraylist when storing this in the final result.
     * SC: O(n!)
     *
     * Optimal Approach:
     * We will use Two pointers approach here. First we will convert an integer to string in order to
     * compare each digit of a number. We will focus on the LSB(Less significant bit) to convert into
     * next greater element. We will start from n-2 and go towards 0 and we will check if we find
     * that the current number is lesser than the next number, then we will go towards right and find
     * the just next greater element than the current one and will swap it with the current number,
     * ONce swpaping is done, then sort the numbers from i+1 to n in ascending order and the
     * obtained digit is our result.
     * */
    public int nextGreaterElement(int givenNumber) {
        StringBuilder str = new StringBuilder(String.valueOf(givenNumber));
        int n = str.length();
        for(int i=n-2; i>=0; i--){
            int currNum = Integer.parseInt(String.valueOf(str.charAt(i)));
            int nextNum = Integer.parseInt(String.valueOf(str.charAt(i+1)));
            if(currNum<nextNum) {
                // pop the next greater element then currNum to the right
                int j = i+2;
                int nextGreaterElement = nextNum;
                int idx = i+1;
                while(j<n){
                    int possibleNum = Integer.parseInt(String.valueOf(str.charAt(j)));
                    if(possibleNum>currNum && possibleNum<nextGreaterElement){
                        nextGreaterElement = possibleNum;
                        idx = j;
                    }
                    j++;
                }
                // SWap
                char temp = str.charAt(idx);
                str.setCharAt(idx, str.charAt(i));
                str.setCharAt(i, temp);

                // sort from i+1 to n-1(inclusive)
                int[] arr = new int[n-i-1];
                int k = i+1;
                int l = 0;
                while(k<n){
                    arr[l++] = Integer.parseInt(String.valueOf(str.charAt(k++)));
                }

                Arrays.sort(arr);
                k = i+1;
                l = 0;
                while(k<n){
                    str.setCharAt(k++, String.valueOf(arr[l++]).charAt(0));
                }
                System.out.println(str);
                long res = Long.parseLong(str.toString());
                return res > Integer.MAX_VALUE ? -1 : (int) res;
            }
        }

        return -1;
    }
}
