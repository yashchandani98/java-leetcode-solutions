package MaxHeap;

import java.util.*;


/*
*
* The intuition is to that while calculating median of any array, we should know middle element in a sorted array. i.e. Numbers on the left side of median should be less
* than equals to number on the right side.
* So, we will use 2 heaps
*   maxHeap for the left side to get the top element from the left side
*   minHeap for the right side to get the lowest element from the right side
*   median = (minheap.poll()+maxHeap.poll())/2 if minHeap.size() == maxHeap.size()
* */
public class Leetcode295FindMedianFromDataStream {
    private final PriorityQueue<Integer> minHeap; // right side of an array
    private final PriorityQueue<Integer> maxHeap; // left side of an array

    public Leetcode295FindMedianFromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        // Add an element in the left side of an array first
        maxHeap.offer(num);
        // We will see since we are always storing the data in the left side (max Heap). if the element by mistakenly added in the left side violates the property leftSide in heap <=rightSide in the heap
        if(minHeap.size()>0 && maxHeap.peek() > minHeap.peek()){
            minHeap.offer(maxHeap.poll());
        }
        // we have to keep the size difference between two heaps of atmost 1. Hence, this check is needed for both minHeap and maxHeap
        if(maxHeap.size() > minHeap.size() + 1){
            minHeap.offer(maxHeap.poll());
        }
        if(minHeap.size() > maxHeap.size() + 1){
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if(minHeap.size() == maxHeap.size())
            return (minHeap.peek() + maxHeap.peek())/2.0;
        if(minHeap.size() > maxHeap.size())
            return minHeap.peek();
        return maxHeap.peek();
    }
}
