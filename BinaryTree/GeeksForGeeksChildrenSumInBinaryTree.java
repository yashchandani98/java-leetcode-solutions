package BinaryTree;

public class GeeksForGeeksChildrenSumInBinaryTree {
    
    
    /**
     * DFS: Recursively check for every subtree. if any sub-tree is not balanced (property is not applicable), return false and donot check for supertree if any of the left or right subtree
     * returns false;
     * */
    public static int isSumProperty(Node root)
    {
        boolean res = dfs(root);
        return res ? 1: 0;

    }

    private static boolean dfs(Node node){
        if(node.left == null && node.right == null){
            return true;
        }

        boolean leftBalanced = true;
        boolean rightBalanced = true;

        if(node.left!=null){
            leftBalanced = dfs(node.left);
        }
        if(node.right!=null){
            rightBalanced = dfs(node.right);
        }

        if(!leftBalanced || !rightBalanced){
            return false;
        }

        return node.data == (node.left!=null ? node.left.data : 0) + (node.right!=null ? node.right.data : 0);
    }
}
