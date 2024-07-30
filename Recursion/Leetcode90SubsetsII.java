package Recursion;

import java.util.*;
import java.util.stream.Collectors;

/*
*
* - First we will sort an array
- Then we will Use a recursive approach and keep on adding elements in ArrayList and ultimately in Hashset to avoid duplicate subsets.
* */

public class Leetcode90SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<String> hashSet = new HashSet<String>();
        ArrayList<Integer> currList = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        solve(0, hashSet, currList, nums);

        System.out.println(hashSet);
        for(String item: hashSet){
            if(item == "[]"){
                res.add(new ArrayList<>());
            } else{
                res.add(Arrays.stream(item.substring(1, item.length() - 1).split(","))
                        .map(String::trim) // Trim each element
                        .map(Integer::parseInt) // Parse each element to Integer
                        .collect(Collectors.toList()));
            }
        }

        return res;
    }
    private void solve(int idx, HashSet<String> hashSet, ArrayList<Integer> currList, int[] nums){
        if(idx>=nums.length){
            hashSet.add(currList.toString());
            return;
        }
        currList.add(nums[idx]);
        solve(idx+1, hashSet, currList, nums);
        currList.remove(currList.size()-1);
        solve(idx+1, hashSet, currList, nums);
    }
}
