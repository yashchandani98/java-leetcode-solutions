package BinaryTree;


/*
* We will use DFS approach to find out path for both startValue and destValue. Then we will remove common path between both the paths to find out LCA (Lowest common ancestor)
* and in the next step we will replace all L and R from the pathTowardsStart to 'U' and in the end we wil return by result by appending pathTowardsStart and pathTowardsDest
*
*
* */

public class Leetcode2096StepByStepDirectionsFromBinaryTreeNodeToAnother {
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
    public String getDirections(TreeNode root, int startValue, int destValue) {

        StringBuilder pathTowardsStart = new StringBuilder();
        StringBuilder pathTowardsDest = new StringBuilder();

        dfs(root, destValue, pathTowardsDest);

        dfs(root, startValue, pathTowardsStart);

        int ptr = 0;

        pathTowardsStart.reverse();
        pathTowardsDest.reverse();



        while(ptr<pathTowardsStart.length() && ptr<pathTowardsDest.length() && pathTowardsStart.charAt(ptr) == pathTowardsDest.charAt(ptr)){
            ptr++;
        }
        return "U".repeat(pathTowardsStart.length() - ptr) + pathTowardsDest.substring(ptr);
    }

    private boolean dfs(TreeNode node, int value, StringBuilder path){
        if(node.val == value){
            return true;
        }

        if(node.left!=null && dfs(node.left, value, path))
            path.append("L");
        else if(node.right!=null && dfs(node.right, value, path))
            path.append("R");

        return path.length()>0;
    }
}
