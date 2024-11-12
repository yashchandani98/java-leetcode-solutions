package heap;

import java.util.*;
public class Leetcode3275KthNearestObstacleQueries {
    public int[] resultsArray(int[][] queries, int k) {
        int[] res = new int[queries.length];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;
        for(int[] query: queries){
            minHeap.offer(Math.abs(query[0]) + Math.abs(query[1]));
            res[idx] = getkthObstacle(minHeap, k);
            // System.out.println(res[idx]);
            idx++;
        }
        return res;
    }

    private int getkthObstacle(PriorityQueue<Integer> minHeap, int k){
        if(minHeap.size()<k) return -1;
        if(minHeap.size()==k) return minHeap.peek();
        int res = -1;

        int popCount = minHeap.size() - k;

        for(int i =0;i<popCount;i++){
            minHeap.poll();
        }
        res = minHeap.peek();

        return res;
    }
}
