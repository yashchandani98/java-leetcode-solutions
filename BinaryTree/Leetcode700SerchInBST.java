package BinaryTree;

import utils.*;

public class Leetcode700SerchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        return dfs(root, val);
    }

    private TreeNode dfs(TreeNode node, int val) {
        if(node == null){
            return node;
        }
        if(node.val == val){
            return node;
        }


        if(val>node.val){
            return dfs(node.right, val);
        } else {
            return dfs(node.left, val);
        }
    }
}
