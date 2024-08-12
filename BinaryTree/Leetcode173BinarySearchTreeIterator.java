package BinaryTree;


import utils.*;
import java.util.*;
public class Leetcode173BinarySearchTreeIterator {
    private Stack<TreeNode> st;


    /**
     * Use Stack to store nodes during left traversal during initialization.
     *
     * During next() method, pop the topmost node in the stack and add it's first right  node  and add the right node's all the left nodes
     * in the stack and return the node's value.
     *
     * */

    public Leetcode173BinarySearchTreeIterator(TreeNode root) {
        st = new Stack<>();

        st.push(root);
        while(st.peek().left!=null){
            st.push(st.peek().left);
        }
    }

    public int next() {
        TreeNode node = st.pop();

        if(node.right!=null){
            st.push(node.right);

            while(st.peek().left!=null){
                st.push(st.peek().left);
            }
        }

        return node.val;
    }

    public boolean hasNext() {
        return st.size()>0;
    }
}
