package Stack;

import java.util.*;

/*
* We will use Stack to store each and every string and we will use that stack to create new string popping from the top of the stack
*
* */
public class Leetcode151ReverseWordsInAString {
    public String reverseWords(String s) {
        String newString = "";
        Stack<String> st = new Stack<>();

        String currString = "";

        for(Character ch: s.toCharArray()){
            if(ch == ' '){
                System.out.println(ch);
                if(currString!=" " && currString!=""){
                    st.push(currString);
                }
                currString = "";
            } else{
                currString+=ch;
            }
        }
        st.push(currString);

        System.out.println(st);

        while(!st.isEmpty()){
            String str = st.pop();
            if(str!=""){
                newString+=str;
                newString+=' ';
            }
        }
        return newString.substring(0, newString.length()-1);
    }
}
