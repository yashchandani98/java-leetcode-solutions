package BinaryTree;

import utils.*;
public class Leetcode124BinaryTreeMaximumPathSum {


    /**
     *Perform DFS and at each recursive call, do update result after every recursive call. the recursive call returns the maximum path value. Do focus on negative values as well.
     * */
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }

    private int dfs(TreeNode node){
        if(node == null){
            return 0;
        }

        int leftSum = dfs(node.left);
        int rightSum = dfs(node.right);

        int currSum = Integer.MIN_VALUE;
        currSum = Math.max(currSum, leftSum+rightSum+node.val);
        currSum = Math.max(currSum, rightSum+node.val);
        currSum = Math.max(currSum, leftSum+node.val);
        currSum = Math.max(currSum, node.val);


        result = Math.max(result, currSum);


        return Math.max(node.val, Math.max(node.val+leftSum, node.val+rightSum));
    }
}
