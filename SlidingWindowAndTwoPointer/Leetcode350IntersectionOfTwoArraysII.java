package SlidingWindowAndTwoPointer;

import java.util.*;

/**
 There are 3 ways to solve this problem:
 - Binary Search
    - Iterate over the first array and use pointer for the first array.
    - Sort the 2nd array
    - Use Binary Search in the 2nd array to determine if the number to which the pointer is pointing in the 1st array is presend in  2nd array
 - HashTable
    - Use 2 hash tables to store the elements and calculate minimum frequency of every elements in the hashtable.
 - Two pointers (TC: O(nLogn) + O(m+n))
     - Sort an array
     - Use ptr_1 in nums1, ptr_2 in nums2
     - iterate until the end of both the arrays.
     - if nums1[ptr_1]<nums2[ptr_2]: ptr_1++
     - if nums1[ptr_1]>nums2[ptr_2]: ptr_2++
     - else: add an element in the resultant array
 */
public class Leetcode350IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        int ptr_1 = 0, ptr_2 = 0;
        int nums1_len = nums1.length, nums2_len = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<Integer>();
        int idx = 0;
        while(idx<(nums1_len+nums2_len)){
            if(ptr_1 == nums1_len || ptr_2 == nums2_len){
                break;
            }
            System.out.println(ptr_1);
            System.out.println(ptr_2);
            if(nums1[ptr_1]<nums2[ptr_2]){
                if(ptr_1+1 < nums1_len){
                    ptr_1++;
                }
            }
            else if(nums1[ptr_1]>nums2[ptr_2]){
                if(ptr_2+1 < nums2_len){
                    ptr_2++;
                }
            }
            else{
                res.add(nums1[ptr_1]);
                ptr_1++;
                ptr_2++;
                if(nums1_len<nums2_len && ptr_1 == nums1_len){
                    break;
                }
                if(nums2_len<nums1_len && ptr_2 == nums2_len){
                    break;
                }
            }
            idx++;
        }
        return Arrays.stream(res.toArray())
                .filter(Number.class::isInstance)
                .map(Number.class::cast)
                .mapToInt(Number::intValue)
                .toArray();
    }
}
