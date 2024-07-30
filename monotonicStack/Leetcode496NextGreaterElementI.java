package monotonicStack;

import java.util.*;
public class Leetcode496NextGreaterElementI {
    /**
     We will use monotonic increasing  stack in increasing order from array top to bottom and we will use new array to store next greater element in place of iterating nums2 form the behind(we are traversing from back to find next greater element because stack does store the track of the next greater element).
     Using HashMap here as the elements are unique and we will use hashMap to store index of every element and
     in order to store the nextgreater element for any number in nums1, we can use that index to get the resukt from
     nextGreaterElement array
     TC: O(n)
     SC: O(n)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nextGreaterElement = new int[nums2.length];
        int[] result = new int[nums1.length];
        Map<Integer,Integer> hashMap = new HashMap<>();
        Stack<Integer> monotonicStack = new Stack<>();
        for(int i=0; i<nums2.length;i++){
            hashMap.put(nums2[i], i);
        }
        for(int i=nums2.length-1; i>=0;i--){
            while(monotonicStack.size()>0 && monotonicStack.peek()<nums2[i]){
                monotonicStack.pop();
            }
            nextGreaterElement[i] = monotonicStack.size()>0 ? monotonicStack.peek(): -1;
            monotonicStack.push(nums2[i]);
        }
        for(int i=0; i<nums1.length;i++){
            result[i] = nextGreaterElement[hashMap.get(nums1[i])];
        }
        return result;
    }
}
