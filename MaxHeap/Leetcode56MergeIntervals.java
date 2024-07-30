package MaxHeap;

import java.util.*;

public class Leetcode56MergeIntervals {
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
        PriorityQueue<Intervals> inter = new PriorityQueue<>((a, b)-> a.firstValue-b.firstValue);
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
}
