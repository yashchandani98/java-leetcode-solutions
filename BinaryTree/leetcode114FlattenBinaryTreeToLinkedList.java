package BinaryTree;

import utils.*;
public class leetcode114FlattenBinaryTreeToLinkedList {
    /***
     *
     * We will perform DFS  post order traversal. We will explore left’s node rightest node and assign the rightest node’s right to the
     * right node returned during traversal of right subtree. Make currentNode left’s pointer to null, right’s pointer to left pointer.
     * these all will be performed if left is not null, if it’s null, we will not modify  any pointer since right subtree has been already arranged.
     * **TC: O(n) for traversal where n is number of nodes**
     * SC: O(n) worst case is tree is skewed or O(h) average case where h is height of the tree.
     */

    public void flatten(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode node){
        if(node==null){
            return node;
        }

        TreeNode left = dfs(node.left);
        TreeNode right = dfs(node.right);


        if(left!=null) {
            TreeNode traversalRight = left;
            while(traversalRight.right!=null){
                traversalRight = traversalRight.right;
            }
            traversalRight.right = right;

            node.left = null;
            node.right = left;
        }

        return node;
    }
}
