package BinaryTree;

import java.util.*;

/*
* We will use DFS Algorithm to go deeper towards the root in the tree. We will first traverse the tree and then we will look if the value needs to be deleted.
* Reason being suppose we are at node a which has to be deleted whose left child is b node and b also has tp be deleted as specified in the to_delete array then we won't be able to
* traverse it and will add it in the final result. So we have to first see if lowest nodes need to be deleted or not.
*
* */
public class Leetcode1110DeleteNodesAndReturnForest {

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

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteNodes = new HashSet<>();
        for (int num : to_delete) {
            deleteNodes.add(num); // Autoboxing: int to Integer
        }
        List<TreeNode> nodes = new ArrayList<>();
        if(!dfs(root, deleteNodes, nodes)){
            nodes.add(root);
        }
        return nodes;
    }


    private boolean dfs(TreeNode node, Set<Integer> to_delete, List<TreeNode> nodes){
        if(node == null){
            return false;
        }

        boolean isLeftNodeToDelete = dfs(node.left, to_delete, nodes);
        boolean isRightNodeToDelete = dfs(node.right, to_delete, nodes);

        if(isLeftNodeToDelete)
            node.left = null;
        if(isRightNodeToDelete)
            node.right = null;
        if(to_delete.contains(node.val)){
            if(node.left !=null)
                nodes.add(node.left);
            if(node.right !=null)
                nodes.add(node.right);
            return true;
        }

        return false;

    }
}
