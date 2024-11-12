package BinaryTree;

import java.util.*;
public class Leetcode1361ValidBinaryTreeNodes {

    /***
     * Use kahn's Algorithm to validate if a given graph(We will consider input as a graph until is being proved that it's a binary tree) is a valid binary tree or not
     * Make sure :
     * - Only 1 node should be the root node for a valid binary tree
     * - All nodes in a binary tree should have exactly 1 as indegree  or exactly one node except root node
     *
     * */
    Map<Integer, List<Integer>> graph = new HashMap<>();

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        List<Integer> path = new ArrayList<>();
        int[] indegrees = constructGraph(n, leftChild, rightChild);

        Queue<Integer> que = new LinkedList<>();

        for(int i = 0; i<n;i++){
            if(indegrees[i] == 0){
                que.offer(i);
            }
        }

        // If number of root node is not equals to 1, it's not a valid binary tree
        if(que.size()!=1) return false;
        while(!que.isEmpty()){
            int i = que.poll();
            path.add(i);

            List<Integer> childs = graph.getOrDefault(i, new ArrayList<>());

            for(int child: childs){
                // If indegree of a node is greater than 1, it's not a valid binary tree i.e. a node can have exactly 1 parent
                if(indegrees[child]>1) return false;
                indegrees[child]--;
                if(indegrees[child]==0){
                    que.offer(child);
                }
            }
        }
        return path.size() == n;
    }


    private int[] constructGraph(int n, int[] leftChild, int[] rightChild){
        int[] indegrees = new int[n];
        for(int i=0;i<n;i++){
            if(leftChild[i]!=-1) {
                graph.computeIfAbsent(i, k->new ArrayList<>()).add(leftChild[i]);
            }
            if(rightChild[i]!=-1) {
                graph.computeIfAbsent(i, k->new ArrayList<>()).add(rightChild[i]);
            }
            if(leftChild[i]!=-1) {
                indegrees[leftChild[i]]++;
            }
            if(rightChild[i]!=-1) {
                indegrees[rightChild[i]]++;
            }
        }

        return indegrees;
    }
}
