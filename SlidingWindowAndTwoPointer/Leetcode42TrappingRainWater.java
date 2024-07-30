package SlidingWindowAndTwoPointer;

public class Leetcode42TrappingRainWater {
    /**
     The Intuition here is to find minimum height to the left and minimum height to the right for the current block to check how much water
     it can contain for the current block. Also we will have to use for every block what was the maximum height of the block to the left and
     similarly maximum height of the block to the right in order to sustain the water. So will use memoization approach here and will apply 2 for loop
     to store maxL and maxR one towards forward and one towards backward
     */
    public static void main(String[] args) {
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(heights));
    }

    public static int trap(int[] height) {
        int len = height.length;
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        int currMax = 0, res = 0;
        for(int i=0; i<len; i++){
            maxLeft[i] = currMax;
            if(currMax<height[i]){
                currMax = height[i];
            }
        }

        currMax = 0;
        for(int i=len-1; i>=0; i--){
            maxRight[i] = currMax;
            if(currMax<height[i]){
                currMax = height[i];
            }
        }

        for(int i = 0; i<len; i++){
            int x = Math.min(maxLeft[i], maxRight[i]);
            if(x > height[i]) {
                res = res + x  - height[i];
            }
        }

        return res;

    }

}
