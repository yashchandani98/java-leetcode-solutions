package BinaryTree;


import utils.*;
public class Leetcode226InvertBinaryTree {

    /**
     *
     * DFS: Recursively exchange left node to right node and right node to left node and return the current node
     *
     * */
    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }
    private TreeNode dfs(TreeNode node) {
        if(node == null){
            return null;
        }

        TreeNode leftNode = dfs(node.left);
        TreeNode rightNode = dfs(node.right);
        node.left = rightNode;
        node.right = leftNode;

        return node;

    }
}
