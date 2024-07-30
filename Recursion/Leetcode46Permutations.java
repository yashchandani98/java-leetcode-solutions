package Recursion;

import java.util.*;

/*
* We will iterate over the whole array to add an element in the array possibly and We will use hashSet to store the indexes whose element which has been taken in the currentList.
*               [1,2,3]
*           1      2        3
*       2        1   3   1     2
*   3
* */
public class Leetcode46Permutations {
    /**
     TC: O(N!)
     */
    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> hashSet = new HashSet<Integer>();
        List<Integer> currList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        solve(nums, hashSet, currList, result);
        return result;
    }

    private void solve(int[] nums, Set<Integer> hashSet, List<Integer> currList, List<List<Integer>> result){
        // Base case
        if(currList.size()==nums.length){
            result.add(new ArrayList<>(currList));
            return;
        }

        for(int i=0; i<nums.length; i++){
            if(!hashSet.contains(i)){
                currList.add(nums[i]);
                hashSet.add(i);
                solve(nums, hashSet, currList, result);
                hashSet.remove(i);
                currList.remove(currList.size()-1);
            }
        }
    }
}
