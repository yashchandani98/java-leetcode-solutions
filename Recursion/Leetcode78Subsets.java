package Recursion;

import java.util.*;

public class Leetcode78Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        solve(0, new ArrayList<>(), result, nums);

        return result;
    }

    private void solve(int idx, List<Integer> currList, List<List<Integer>> result,int[] nums) {
        if(idx>=nums.length) {
            result.add(currList);
            return;
        }

        List<Integer> tempList = new ArrayList<>(currList);
        tempList.add(nums[idx]);
        solve(idx+1, tempList, result, nums);
        solve(idx+1, currList, result, nums);
    }
}
