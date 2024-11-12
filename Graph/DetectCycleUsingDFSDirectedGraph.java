package Graph;

import java.util.*;

public class DetectCycleUsingDFSDirectedGraph {

    private static final Map<Integer, List<Integer>> graph = new HashMap<>();
    private static final Set<Integer> visited = new HashSet<>();
    private static final Set<Integer> dfsVisited = new HashSet<>();
    public static boolean isCycleDetected() {
        for(int node: graph.keySet()) {
            if(!visited.contains(node)) {
                boolean isCycleDetected = dfs(node);
                if(isCycleDetected) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(int node){
        if(dfsVisited.contains(node)) return true;

        if(visited.contains(node)) return false;

        dfsVisited.add(node);

        List<Integer> neighbours = graph.get(node);

        if(neighbours != null){
            for(int nei: neighbours) {
                boolean isCycleDetected = dfs(nei);
                if(isCycleDetected) return true;
            }
        }
        dfsVisited.remove(node);
        visited.add(node);

        return false;
    }

    private static boolean dfs2(int node) {
        if(visited.contains(node)) return false;
        visited.add(node);
        if(dfsVisited.contains(node)) return true;

        List<Integer> neighbours = graph.get(node);

        if(neighbours == null) return false;

        for(int nei: neighbours) {
            dfsVisited.add(nei);
            boolean isCycleDetected = dfs(nei);
            if(isCycleDetected) {
                return true;
            }
            dfsVisited.remove(nei);
        }
        return false;
    }

    public static void main(String[] args) {
        // Example usage:
        // Add edges to the graph
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(4));
        graph.put(4, Arrays.asList(2)); // Creates a cycle: 2 -> 3 -> 4 -> 2

        System.out.println(isCycleDetected() ? "Cycle detected" : "No cycle detected");
    }
}
