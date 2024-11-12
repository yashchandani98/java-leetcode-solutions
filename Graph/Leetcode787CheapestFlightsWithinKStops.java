package Graph;

import java.util.*;

public class Leetcode787CheapestFlightsWithinKStops {
    private Map<Integer, List<int[]>> graph = new HashMap<>();

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build the graph
        buildGraph(flights);

        // Use a priority queue to store the state [cost, currentNode, stopsRemaining]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src, k + 1}); // start with 0 cost, src node, and k+1 stops

        // Track the minimum cost to reach each node with a specific number of stops
        Map<String, Integer> minCost = new HashMap<>();

        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int cost = state[0];
            int node = state[1];
            int stops = state[2];

            if (node == dst) return cost;

            if (stops > 0) {
                for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                    int nextNode = neighbor[0];
                    int travelCost = neighbor[1];
                    int newCost = cost + travelCost;
                    String key = nextNode + "-" + (stops - 1);

                    if (newCost < minCost.getOrDefault(key, Integer.MAX_VALUE)) {
                        minCost.put(key, newCost);
                        pq.offer(new int[]{newCost, nextNode, stops - 1});
                    }
                }
            }
        }

        return -1;
    }

    private void buildGraph(int[][] flights) {
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, cost});
        }
    }
}
