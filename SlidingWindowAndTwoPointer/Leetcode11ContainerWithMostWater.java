package SlidingWindowAndTwoPointer;

public class Leetcode11ContainerWithMostWater {
    public static void main(String[] args) {
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(heights));
    }
    public static int maxArea(int[] height) {
        int lptr = 0, rptr = (height.length - 1);
        int maxVolume = 0;
        while(lptr<rptr) {
            if(height[lptr] < height[rptr]) {
                maxVolume = Math.max(maxVolume, (rptr-lptr) * height[lptr]);
                lptr++;
            } else{
                maxVolume = Math.max(maxVolume, (rptr-lptr) * height[rptr]);
                rptr--;
            }
        }
        return maxVolume;
    }
}
