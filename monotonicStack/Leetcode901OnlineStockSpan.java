package monotonicStack;

import java.util.*;
public class Leetcode901OnlineStockSpan {
    /**
     * Create Monotonic increasing Stack of integer array [] which stores price and span property for every price.
     * The monotonic property will use price as the key factor. We will keep on adding span as long as all the lesser prices are eliminated.
     * */

    private final Stack<int[]> st;

    public Leetcode901OnlineStockSpan() {
        st = new Stack<>();
    }

    public int next(int price) {
        int span =1;
        while(st.size()>0 && st.peek()[0]<=price){
            span+=st.pop()[1];
        }
        st.push(new int[]{price, span});
        return span;
    }
}
