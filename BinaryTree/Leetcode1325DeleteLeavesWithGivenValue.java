package BinaryTree;

import utils.TreeNode;

public class Leetcode1325DeleteLeavesWithGivenValue {
    /**
     * We will do post order traversal using dfs and use parentNode as the current node's parent and current node in the dfs call.
     * */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        dfs(null, root, target);
        if(root.val == target && root.left == null && root.right==null){
            return null;
        }
        return root;
    }

    private void dfs(TreeNode parentNode, TreeNode node, int target) {
        if(node==null){
            return;
        }
        dfs(node, node.left, target);
        dfs(node, node.right, target);

        if(parentNode!=null && node.left == null && node.right==null && node.val == target){
            if(parentNode.left == node) {
                parentNode.left=null;
            } else if(parentNode.right == node) {
                parentNode.right=null;
            }
        }
        return;
    }
}
