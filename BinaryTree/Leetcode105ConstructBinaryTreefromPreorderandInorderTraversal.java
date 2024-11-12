package BinaryTree;

import utils.TreeNode;

import java.util.Map;

public class Leetcode105ConstructBinaryTreefromPreorderandInorderTraversal {
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> eleToIdxInorder = new HashMap<>();

        for(int i =0; i<inorder.length; i++) {
            eleToIdxInorder.put(inorder[i], i);
        }


        return buildTree(0, preorder.length - 1, new int[1], preorder, eleToIdxInorder);
    }

    private TreeNode buildTree(int left_ptr, int right_ptr, int[] idx, int[] preorder, Map<Integer, Integer> eleToIdxInorder) {
        if(idx[0]>=preorder.length || left_ptr>right_ptr){
            return null;
        }

        int ele = preorder[idx[0]];
        TreeNode node = new TreeNode(ele);
        idx[0]++;

        node.left = buildTree(left_ptr, eleToIdxInorder.get(ele) - 1, idx, preorder, eleToIdxInorder);
        node.right = buildTree(eleToIdxInorder.get(ele) + 1, right_ptr, idx, preorder, eleToIdxInorder);

        return node;
    }
}
