package Recursion;

import java.util.*;
public class Leetcode140WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        List<String> currWord = new ArrayList<>();
        backTrack(0, currWord, result, words, s);
        return result;
    }

    private void backTrack(int idx, List<String> currWord ,List<String> result, Set<String> wordDict, String s){
        if(idx == s.length()){
            System.out.println(idx);
            String str = new String(String.join(" ", currWord));
            result.add(str);
            return;
        }

        for(int j = idx; j<s.length(); j++){
            if(wordDict.contains(s.substring(idx, j+1))){
                currWord.add(s.substring(idx, j+1));
                backTrack(j+1, currWord, result, wordDict, s);
                currWord.remove(currWord.size()-1);
            }
        }
    }
}
