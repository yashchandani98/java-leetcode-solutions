package LinkedList;

import utils.TreeNode;

public class Leetcode1367LinkedListInBinaryTree {

    /**
     * There will be 2 dfs call.
     *  1. It will check for every node in the sub-tree and it's head whether it can become downward path.
     *  2. In 2nd dfs call we will check if either left or sub-tree is making a downwards path. In this iteration we will iterate both linkedList and Binary Tree
     *  to check downwards path.
     *
     * */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root==null) return false;
        if(head.val == root.val){
            System.out.println(root.val);
            boolean isValidSubTree = dfs(head, root);
            if(isValidSubTree) return true;
        }
        boolean isValidSubTree = isSubPath(head, root.left);
        if(isValidSubTree) return true;
        isValidSubTree = isSubPath(head, root.right);
        if(isValidSubTree) return true;
        return false;
    }

    private boolean dfs(ListNode nodeLinkedList, TreeNode nodeTree) {
        if(nodeTree==null) {
            if(nodeLinkedList== null) return true;
            else return false;
        }
        if(nodeLinkedList==null) return true;

        if(nodeLinkedList.val!=nodeTree.val) return false;

        boolean isLeftSubTreeValid = dfs(nodeLinkedList.next, nodeTree.left);
        if(!isLeftSubTreeValid) {
            boolean isRightSubTreeValid = dfs(nodeLinkedList.next, nodeTree.right);
            if(isRightSubTreeValid) return true;
        } else {
            return true;
        }
        return false;
    }
}
