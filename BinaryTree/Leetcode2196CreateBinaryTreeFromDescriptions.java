package BinaryTree;

import java.util.*;
public class Leetcode2196CreateBinaryTreeFromDescriptions {
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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<TreeNode> nodes = new HashSet<>();
        Set<TreeNode> childrens = new HashSet<>();
        Map<Integer, TreeNode> hashMap = new HashMap<>();

        for(int[] description: descriptions){
            TreeNode parent = hashMap.containsKey(description[0]) ? hashMap.get(description[0]): new TreeNode(description[0]);
            nodes.add(parent);
            TreeNode node = hashMap.containsKey(description[1]) ? hashMap.get(description[1]) : new TreeNode(description[1]);
            if(description[2] == 1){
                parent.left = node;
            } else {
                parent.right = node;
            }
            hashMap.put(description[1], node);
            hashMap.put(description[0], parent);
            childrens.add(node);
            nodes.add(node);
        }

        for(TreeNode node: nodes){
            if(!childrens.contains(node)){
                return node;
            }
        }
        return null;
    }
}
