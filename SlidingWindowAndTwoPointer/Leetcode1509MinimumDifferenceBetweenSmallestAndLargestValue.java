package SlidingWindowAndTwoPointer;

import java.util.*;
/**
 The minimum difference possible is obtained by removing three elements between the three smallest and three largest values in the array.

 use sliding window algo to calculate minimum difference.
 Steps behind the approach is:
 - Sort an array
 - First we will remove first 3 elements from an extreme left and calculate difference and min
 - Then we will remove last 1 element from extreme right and first 2 element from an extreme left  and calculate difference and min
 - Then we will remove last 2 element from extreme right and first 1 element from an extreme left  and calculate difference and min
 - Then we will remove last 3 elements from an extreme right and calculate difference and min

 Intuition
 First lets understand for k = 3
 You can remove in this manner

 Remove first three and last zero elements (3,0) --> nums[n-1] - nums[3]
 Remove first two and last one (2,1) --> nums[n-2] - nums[2]
 Remove first one and last two (1,2) --> nums[n-3] - nums[1]
 Remove first zero and last three elements (0,3) --> nums[n-4] - nums[0]
 So we take the minimum of these.

 So for k we can solve like this

 remove (k,0)
 remove (k-1,1)
 remove (k-2,2)
 so on.....

 */
public class Leetcode1509MinimumDifferenceBetweenSmallestAndLargestValue {
    public int minDifference(int[] nums) {
        if(nums.length<=4){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);

        int i = 3, j = nums.length -1;

        while(i>=0){
            min = Math.min(nums[j] - nums[i], min);
            i--;
            j--;
        }
        return min;
    }
}
