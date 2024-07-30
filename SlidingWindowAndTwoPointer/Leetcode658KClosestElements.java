package SlidingWindowAndTwoPointer;

import java.util.*;

public class Leetcode658KClosestElements {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int k = 4;
        int x=3;
        System.out.println(findClosestElements(arr, k, x));
    }
    /**
     PriorityQueue Approach
     */
    // public List<Integer> findClosestElements(int[] arr, int k, int x) {
    //     PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> Math.abs(x-a) != Math.abs(x-b) ? Math.abs(x-b) - Math.abs(x-a) : b-a);
    //     for(int el: arr){
    //         pq.add(el);
    //         if(pq.size()>k){
    //             pq.poll();
    //         }
    //     }
    //     List<Integer> res = new ArrayList<>(pq);
    //     Collections.sort(res);
    //     return res;
    // }
    /**
     Two pointers approach
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lptr = 0, rptr = arr.length-1;

        while(rptr-lptr >= k){
            if(Math.abs(x-arr[lptr]) > Math.abs(x-arr[rptr])){
                lptr++;
            } else{
                rptr--;
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = lptr; i<=rptr;i++){
            res.add(arr[i]);
        }
        return res;
    }
}
