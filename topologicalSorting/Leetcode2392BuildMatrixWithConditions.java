package topologicalSorting;

import java.util.*;

public class Leetcode2392BuildMatrixWithConditions {
    /**
     Intuition: Treat this probelm as a graph problem where 1 to k numbers are nodes and we have to sort these numbers according to the given row and column condition (we have to sort these nodes according to the given row and column condition). So we will use Topological sort to solve this problem. First we will have to detect if cycle is present to check if row and column conditions are valid. If yes, return null else solve the problem using topological sorting
     */
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

        Map<Integer, List<Integer>> rowGraph = constructAdjacencymatrix(rowConditions);
        Map<Integer, List<Integer>> colGraph = constructAdjacencymatrix(colConditions);
        Stack<Integer> rowStack = new Stack<Integer>();
        boolean isConditionsValid = true;
        int[] visited = new int[k+1];
        /* Check if conditions are valid (No cycle in terms of graph) if yes add nodes to the stack as per it's row index in the matrix. Remember
         the top of the stack element should appear before the second topmost one and so on.
        */
        for(int i = 1; i<=k; i++){
            if(visited[i] == 0){
                isConditionsValid = dfs(rowGraph, visited, i, rowStack);
                if(!isConditionsValid)
                    return new int[0][0];
            }
        }
        visited = new int[k+1];
        // Check if conditions are valid (No cycle in terms of graph) if yes add nodes to the stack as per it's row index in the matrix. Remember
        // the top of the stack element should appear before the second topmost one and so on.
        Stack<Integer> colStack = new Stack<Integer>();
        for(int i = 1; i<=k; i++){
            if(visited[i] == 0){
                isConditionsValid = dfs(colGraph, visited, i, colStack);
                if(!isConditionsValid)
                    return new int[0][0];
            }
        }
        // HashMap for k elements which will store row and col indexes.
        Map<Integer, int[]> kHashMap = new HashMap<>();
        int idx = 0;
        // Put row index for every k elements.
        while(rowStack.size()>0){
            int el = rowStack.pop();
            int[] indices = {idx++,0};
            kHashMap.put(el, indices);
        }

        idx = 0;
        // Put column index for every k elements.
        while(colStack.size()>0){
            int el = colStack.pop();
            int[] indices = kHashMap.get(el);
            indices[1] = idx++;
            kHashMap.put(el, indices);
        }

        // Create new matrix with k*k size
        int[][] result = new int[k][k];
        for(Map.Entry<Integer, int[]> entry: kHashMap.entrySet()){
            int[] indices = entry.getValue();
            // Put the element in respective row and column number in the result
            result[indices[0]][indices[1]] = entry.getKey();
        }
        return result;
    }

    private Map<Integer, List<Integer>> constructAdjacencymatrix(int[][] conditions){
        Map<Integer, List<Integer>> hashMap = new HashMap<>();
        for(int[] condition: conditions){
            List<Integer> gr = hashMap.getOrDefault(condition[0], new ArrayList<>());
            gr.add(condition[1]);
            hashMap.put(condition[0], gr);
        }
        return hashMap;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int[] visited, int node, Stack<Integer> stack){
        if (visited[node] == 1) {
            return false;
        }
        if (visited[node] == 2) {
            return true;
        }

        // Mark the current node as visited
        visited[node] = 1;

        // Visit all neighbors
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!dfs(graph, visited, neighbor, stack)) {
                return false; // Cycle detected downstream
            }
        }
        stack.add(node);

        visited[node] = 2;
        // No cycle found starting from this node
        return true;
    }
}
