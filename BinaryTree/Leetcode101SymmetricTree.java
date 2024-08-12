package BinaryTree;

import utils.*;
public class Leetcode101SymmetricTree {

    /**
     *
     * DFS: We will split the left sub-tree and right subtree in the first recursive call and inline check recursively symmetric property for both of the sub-tree.
     * */
    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode nodeLeft, TreeNode nodeRight){
        if(nodeLeft == null && nodeRight == null){
            return true;
        }

        if(nodeLeft == null || nodeRight == null || nodeLeft.val!=nodeRight.val){
            return false;
        }

        return dfs(nodeLeft.left, nodeRight.right) && dfs(nodeLeft.right, nodeRight.left);
    }
}
