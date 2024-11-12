package Graph;

import java.util.*;
public class Leetcode743NetworkDelayTime {
    static class Pair{
        int node;
        int time;
        public Pair(int node, int time){
            this.node = node;
            this.time = time;
        }
    }

    Map<Integer, List<int[]>> graph = new HashMap<>();

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] timeNeeded = new int[n+1];

        int delayTime = 0;

        Set<Integer> visited = new HashSet<>();

        Arrays.fill(timeNeeded, Integer.MAX_VALUE);
        timeNeeded[k] = 0;

        constructGraph(times);

        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> a.time - b.time);

        pq.offer(new Pair(k,0));

        while(!pq.isEmpty()) {
            Pair pair = pq.poll();
            int node = pair.node;
            int time = pair.time;

            if(visited.contains(node)) continue;
            visited.add(node);

            List<int[]> edges = graph.get(node);

            if(edges == null) continue;

            for(int[] edge: edges) {
                if(timeNeeded[edge[0]] > time + edge[1]) {
                    System.out.println("FlowWEWEWE"+node);
                    timeNeeded[edge[0]] = time + edge[1];
                }
                pq.offer(new Pair(edge[0], timeNeeded[edge[0]]));
            }
            System.out.println("Flow"+node);
        }

        int idx = 0;

        for(int time: timeNeeded) {
            if(idx++==0) continue;
            delayTime = Math.max(delayTime, time);
        }

        System.out.println(Arrays.toString(timeNeeded));
        return delayTime == Integer.MAX_VALUE ? -1 : delayTime;

    }


    private void constructGraph(int[][] times){
        for(int[] time: times) {
            graph.computeIfAbsent(time[0], k-> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }
    }
}
