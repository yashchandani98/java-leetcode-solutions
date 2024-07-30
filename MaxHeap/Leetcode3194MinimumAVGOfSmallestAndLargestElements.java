package MaxHeap;

import java.util.*;

public class Leetcode3194MinimumAVGOfSmallestAndLargestElements {
    public double minimumAverage(int[] nums) {
        PriorityQueue<Float> minpq = new PriorityQueue<Float>();
        PriorityQueue<Float> maxpq = new PriorityQueue<Float>(Collections.reverseOrder());
        float avg = Float.MAX_VALUE;
        for(int i=0; i<nums.length;i++){
            minpq.add((float)nums[i]);
            maxpq.add((float)nums[i]);
        }
        for(int i=0; i<nums.length/2;i++){
            if(!minpq.isEmpty()){
                float minElement = minpq.poll();
                float maxElement = maxpq.poll();
                minpq.remove(maxElement);
                maxpq.remove(minElement);
                float currAvg = (minElement+maxElement)/2;
                avg = Math.min(currAvg, avg);
            } else{
                break;
            }
        }
        return avg;
    }
}
