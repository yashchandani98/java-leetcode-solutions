package Greedy;

import java.util.*;


/*
*
*
*Link: https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#

* Given arrival and departure times of all trains that reach a railway station. Find the minimum number of platforms required
* for the railway station so that no train is kept waiting.Consider that all the trains arrive on the same day and leave
* on the same day. Arrival and departure time can never be the same for a train but we can have arrival time of one train equal
* to departure time of the other. At any given instance of time, same platform can not be used for both departure of a train and
* arrival of another train. In such cases, we need different platforms.

Examples:

Input: n = 6, arr[] = {0900, 0940, 0950, 1100, 1500, 1800},
            dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
Output: 3
Explanation: There are three trains during the time 0940 to 1200. So we need minimum 3 platforms.
* */

public class GeeksForGeeksMinimumPlatforms {

    public static void main(String[] args){
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dept = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(findPlatform(arr, dept));
    }

    static int findPlatform(int[] arr, int[] dep)
    {
        Map<Integer,Character> trainActions = new HashMap<>();
        int totalPlatform = 0;
        int res = 0;

        int[] times = new int[arr.length+dep.length];

        int idx = 0;
        for(int time:arr){
            trainActions.put(time, 'A');
            times[idx++] = time;
        }
        for(int time:dep){
            trainActions.put(time, 'D');
            times[idx++] = time;
        }
        Arrays.sort(times);

        for(int time: times){
            if(trainActions.get(time) == 'A'){
                totalPlatform++;
            } else{
                totalPlatform--;
            }
            res = Math.max(res, totalPlatform);
        }
        return res;

    }
}
