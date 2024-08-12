package BinaryTree;

import java.util.*;
public class Leetcode987VerticalOrderTraversalOfBinaryTree {
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
    private static class TreeNodePair{
        private final TreeNode node;
        private final int rowpos;
        private final int colpos;

        public TreeNodePair(TreeNode node, int rowpos, int colPos){
            this.node = node;
            this.rowpos = rowpos;
            this.colpos = colPos;
        }
    }

    /**
     * With the help of Queue and BFS (Level order traversal) algorithm. We will add TreeNodePair in the queue with row and col indices. if we go left, the column
     * will be col-1 and if right, the col will be col+1. We will store the unprocessed result in the TreeMap with the ascending order of the column indices since it's a vertical order
     * traversal. In case same row and column is present, we will sort the data.
     * Note: Here in our use case since TreeMap compares the records with the column indexes and not row indexes. It's possible that we may get the result
     * of two different records with different row and same column indices will reside in the same TreeMap key. To know more about this in detail
     * Dry run some test cases.
     * TC: O(n*nlogn) ~= O(n^2logn)
     * SC: O(3n) ~= O(n)
     * */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // Base case
        if(root == null){
            return res;
        }

        Queue<TreeNodePair> que = new LinkedList<>();
        // This will contain result
        Map<int[], List<TreeNodePair>> levelToNodes = new TreeMap<>(Comparator.comparingInt(a -> a[1]));

        que.offer(new TreeNodePair(root, 0, 0));

        while(!que.isEmpty()){
            int n = que.size();
            for(int i=0; i<n;i++){
                TreeNodePair node = que.remove();
                int currRow = node.rowpos;
                int currColumn = node.colpos;
                int[] level = {currRow, currColumn};
                if(levelToNodes.containsKey(level)){
                    List<TreeNodePair> valuesList = levelToNodes.get(level);
                    List<TreeNodePair> unSortedList = new ArrayList<>();
                    List<TreeNodePair> sortedList = new ArrayList<>();
                    for(TreeNodePair item: valuesList){
                        if(item.rowpos == level[0] && item.colpos == level[1]){
                            sortedList.add(item);
                        } else {
                            unSortedList.add(item);
                        }
                    }
                    sortedList.add(node);
                    Collections.sort(sortedList, (a,b)->a.node.val-b.node.val);
                    valuesList=new ArrayList<>();
                    valuesList.addAll(unSortedList);
                    valuesList.addAll(sortedList);
                    levelToNodes.put(level, valuesList);
                } else {
                    levelToNodes.computeIfAbsent(level, k-> new ArrayList<>()).add(node);
                }
                if(node.node.left!=null){
                    TreeNode newNode = node.node.left;
                    TreeNodePair newNodePair = new TreeNodePair(newNode, currRow+1, currColumn-1);
                    que.offer(newNodePair);
                }
                if(node.node.right!=null){
                    TreeNode newNode = node.node.right;
                    TreeNodePair newNodePair = new TreeNodePair(newNode, currRow+1, currColumn+1);
                    que.offer(newNodePair);
                }
            }
        }

        for(List<TreeNodePair> item: levelToNodes.values()){
            List<Integer> helperRes = new ArrayList<Integer>();
            for(TreeNodePair node: item){
                helperRes.add(node.node.val);
            }
            res.add(helperRes);
        }
        return res;
    }
}
