package Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

public class DetectCycleUsingDFSInUndirectedGraph {

    private static final Map<Integer, List<Integer>> graph = new HashMap<>();
    private static final Set<Integer> visited = new HashSet<>();
    private static boolean isCycleDetected() {
        for(int node: graph.keySet()) {
            if(!visited.contains(node)) {
                boolean isCycleDetected = dfs(node, -1);
                if(isCycleDetected) return true;
            }
        }
        return false;
    }

    private static boolean dfs(int node, int parentNode){
        if(visited.contains(node)) return true;
        visited.add(node);

        List<Integer> neighbours = graph.get(node);
        if(neighbours==null) return false;

        for(int neighbourNode: neighbours) {
            if(neighbourNode!=parentNode){
                boolean isCycleDetected = dfs(neighbourNode, node);
                if(isCycleDetected) return true;
            }
        }

        return false;
    }

    public static void main(String[] agrs){
//        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 2));
        graph.put(2, Arrays.asList(0, 1));
        graph.put(3, Arrays.asList(4));
        graph.put(4, Arrays.asList(3));
        System.out.println(isCycleDetected());
    }


}
