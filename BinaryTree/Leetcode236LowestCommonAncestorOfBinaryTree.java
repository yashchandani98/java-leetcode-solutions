package BinaryTree;

import utils.*;

public class Leetcode236LowestCommonAncestorOfBinaryTree {


    TreeNode res;

    /**
     * We will perform DFS in the binary tree. We will see if the current node is either p or q, if that's the case, it can be our LCA, hence assign that node to the global result variable.
     *
     * we will check if the recursive call towards left and right both return true i.e. p and q are present in left and right subtree, that means current node is the LCA.
     * Then we will return if either left || right || currentNode is an LCA.
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    private boolean dfs(TreeNode node, TreeNode p, TreeNode q){
        if(node==null){
            return false;
        }

        boolean left = dfs(node.left, p, q);
        boolean right = dfs(node.right, p, q);

        if(node == p || node == q){
            res = node;
        }

        if(left && right){
            res = node;
        }

        return left || right || res == node;

    }
}
