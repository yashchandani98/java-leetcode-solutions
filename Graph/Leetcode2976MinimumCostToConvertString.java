package Graph;
import java.util.*;
public class Leetcode2976MinimumCostToConvertString {
    /**
     * We will treat characters as the Node of a graph and cost to convert between original and advanced is the distance of the vertices between two character edges.
     * In every source to target conversion, we will call dijkstra's algorithm to get the minimum cost and store it in the Map using memoization approach to save
     * time. In the next iteration if we encounter same source to target conversion, we will directly return it from the method.
     *
     * DS: minHeap, HashMap, HashSet
     * Algorithm: Dijkstra's
     * Problem solving: Dynamic programming
     *
     * */
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        if (source.length() != target.length()) return -1;

        // Build the transformation graph with costs
        Map<Character, List<Pair<Character, Integer>>> graph = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            graph.computeIfAbsent(original[i], k -> new ArrayList<>())
                    .add(new Pair<>(changed[i], cost[i]));
        }

        // Memoization cache
        Map<Pair<Character, Character>, Long> memo = new HashMap<>();
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            char srcChar = source.charAt(i);
            char tgtChar = target.charAt(i);
            if (srcChar != tgtChar) {
                long costToConvert = dijkstra(srcChar, tgtChar, graph, memo);
                if (costToConvert == Long.MAX_VALUE) {
                    return -1;
                }
                totalCost += costToConvert;
            }
        }

        return totalCost;
    }

    private long dijkstra(char start, char end, Map<Character, List<Pair<Character, Integer>>> graph, Map<Pair<Character, Character>, Long> memo) {
        Pair<Character, Character> key = new Pair<>(start, end);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        PriorityQueue<Pair<Character, Long>> minHeap = new PriorityQueue<>(Comparator.comparingLong(Pair::getValue));
        Map<Character, Long> minCostMap = new HashMap<>();
        Set<Character> visited = new HashSet<>();

        minHeap.add(new Pair<>(start, 0L));
        minCostMap.put(start, 0L);

        while (!minHeap.isEmpty()) {
            Pair<Character, Long> current = minHeap.poll();
            char currentChar = current.getKey();
            long currentCost = current.getValue();

            if (currentChar == end) {
                memo.put(key, currentCost);
                return currentCost;
            }

            if (visited.contains(currentChar)) continue;
            visited.add(currentChar);

            List<Pair<Character, Integer>> neighbors = graph.get(currentChar);
            if (neighbors == null) continue;

            for (Pair<Character, Integer> neighbor : neighbors) {
                char nextChar = neighbor.getKey();
                long newCost = currentCost + neighbor.getValue();

                if (newCost < minCostMap.getOrDefault(nextChar, Long.MAX_VALUE)) {
                    minCostMap.put(nextChar, newCost);
                    minHeap.add(new Pair<>(nextChar, newCost));
                }
            }
        }

        long result = Long.MAX_VALUE;
        memo.put(key, result);
        return result;
    }

    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
