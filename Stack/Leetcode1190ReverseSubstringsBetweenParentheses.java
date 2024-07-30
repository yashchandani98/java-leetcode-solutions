package Stack;

import java.util.*;
public class Leetcode1190ReverseSubstringsBetweenParentheses {

    public String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();
        int subStringSize = 0;
        int totalReversalLength = 0;
        int idx = 0;
        for(Character c: s.toCharArray()){
            if(c == ')'){
                List<Character> strst = new ArrayList<Character>();
                while(st.size()>0 && st.peek()!='('){
                    strst.add(st.pop());
                }
                if(!st.isEmpty() && st.peek() == '(')
                    st.pop();
                st.addAll(strst);
            } else{
                st.push(c);
            }
            idx++;
        }
        return st.toString().replace("[","").replace("]","").replace(",","").replaceAll("\\s+","");
    }
}
