package BinaryTree;


import utils.*;
public class Leetcode98ValidateBST {

    /**
     * We will do inorder traversal and will keep the track of previous value, and in every traversal, we will check if the property of BST is followed
     * */

    private long preVal= Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }

        boolean isLeftSubTreeValidBST = isValidBST(root.left);

        if(preVal>=root.val) return false;
        preVal = root.val;


        boolean isRightSubTreeValidBST = isValidBST(root.right);


        if(!isLeftSubTreeValidBST || !isRightSubTreeValidBST) {
            return false;
        }

        if(root.left == null && root.right == null) {
            return true;
        }

        return true;
    }
}
