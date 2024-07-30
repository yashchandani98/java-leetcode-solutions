package monotonicStack;
import java.util.*;
public class InterviewBitNearestSmallerElement {
    /*
   Use Monotonic decreasing stack from array top and if condition is fulfilled, the top element is
   the nearest smallest element of the current element
   */
    public int[] prevSmaller(int[] A) {
        int[] result = new int[A.length];
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<A.length;i++){
            while(st.size()>0 && st.peek()>=A[i]){
                st.pop();
            }
            if(st.size() == 0){
                result[i] = -1;
                st.add(A[i]);
            } else {
                result[i] = st.peek();
                st.add(A[i]);
            }
        }
        return result;
    }
}
