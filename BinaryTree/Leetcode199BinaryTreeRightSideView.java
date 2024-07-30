package BinaryTree;

import java.util.*;


public class Leetcode199BinaryTreeRightSideView {
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
    public List<Integer> rightSideView(TreeNode root) {
        /**
         We will use BFS (Level order traversal) and always print righter view.
         Note: If right child of any node is null, print the left child

         // BFS Approach (No need to maintain extra memory, Just calulate queue size and iterate until the number of elements present in the queue and in first add the value because first we will add TreeNode from the right and then left. Hence, the right view for every level is present at front of the queue)

         // DFS Approach: Needed extra memory to store all the element in every level, Add the element in 2d arrayList for every level, Note: index will be the level. Then traverse through the whole array list and add the last  value always
         */

        List<Integer> res = new ArrayList<>();
        if(root==null){
            return new ArrayList<Integer>();
        }
        // DFS approach

        //  List<ArrayList<Integer>> helper = new ArrayList<>();
        //  dfs(root, 0, helper);
        //  for(List<Integer> el: helper){
        //     res.add(el.get(el.size()-1));
        //  }


        // BFS Approach
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);

        while(!que.isEmpty()){
            int n = que.size();
            for(int i=0; i< n;i++){
                TreeNode el = que.remove();
                if(el.right!=null)
                    que.add(el.right);
                if(el.left!=null)
                    que.add(el.left);
                if(i==0)
                    res.add(el.val);
            }
        }
        return res;
    }

    private void dfs(TreeNode node, int level, List<ArrayList<Integer>> result){
        if(node.left == null && node.right == null){
            if(level<result.size()){
                result.get(level).add(node.val);
            } else{
                result.add(level, new ArrayList<>(Arrays.asList(node.val)));
            }
            return;
        }

        // int leftLevel = 0;
        // int rightLevel = 0;
        if(level<result.size())
            result.get(level).add(node.val);
        else{
            result.add(level, new ArrayList<>(Arrays.asList(node.val)));
        }
        if(node.left!=null)
            dfs(node.left, level+1, result);
        if(node.right!=null)
            dfs(node.right, level+1, result);

        return;
    }
}
