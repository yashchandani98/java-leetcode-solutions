package BinaryTree;

import utils.*;
public class Leetcode1373MaximumSumBST {

    /***
     * We will perform DFS post order traversal. In every traversal, we will evaluate result iff it’s subtree is a valid bst.
     * If the current Subtree is valid, return Pair with the sum of current subtree nodes and currMax and currMin. Remember,
     * we would need currMax and currMin just to check if the super node (current subtree parent node) and current subtree combinely
     * form a valid BST (Ex: [1,null,10,-5,20]).
     **TC:** O(n) where n is number of nodes
     SC: O(n)  where n is number of nodes (in case of skewed tree) otherwise O(h) in average case.
     *
     * */
    private static class Pair{
        private boolean isBST;
        private int currSum;
        private int currMax;
        private int currMin;

        public Pair(boolean isBST, int currSum){
            this.isBST = isBST;
            this.currSum = currSum;
            this.currMax = Integer.MIN_VALUE;
            this.currMin = Integer.MAX_VALUE;
        }
    }
    public int maxSumBST(TreeNode root) {
        int[] result = new int[]{0};

        dfs(root, result);

        return result[0];
    }

    private Pair dfs(TreeNode node, int[] result){
        if(node == null){
            return new Pair(true, 0);
        }

        Pair leftResponse = dfs(node.left, result);
        Pair rightResponse = dfs(node.right, result);

        if(leftResponse.currMax == Integer.MIN_VALUE) {
            leftResponse.currMax = node.val;
        } else {
            if(leftResponse.isBST && node.val<leftResponse.currMax){
                Pair pair = new Pair(false, 0);
                pair.currMax = Math.max(node.val, Math.max(leftResponse.currMax, rightResponse.currMax));
                pair.currMin = Math.min(node.val, Math.min(leftResponse.currMin, rightResponse.currMin));
                return pair;
            }
        }

        if(rightResponse.currMin == Integer.MAX_VALUE) {
            rightResponse.currMin = node.val;
        } else {
            if(rightResponse.isBST && node.val>rightResponse.currMin){
                Pair pair = new Pair(false, 0);
                pair.currMax = Math.max(node.val, Math.max(leftResponse.currMax, rightResponse.currMax));
                pair.currMin = Math.min(node.val, Math.min(leftResponse.currMin, rightResponse.currMin));
                return pair;
            }
        }

        Pair pair = null;

        if(leftResponse.isBST && rightResponse.isBST) {
            if((node.left != null && node.left.val > node.val) || (node.right != null && node.right.val < node.val)) {
                pair = new Pair(false, 0);
            }
            else if(node.left != null && node.right!=null && node.left.val< node.val && node.val < node.right.val) {
                int sum = node.val + leftResponse.currSum + rightResponse.currSum;
                result[0] = Math.max(result[0], sum);

                pair = new Pair(true, sum);
            } else if (node.left == null && node.right == null){
                int sum = node.val;
                result[0] = Math.max(result[0], sum);

                pair = new Pair(true, sum);
            } else if(node.left == null && node.val < node.right.val) {
                int sum = node.val +rightResponse.currSum;
                result[0] = Math.max(result[0], sum);

                pair = new Pair(true, sum);
            } else if(node.right == null && node.left.val < node.val) {
                int sum = node.val +leftResponse.currSum;
                result[0] = Math.max(result[0], sum);

                pair = new Pair(true, sum);
            }
        }

        if(pair == null) {
            pair = new Pair(false, 0);
        }

        pair.currMax = Math.max(node.val, Math.max(leftResponse.currMax, rightResponse.currMax));
        pair.currMin = Math.min(node.val, Math.min(leftResponse.currMin, rightResponse.currMin));
        return pair;
    }
}
