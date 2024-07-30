package monotonicStack;

import java.util.*;
public class Leetcode84LargestRectangleInHistogram {
    /**
     * Here we will use Monotonic stack to determine nextSmallest height and previousSmallest height index for the current height.
     * TC: O(N) for left smallest + O(N) for next largest and O(N) for stack
     * */
    public int largestRectangleArea(int[] heights) {
        int[] leftSmallestElementIndex = new int[heights.length];
        int[] rightSmallestElementIndex = new int[heights.length];

        Stack<Integer> st = new Stack<Integer>();

        for(int i=0;i<heights.length;i++){
            while(st.size()>0 && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(st.size() == 0){
                leftSmallestElementIndex[i] = 0;
            }
            else {
                leftSmallestElementIndex[i] = st.peek()+1;
            }
            st.add(i);
        }
        st = new Stack<>();
        for(int i=heights.length-1;i>=0;i--){
            while(st.size()>0 && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(st.size() == 0){
                rightSmallestElementIndex[i] = heights.length-1;
            }
            else {
                rightSmallestElementIndex[i] = st.peek()-1;
            }
            st.add(i);
        }

        int res = 0;

        for(int i=0; i<heights.length;i++){
            res = Math.max( res, heights[i] *  (rightSmallestElementIndex[i] - leftSmallestElementIndex[i]+1));
        }
        return res;
    }
}
