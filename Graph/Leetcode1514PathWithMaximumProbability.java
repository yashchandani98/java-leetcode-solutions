package Graph;


import java.util.*;
public class Leetcode1514PathWithMaximumProbability {
    Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();
    static class Pair<D1,D2> {
        private final D1 node;
        private final D2 prob;
        public Pair(D1 node, D2 prob) {
            this.node = node;
            this.prob = prob;
        }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Dijkstra's algorithm
        PriorityQueue<Pair<Integer, Double>> pq = new PriorityQueue<>(
                (Pair<Integer, Double> p1, Pair<Integer, Double> p2) -> Double.compare(p2.prob, p1.prob)
        );


        Set<Integer> visited = new HashSet<>();

        buildGraph(edges, succProb);
        double[] maxProb = new double[n];
        Arrays.fill(maxProb, Double.MIN_VALUE);
        pq.offer(new Pair<Integer, Double>(start_node, 1.00));
        while(!pq.isEmpty()) {
            Pair<Integer, Double> pair = pq.poll();
            int node = pair.node;
            double prob = pair.prob;
            if(visited.contains(node)) continue;
            visited.add(node);

            List<Pair<Integer, Double>> neighbours =  graph.get(node);
            if(neighbours==null) continue;

            for(Pair<Integer, Double> neighbour : neighbours) {
                int neighbourNode = neighbour.node;
                double neighbourProb = neighbour.prob;

                if(maxProb[neighbourNode] < neighbourProb * prob) {
                    maxProb[neighbourNode] = neighbourProb * prob;
                }
                pq.offer(new Pair<Integer, Double>(neighbourNode, maxProb[neighbourNode]));
            }
        }

        return maxProb[end_node] == Double.MIN_VALUE ? 0 : maxProb[end_node];
    }

    private void buildGraph(int[][] edges, double[] succProb){
        int idx = 0;
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            double prob = succProb[idx++];
            graph.computeIfAbsent(from, k-> new ArrayList<>()).add(new Pair<Integer, Double>(to, prob));
            graph.computeIfAbsent(to, k-> new ArrayList<>()).add(new Pair<Integer, Double>(from, prob));
        }
    }
}
