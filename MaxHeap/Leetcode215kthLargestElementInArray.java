package MaxHeap;

import java.util.*;
import java.util.stream.Collectors;
public class Leetcode215kthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
        pq.addAll(list);

        int res = 0;
        while(k>0){
            res = pq.poll();
            k--;
        }
        return res;
    }
}
