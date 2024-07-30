package Graph;
import java.util.*;

public class Leetcode2192AncestorsOfDAG {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for(int[] edge : edges) {
            List<Integer> adjacentNode = adjacencyList.getOrDefault(edge[0], new ArrayList<Integer>());
            adjacentNode.add(edge[1]);
            adjacencyList.put(edge[0], adjacentNode);
        }
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0; i<n; i++){
            res.add(new ArrayList<>());
        }

        for(int i=0;i<n;i++){
            dfs(i, i, adjacencyList, res, new boolean[n]);
        }
        return res;
    }

    private void dfs(Integer parent, int node, Map<Integer, List<Integer>> adjacencyList, List<List<Integer>> res, boolean[] visited){

        List<Integer> adjacentNodes = adjacencyList.getOrDefault(node, new ArrayList<Integer>());

        visited[node] = true;

        for(int i=0; i< adjacentNodes.size(); i++){
            int dest=adjacencyList.get(node).get(i);
            if(!visited[dest]){
                res.get(dest).add(parent);
                dfs(parent,dest,adjacencyList,res,visited);
            }
        }
    }
}
