package BinaryTree;

public class Leetcode572SubTreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    private boolean dfs(TreeNode root, TreeNode subRoot) {
        if(root == null){
            if(subRoot== null) return true;
            return false;
        }

        if(root.val == subRoot.val){
            if(checkIfSubTree(root, subRoot)){
                return true;
            }
        }
        boolean isValidLeft = dfs(root.left, subRoot);
        if(isValidLeft) return true;
        boolean isValidRight = dfs(root.right, subRoot);
        return isValidRight;
    }

    private boolean checkIfSubTree(TreeNode root1, TreeNode subRoot){
        // perform bfs over both the tree
        Queue<TreeNode[]> que = new LinkedList<>();
        que.offer(new TreeNode[]{root1, subRoot});

        while(!que.isEmpty()){
            int n = que.size();

            for(int i=0; i<n; i++){
                TreeNode[] nodes = que.poll();

                if(nodes[0] == null && nodes[1] == null){
                    continue;
                }
                if(nodes[0] == null) return false;
                if(nodes[1] == null) return false;
                if(nodes[0].val!=nodes[1].val) return false;
                que.offer(new TreeNode[]{nodes[0].left, nodes[1].left});
                que.offer(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }

        return true;
    }
}
