package heap;

import java.util.*;

public class Leetcode56MergeIntervals {
    /**
     * Use the minHeap to store int[start, end] and use start as a comparator to sort all the intervals,
     * first use the first element interval as  start and end interval to compare with the subsequent intervals in the heap.
     * Remember, we just don't have to check the two intervals, we have to be greedy here and keep on checking the subsequent
     * intervals until the condition becomes false, if it becomes false, we will add the start and end interval to the final
     * result and assign current intervals as the start and end.
     * */
    class Intervals{
        int firstValue;
        int lastValue;
        public Intervals(int firstValue, int lastValue){
            this.lastValue = lastValue;
            this.firstValue = firstValue;
        }
    }
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 1){
            return intervals;
        }
        PriorityQueue<Intervals> inter = new PriorityQueue<>(Comparator.comparingInt(a -> a.firstValue));
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<intervals.length; i++){
            inter.add(new Intervals(intervals[i][0], intervals[i][1]));
        }
        Intervals curr = inter.poll();
        int start = curr.firstValue, end = curr.lastValue;
        while(!inter.isEmpty()){
            curr = inter.poll();
            List<Integer> ansAppend = new ArrayList<>(2);
            if((end<=curr.lastValue && end>=curr.firstValue) || (end>=curr.lastValue && curr.firstValue<=end)){
                start = Math.min(start, curr.firstValue);
                end = Math.max(curr.lastValue, end);
            } else{
                ansAppend.add(start);
                ansAppend.add(end);
                ans.add(ansAppend);
                start = curr.firstValue;
                end = curr.lastValue;
            }
        }
        if(curr !=null){
            List<Integer> ansAppend = new ArrayList<>(2);
            ansAppend.add(start);
            ansAppend.add(end);
            ans.add(ansAppend);
        }
        return ans.stream()
                .map(u -> u.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }

    // Optimised code
    public int[][] merge2(int[][] intervals) {
        if(intervals.length == 1) return intervals;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));

        // Populate all the intervals in minHeap
        for(int[] interval: intervals){
            pq.offer(interval);
        }

        List<int[]> result = new ArrayList<>();


        int[] startInterval = pq.poll();

        int start = startInterval[0], end = startInterval[1];

        while(!pq.isEmpty()){
            int[] interval = pq.poll();

            if((end<=interval[1] && end>=interval[0]) || (end>=interval[0] && end>=interval[1])){
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            } else {
                int[] newInterval = new int[2];
                newInterval[0] = start;
                newInterval[1] = end;
                start = interval[0];
                end = interval[1];
                result.add(newInterval);
            }
        }


        // add the last evaluated start and end
        result.add(new int[]{start, end});

        return result.stream().toArray(int[][]::new);

    }
}
