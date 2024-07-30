package Recursion;

import java.util.*;

/*
*
* Use recursion in a looping for every subset we will check if the given string is a palindrome,
*  if yes we will call the recursive function for index greater then current one. We will iterate over the string while calling recursive function in every iteration.
* */
public class Leetcode131PalindromePartioning {
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();
            solve(0, s, new ArrayList<String>(), result);
            return  result;
        }

        private void solve(int idx, String s, List<String> currList, List<List<String>> result){
            if(idx >= s.length()){
                result.add(new ArrayList<>(currList));
                return;
            }

            for(int i=idx; i<s.length(); i++){
                if(isPalindrome(idx, i, s)){
                    currList.add(s.substring(idx, i+1));
                    solve(i+1, s, currList, result);
                    currList.remove(currList.size()-1);
                }
            }
        }


        private boolean isPalindrome(int start, int end, String s){
            while(start<end){
                if(s.charAt(start++) != s.charAt(end--)){
                    return false;
                }
            }
            return true;
        }
}
