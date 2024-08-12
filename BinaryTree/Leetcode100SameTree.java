package BinaryTree;

import utils.*;
public class Leetcode100SameTree {


    /**
     *
     * DFS: Perform in place check for both of the TreeNode left and right recursively.
     * */

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q == null){
            return true;
        }
        if(p==null || q == null){
            return false;
        }
        return dfs(p, q);
    }

    private boolean dfs(TreeNode nodeA,  TreeNode nodeB){
        if(nodeA == null && nodeB == null){
            return true;
        }
        if(nodeA==null || nodeB == null || nodeA.val!=nodeB.val){
            return false;
        }

        return dfs(nodeA.left, nodeB.left) && dfs(nodeA.right, nodeB.right);
    }
}
