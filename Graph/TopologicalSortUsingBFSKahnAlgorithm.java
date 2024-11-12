package Graph;

import java.util.*;

public class TopologicalSortUsingBFSKahnAlgorithm {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    private List<Integer> topologicalSort(){
        int numberOfNodes = 5;
        int[][] nodes = {{1,4},{2,4},{3,1},{3,2}};
        int[] indegrees;
        List<Integer> topoSort = new ArrayList<>();

        Queue<Integer> que = new LinkedList<>();

        indegrees = buildGraph(nodes, numberOfNodes);

        for(int i=0; i<numberOfNodes; i++){
            if(indegrees[i] == 0) {
                que.offer(i);
            }
        }

        while(!que.isEmpty()){
            int node = que.poll();
            topoSort.add(node);

            List<Integer> neigh = graph.get(node);

            if(neigh!=null){
                for(int nei: neigh){
                    indegrees[nei]--;
                    if(indegrees[nei] == 0){
                        que.offer(nei);
                    }
                }
            }
        }
        return topoSort;
    }

    private int[] buildGraph(int[][] nodes, int numberOfNodes){
        int[] indegrees = new int[numberOfNodes];
        for(int[] node: nodes) {
            graph.computeIfAbsent(node[0], k-> new ArrayList<>()).add(node[1]);
            indegrees[node[1]]++;
        }
        return indegrees;
    }


}
