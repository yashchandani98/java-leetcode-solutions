package SlidingWindowAndTwoPointer;

import java.util.Arrays;

public class Leetcode167TwoSumII {
    public static void main(String[] args){
        int[] arr = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(arr, target)));
    }
    public static int[] twoSum(int[] numbers, int target) {
        int[] indices = new int[2];
        int lptr = 0, rptr = numbers.length - 1;
        while(lptr<rptr) {
            int currSum = numbers[lptr]+numbers[rptr];
            if(currSum == target) {
                indices[0] = lptr+1;
                indices[1] = rptr+1;
                break;
            } else if (currSum < target) {
                lptr++;
            } else {
                rptr--;
            }
        }
        return indices;
    }
}
