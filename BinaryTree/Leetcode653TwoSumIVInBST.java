package BinaryTree;

import java.util.*;
import utils.*;
public class Leetcode653TwoSumIVInBST {

    /**
     * DFS Inorder approach: Use Hashset to keep the track of the previous nodes value, If k-currNode.val is [resent in the hashset, then we found the Two nodes woth sum equals to k.
     *
     * */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> visited = new HashSet<>();
        return dfs(root, k, visited);
    }

    private boolean dfs(TreeNode node, int k, Set<Integer> visited){
        if(node == null){
            return false;
        }

        boolean isFoundOnLeft = dfs(node.left, k, visited);

        if(isFoundOnLeft) return true;

        if(visited.contains(k-node.val)){
            return true;
        }

        visited.add(node.val);

        boolean isFoundOnRight = dfs(node.right, k, visited);

        if(isFoundOnRight) return true;

        return false;
    }
}
