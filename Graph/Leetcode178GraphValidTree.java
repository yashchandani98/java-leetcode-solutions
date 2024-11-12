package Graph;

import java.util.*;

public class Leetcode178GraphValidTree {

    /**
     * For a graph to be a valid tree, certain conditions should be valid enough:
     * - All the nodes should be visited starting from the source node, here 0
     * - The Graph should not contain cycle i.e. a loop should not odduc in the graph
     *
     * Perform DFS call and add the node in the visited set if there is no cycle. In the end, we will compare the visited set size with the total number of vertices
     * if equal, and a graph doesn't contain cycle, return true else return false
     *
     * */
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Set<Integer> visited = new HashSet<>();
    private static boolean isValidTree(int[][] edges, int n) {
        constructGraph(edges);
        // The DFS call will check if a graph contains cycle
        boolean isCycleDetected = dfs(0, -1);
        // If a graph is a valid tree, it must not contain cycle and all the nodes should be visited. Hence, checking if visitedSize == n(Number of vertices)
        return !isCycleDetected && visited.size() == n;
    }


    private static boolean dfs(int node, int parentNode){
        if(visited.contains(node)) return true;

        visited.add(node);

        List<Integer> neighbours = graph.getOrDefault(node, new ArrayList<>());

        for(int nei: neighbours) {
            if(nei!=parentNode){
                // Visit it's neighbours except parentNode (caller node)
                boolean isCycleDetected = dfs(nei, node);
                if(isCycleDetected) return true;
            }
        }
        return false;
    }

    private static void constructGraph(int[][] edges){
        for(int[] edge: edges){
            graph.computeIfAbsent(edge[0], k->new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k->new ArrayList<>()).add(edge[0]);
        }
    }
}
