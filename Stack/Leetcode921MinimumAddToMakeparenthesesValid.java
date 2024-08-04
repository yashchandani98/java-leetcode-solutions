package Stack;

import java.util.Stack;
public class Leetcode921MinimumAddToMakeparenthesesValid {
    /**
     * Use Stack to push character into the stack if it's an open braces, pop from the stack if encountered closed brace and stack peek is open braces
     * else increment the count.
     * */
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        int count = 0;
        char open = '(';
        char closed = ')';
        for(Character ch: s.toCharArray()){
            if(ch == open){
                st.push(ch);
            }
            else {
                if(st.size()>0 && st.peek() == open){
                    st.pop();
                } else {
                    count++;
                }
            }
        }
        count+=st.size();
        return count;
    }
}
