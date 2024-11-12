package Arrays;

import java.util.*;
public class Leetcode539MinimumTimeDifference {
    /**
     * Intuition behind this problem is to first convert given time into minutes and store in an array and sort it with the minutes as the value. Then in the next iteration, keep on comparing adjacent minutes
     * and store in the global result with the min value. Remember time is circular, so do also compare last time in the sorted array with the first time in an array
     * so the next element May also be found between last element and first element in an array. Remember we will not compare 2nd element and the last element in the circular time
     * because if it would have got the min minutes value between last minute and first minute, no need to explore 2nd minutes as it will be of high value since array
     * is sorted.
     *
     * TC: O(nlogn)(sorting) + O(2n) (iteration)
     * SC: O(n)
     * */
    public int findMinDifference(List<String> timePoints) {
        int[] minutes = new int[timePoints.size()];

        int idx = 0;

        int result = Integer.MAX_VALUE;

        int prevTimeInMins = -1;
        for(String time: timePoints){
            int hours = Integer.parseInt(time.substring(0,2));
            int minute = Integer.parseInt(time.substring(3,5));
            int currentTimeMins = (hours*60) + minute;
            minutes[idx++] = currentTimeMins;
        }

        Arrays.sort(minutes);

        for(int i=1;i<minutes.length;i++){
            result = Math.min(result, Math.abs(minutes[i] - minutes[i-1]));
        }

        result = Math.min(result, Math.abs((minutes[0]+1440) - minutes[minutes.length-1]));

        return result;
    }
}
