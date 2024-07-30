package SlidingWindowAndTwoPointer;

import java.util.*;


/*
*
* Approach-1(Current): The intuition behind this approach is to first sort an array, then use 2 nested loops and then inside use 2 pointer technique for other indexes to find if these sum
* is equal to target.
* Approach-2: The intuition behind this approach is to use 2 nested loops then use 1 pointer technique for other indexes to find if target - current sum
* is present in hashSet. if yes we will add that to the result and add the encountered element to the hashset, else we will add that element to the hashSet ultimately we have that value present
* in the hashset.
*TC: O(n^3)
*
* */
public class Leetcode153Sum {
    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(arr));
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        List<List<Integer>> triplets = new ArrayList<List<Integer>>();

        for(int i=0; i< nums.length; i++){
            int target = 0 - nums[i];
            int lptr = i+1, rptr = nums.length - 1;
            while(lptr<rptr){
                List<Integer> triplet = new ArrayList<>(3);
                int currSum = nums[lptr] + nums[rptr];
                if(target == currSum){
                    triplet.add(nums[i]);
                    triplet.add(nums[lptr]);
                    triplet.add(nums[rptr]);
                    res.add(triplet);
                    lptr++;
                    rptr--;
                } else if(target<currSum) {
                    rptr--;
                } else{
                    lptr++;
                }
            }

        }
        triplets.addAll(res);
        return triplets;
    }
}
