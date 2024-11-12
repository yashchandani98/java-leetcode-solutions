package Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class DetectCycleUsingBFSInUndirectedGraph {

    private static final Map<Integer, List<Integer>> graph = new HashMap<>();

    static class Pair {
        int node;
        int parentNode;

        public Pair(int node, int parentNode) {
            this.node = node;
            this.parentNode = parentNode;
        }
    }
    public static boolean isCycleDetected() {
        Queue<Pair> traverse = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        // Iterate over all nodes to ensure disconnected components are checked
        for (int startNode : graph.keySet()) {
            if (!visited.contains(startNode)) {
                traverse.offer(new Pair(startNode, -1));
                while(!traverse.isEmpty()) {
                    Pair pair = traverse.poll();
                    int node = pair.node;
                    int parentNode = pair.parentNode;

                    if(visited.contains(node)){
                        // Cycle detected here because the node is being visited twice from two different parent nodes.
                        return true;
                    }
                    visited.add(node);

                    List<Integer> neighbours = graph.get(node);

                    if(neighbours==null) continue;
                    for(int neighbour: neighbours) {
                        // This ensures to not traverse the parent node again using neighbour node
                        if(neighbour!=parentNode) {
                            traverse.offer(new Pair(neighbour,node));
                        }
                    }
                }
            }
        }
        return false;
    }
}
