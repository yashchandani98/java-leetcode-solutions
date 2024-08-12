package BinaryTree;

import utils.*;

public class Leetcode543DiameterOfBinaryTree {
    int res;
    /**
    * Perform DFS: At every level, we have to  update result. Recursively call left subtree and right subtree. return 1 + max(leftNodes, rightNodes)
    *
    * */
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node){
        if(node == null){
            return 0;
        }

        int leftNodes = dfs(node.left);
        int rightNodes = dfs(node.right);

        res = Math.max(res, leftNodes+ rightNodes);

        return 1+Math.max(leftNodes, rightNodes);
    }
}
