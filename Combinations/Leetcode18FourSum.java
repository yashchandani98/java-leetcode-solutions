package Combinations;

import java.util.*;


/*
* Intuition behind this approach is to first sort an array then use two nested loops and inside the loop, use two pointer technique to
* find all the four integers.
* TC: O(n^3) + O(nLogn)
* SC: O(1)
* */
public class Leetcode18FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        if(nums.length<4) return new ArrayList<>();

        Arrays.sort(nums);

        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j< nums.length; j++){
                int left_ptr = j+1, right_ptr = nums.length-1;
                int sum_with_two_el =nums[i] + nums[j];
                while(left_ptr<right_ptr) {
                    double remainingSum = (double)target - (double)sum_with_two_el;
                    double currSum = (double)nums[left_ptr] + (double)nums[right_ptr];
                    if(currSum == remainingSum){
                        List<Integer> indices = Arrays.asList(nums[i], nums[j], nums[left_ptr], nums[right_ptr]);
                        if(!res.contains(indices)){
                            res.add(indices);
                        }
                        left_ptr++;
                        right_ptr--;
                    } else if(currSum<remainingSum){
                        left_ptr++;
                    } else {
                        right_ptr--;
                    }
                }
            }
        }

        return new ArrayList<>(res);
    }
}
