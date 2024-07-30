package Stack;

import java.util.*;
import java.util.stream.*;
public class Leetcode1717MaximumScoreFromRemovingSubstrings {
    public int maximumGain(String s, int x, int y) {
        // Define stack to add char in the stack if the pattern doesnot exist. we will process it later once iteration over string is completed
        int result = 0;
        Stack<Character> st = new Stack<>();
        for(Character c: s.toCharArray()){
            if(!st.isEmpty()){
                if(x>=y){
                    // check for "ab" pattern i.e. stack top is a and current element is b
                    if(st.peek() == 'a' && c == 'b'){
                        result+=x;
                        st.pop();
                    } else{
                        st.add(c);
                    }
                } else{
                    // check for "ba" pattern i.e. stack top is b and current element is a
                    if(st.peek() == 'b' && c == 'a'){
                        result+=y;
                        st.pop();
                    } else{
                        st.add(c);
                    }
                }
            } else{
                st.add(c);
            }
        }

        System.out.println(result);
        System.out.println(st);

        s = st.stream().map(Object::toString).collect(Collectors.joining(""));
        st = new Stack<>();
        for(Character c: s.toCharArray()){
            if(!st.isEmpty()){
                if(x<y){
                    // check for "ab" pattern i.e. stack top is a and current element is b
                    if(st.peek() == 'a' && c == 'b'){
                        result+=x;
                        st.pop();
                    } else{
                        st.add(c);
                    }
                } else{
                    // check for "ba" pattern i.e. stack top is b and current element is a
                    if(st.peek() == 'b' && c == 'a'){
                        result+=y;
                        st.pop();
                    } else{
                        st.add(c);
                    }
                }
            } else{
                st.add(c);
            }
        }
        return result;
    }
}
