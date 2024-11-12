package Stack;

import java.util.*;


public class Leetcode1249MinimumMoveToMakeValidParentheses {
    /**
     * Intuition behind this problem is to have the string which has both open braces and closed braces equally but occurrence should be sequentially first open then closed.
     * If encountered closed brace whereas open braces is not present in the stack, add the current index to the hashSet which stores the indexes of paranthesis and
     * in the end we will monitor stack if it has any pairs left, if yes add all the indexes of the pairs to hashSet basically they are unbalanced open paranthesis.
     * In the end iterate through original string and remove character stored at index which need to be removec.
     *
     * TC: O(n) where n= string length
     * SC: O(n)
     * Data Structures used: Stack, HashSet, StringBuilder
     * */
    static class Pair {
        int idx;
        char ch;

        public Pair(int idx, char ch){
            this.idx = idx;
            this.ch = ch;
        }
    }
    public String minRemoveToMakeValid(String s) {
        StringBuilder result = new StringBuilder();
        char OPEN_BRACE = '(';
        char CLOSE_BRACE = ')';
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Pair> st = new Stack<>();

        int idx = 0;
        for(char ch: s.toCharArray()){
            if(ch == OPEN_BRACE){
                st.add(new Pair(idx, ch));
            } else if(ch == CLOSE_BRACE){
                if(!st.isEmpty()){
                    st.pop();
                } else {
                    indexesToRemove.add(idx);
                }
            }
            idx++;
        }

        while(!st.isEmpty()){
            Pair pr = st.pop();
            indexesToRemove.add(pr.idx);
        }

        for(int i=0; i<s.length(); i++){
            if(indexesToRemove.contains(i)) continue;
            result.append(s.charAt(i));
        }

        return result.toString();
    }
}
