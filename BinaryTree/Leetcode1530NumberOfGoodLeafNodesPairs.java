package BinaryTree;

import java.util.*;

/*
* The intuition here is to use DFS approach and for every parent node it must get distances from children towards it from the children. So we will check
* for every distance present in left distacnce array if summing up its distance with every distance present in the distance array from the right is <= distance provided
* if yes, increment the counter and return a new array of distance which contains all the distances from both the array. Make sure to add +1 for every distance ehich includes parent node distance as well.
*
* Base case should be if it's a leaf node: return [1] else return []
* TC: O(N^2)
* SC: O(N)
*
* */

public class Leetcode1530NumberOfGoodLeafNodesPairs {

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
    public int countPairs(TreeNode root, int distance) {
        int[] counter = {0};
        dfs(root, distance, counter);
        return counter[0];
    }
    private List<Integer> dfs(TreeNode node, int distance, int[] counter) {
        if(node == null)
            return new ArrayList<>();
        if(node.left == null && node.right == null){
            System.out.println(node.val);
            List<Integer> dis = new ArrayList<>();
            dis.add(1);
            return dis;
        }

        List<Integer> disFromLeft = dfs(node.left, distance, counter);
        List<Integer> disFromRight = dfs(node.right, distance, counter);

        for(Integer disLeft : disFromLeft) {
            for(Integer disRight : disFromRight) {
                if(disLeft+disRight <= distance)
                    counter[0]++;
            }
        }

        List<Integer> distances = new ArrayList<Integer>();

        for(Integer disLeft : disFromLeft) {
            if(disLeft+1<=distance)
                distances.add(disLeft+1);
        }

        for(Integer disRight : disFromRight) {
            if(disRight+1<=distance)
                distances.add(disRight+1);
        }
        return distances;

    }
}
