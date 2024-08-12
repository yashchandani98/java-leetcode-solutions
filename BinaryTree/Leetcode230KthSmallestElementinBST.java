package BinaryTree;


import utils.*;
public class Leetcode230KthSmallestElementinBST {

    /**
     * Treverse inorder in BST and keep track of sequence, If sequence == key, update the global variable res and return it,
     * else return the tracked variable sequence. Increment the sequence variable after visiting the left subtree and update
     * it from the left sub tree and pass the same variable to right subtree and return it from there as well.
     *
     */
    private int res = 0 ;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k, 0);
        return res;
    }

    private int dfs(TreeNode node, int k, int seq) {
        if(node == null){
            return seq;
        }

        seq = dfs(node.left, k, seq);


        seq++;

        if(seq == k) {
            res = node.val;
            return seq;
        }

        seq = dfs(node.right, k, seq);

        return seq;
    }
}
