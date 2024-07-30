package Stack;

import java.util.*;

public class Leetcode155MinStack {
    /**
     * Approach/Intuition: Here we will use Pair class to store the current value and minimum value until now in the stack. Every time the push operation is performed, we will compare it's
     * value to the stack's top min_element. If it's smaller then current push min_element will be previous min element.
     * TC: O(1)
     * SC: O(2n)
     * */
    private static class Pair<K,V>{
        private final K value;
        private final V min_element;
        public Pair(K value, V min_element){
            this.min_element = min_element;
            this.value = value;
        }
    }

    private final Stack<Pair<Integer, Integer>> st;

    public Leetcode155MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        Pair<Integer, Integer> pr;
        if(st.size()>0 && st.peek().min_element < val){
            pr = new Pair(val, st.peek().min_element);
        } else {
            pr = new Pair(val, val);
        }
        st.push(pr);
    }

    public void pop() {
        if(st.size()>0)
            st.pop();
    }

    public int top() {
        return st.size()>0 ? st.peek().value : -1;
    }

    public int getMin() {
        return st.size()>0 ? st.peek().min_element : -1;
    }
}
