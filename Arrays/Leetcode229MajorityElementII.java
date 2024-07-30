package Arrays;

/**
 Boyer moore for n/3.
 We will use two candidates which are possibly to exist more than n/3 times in an array.
 The algorithm would be same  except an additional check in which we will check if the other candidate will not be same as the current element when we update current candidate for count == 0 condition i.e. intersection should not happen between two candidates
 */

import java.util.*;
public class Leetcode229MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        Set<Integer> res = new HashSet<>();
        int candidate1 = 0, candidate2 = 0, count1 = 0, count2 = 0;
        for(int num: nums){
            if(count1 == 0 && num!=candidate2){
                count1 = 1;
                candidate1 = num;
            } else if(count2 == 0 && num!=candidate1){
                count2 = 1;
                candidate2 = num;
            } else if(num == candidate2){
                count2++;
            } else if(num == candidate1){
                count1++;
            } else{
                count1--;
                count2--;
            }
        }

        count1=0;
        count2=0;
        for(int num: nums){
            if(num == candidate1){
                count1++;
            }
            if(num == candidate2){
                count2++;
            }
        }
        if(count1>nums.length/3){
            res.add(candidate1);
        }
        if(count2>nums.length/3){
            res.add(candidate2);
        }
        return new ArrayList<>(res);
    }
}
