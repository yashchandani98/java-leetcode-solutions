package Recursion;

import java.util.*;

// The intuition behind this approach is to use recursive approach to find all the combinations of the marks to suffice target.
// Note: Since repetition is allowed in the question, so the same marks can be repeated
// Note: We have used Stack and not arrayList here to avoid reference value changes while removing an element in line number 33(Not consider element case) since we have to return 2d arrayList.

public class Leetcode39CombinationSum {
    // TC: O(2^N)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> currList = new Stack<>();
        solve(0, result, currList, 0, candidates, target);
        return result;
    }

    private void solve(int currSum, List<List<Integer>> result, Stack<Integer> currList, int idx, int[] candidates, int target){
        // Check if currSum has been surpassed or index has been surpassed
        if(currSum>=target || idx>= candidates.length){
            if(currSum == target){
                List<Integer> res = new ArrayList<Integer>(currList);
                result.add(res);
            }
            return;
        }

        // Consider the current index in the result and add the marks and don't increase the index of using same number infinite times
        // until the sum is surpassed the target value
        currList.push(candidates[idx]);
        solve(currSum+candidates[idx], result, currList, idx, candidates, target);
        // Don't Consider the current index in the result and don't add the marks. Rather increment the target
        currList.pop();
        solve(currSum, result, currList, idx+1, candidates, target);
    }
}
