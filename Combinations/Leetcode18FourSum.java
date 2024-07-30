package Combinations;

import java.util.*;


/*
* Intuition behind this apporoach is to first sort an array then use two nested loops and inside the loop, use two pointer technique to
* find all the four integers.
* TC: O(n^3) + O(nLogn)
* SC: O(1)
* */
public class Leetcode18FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        int numsLen = nums.length;

        Arrays.sort(nums);

        for(int i=0; i<numsLen; i++){
            for(int j = i+1; j<numsLen; j++){
                //Two pointer approach
                int currSum = nums[i]+nums[j];
                int left_ptr = j+1, right_ptr = numsLen - 1;
                double requiredSum = (double)target-(double)currSum;
                while(left_ptr<right_ptr){
                    double tempSum = (double)nums[left_ptr] + (double)nums[right_ptr];
                    if(tempSum>requiredSum){
                        right_ptr--;
                    } else if(tempSum<requiredSum){
                        left_ptr++;
                    } else{
                        List<Integer> result = new ArrayList<>();
                        result.add(nums[i]);
                        result.add(nums[j]);
                        result.add(nums[left_ptr]);
                        result.add(nums[right_ptr]);
                        if(!res.contains(result)){
                            res.add(result);
                        }
                        left_ptr++;
                        right_ptr--;
                        // break;
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
}
