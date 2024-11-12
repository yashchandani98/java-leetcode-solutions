package Graph;

import java.util.*;

public class Leetcode323NumberOfConnectedComponentsInUnDirectedGraph {

    /**
     * Make a dfs call for number of times equivalent to number of vertices if the vertex is not visited and increment count in every dfs call inside the iteration.
     *
     * */

    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Set<Integer> visited = new HashSet<>();
    private static int countComponents(int n, int[][] edges) {
        constructGraph(edges);
        int count = 0;
        for(int i=0;i<n;i++){
            if(!visited.contains(i)){
                count++;
                dfs(i);
            }
        }

        return count;
    }

    private static void dfs(int node){
        if(visited.contains(node)) return;

        visited.add(node);

        List<Integer> neighbours = graph.getOrDefault(node, new ArrayList<>());

        for(int nei: neighbours) {
                dfs(nei);
        }
    }
    private static void constructGraph(int[][] edges) {
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
    }
}
