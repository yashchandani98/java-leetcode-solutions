package Recursion;

import java.util.*;


/*
*
*
* */

public class Leetcode40CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Stack st = new Stack<Integer>();
//        st.addA
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currList = new ArrayList<Integer>();
        Arrays.sort(candidates);
        solve(result, currList, 0, target, 0, candidates);
        return result;
    }

    private void solve(List<List<Integer>> result, List<Integer> currList, int currSum, int target, int idx, int[] candidates){
        if(currSum>=target || idx>=candidates.length){
            System.out.println(currSum);
            System.out.println(idx);
            if(currSum == target){
                result.add(new ArrayList<>(currList));
            }
            return;
        }
        for(int i=idx;i<candidates.length;i++){
            /* If current element and last element value are same then we will skip the current iteration.
            //Reason being for the previous element, current element will be anyways picked up in the
            combination by using for loop. So to avoid duplicate combination, we need to skip it.
            */
            if(i>idx && candidates[i] == candidates[i-1])
                continue;
            // if on adding current score is surpassing the target, we will break here. reason being ahead all the score will
            // be greater then this so need to break
            if(currSum+candidates[i]>target)
                break;
            currList.add(candidates[i]);
            solve(result, currList, currSum+candidates[i], target, i+1, candidates);
            currList.remove(currList.size()-1);
        }
    }
}
