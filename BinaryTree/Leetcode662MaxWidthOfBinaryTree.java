package BinaryTree;

import java.util.*;
public class Leetcode662MaxWidthOfBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /**
     * We will Perform Level order traversal, Also we will put the index of every node in the hashMap.
     * Left child index: 2*currNodeIdx + 1
     * Right child index: 2*currNodeIdx + 2.
     *
     * Remember, these indexes will help us calculate the width at each level.
     * At every level of iteration, we will assign leftNodeIndex as the first node index present in the queue and will
     * keep updating the rightmost index at the level. The result will be rightNodeIdx - leftNodeIdx. + 1
     * TC: O(n)
     * SC: O(2^n)
     * **/
    public int widthOfBinaryTree(TreeNode root) {
        // Level order traversal
        int resultantWidth = 0;
        Map<TreeNode, Integer> nodeToIdx = new HashMap<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        nodeToIdx.put(root, 0);
        while(!que.isEmpty()){
            int n = que.size();
            int leftMostIdx = nodeToIdx.get(que.peek());
            int rightMostIdx = 0;
            for(int i=0;i<n;i++){
                TreeNode node = que.poll();
                int currIdx = nodeToIdx.get(node);
                rightMostIdx = currIdx;
                if(node.left!=null){
                    que.offer(node.left);
                    nodeToIdx.put(node.left, 2*currIdx+1);
                }
                if(node.right!=null){
                    que.offer(node.right);
                    nodeToIdx.put(node.right, 2*currIdx+2);
                }
            }
            resultantWidth = Math.max(resultantWidth, rightMostIdx-leftMostIdx+1);
        }
        return resultantWidth;
    }
}
