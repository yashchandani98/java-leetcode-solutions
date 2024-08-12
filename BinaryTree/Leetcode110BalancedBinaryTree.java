package BinaryTree;

public class Leetcode110BalancedBinaryTree {
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
    private static class Pair {
        private final int height;
        private final boolean isbalanced;
        public Pair(int height, boolean balanced){
            this.height = height;
            this.isbalanced = balanced;
        }
    }

    /**
     * Perform DFS and return Pair<int height, boolean isBalanced> for every subtree.
     * */
    public boolean isBalanced(TreeNode root) {
        // Perform DFS
        if(root == null){
            return true;
        }

        Pair res = dfs(root);

        return res.isbalanced;
    }

    private Pair dfs(TreeNode node){
        if(node == null){
            return new Pair(0, true);
        }

        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        int leftHeight = left.height;
        int rightHeight = right.height;

        if(Math.abs(leftHeight - rightHeight)>1 || !left.isbalanced || !right.isbalanced){
            System.out.println(node.val);
            System.out.println(Math.abs(leftHeight - rightHeight));
            System.out.println(left.isbalanced);
            System.out.println(right.isbalanced);
            return new Pair(1+ Math.max(leftHeight, rightHeight), false);
        } else {
            return new Pair(1+ Math.max(leftHeight, rightHeight), true);
        }
    }
}
