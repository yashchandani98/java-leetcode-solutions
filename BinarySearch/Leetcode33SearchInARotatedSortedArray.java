package BinarySearch;

/*
* Use Binary search and see which part is sorted using mid an low or mid and high to the target and take decision accordingly.
*
* 1st we will compare which part is sorted using low and mid or high and mid, then we will compare target with mid and low or mid and high
Intuition: Identify the sorted half, check where the target is located, and then eliminate one half accordingly:
If arr[low] <= arr[mid]: This condition ensures that the left part is sorted.
If arr[low] <= target && target <= arr[mid]: It signifies that the target is in this sorted half. So, we will eliminate the right half (high = mid-1).
Otherwise, the target does not exist in the sorted half. So, we will eliminate this left half by doing low = mid+1.
Otherwise, if the right half is sorted:
If arr[mid] <= target && target <= arr[high]: It signifies that the target is in this sorted right half. So, we will eliminate the left half (low = mid+1).
Otherwise, the target does not exist in this sorted half. So, we will eliminate this right half by doing high = mid-1.
*
* TC: O(Logn)
* SC: O(1)
*
* */

public class Leetcode33SearchInARotatedSortedArray {
    public int search(int[] nums, int target) {

        int low = 0, high = nums.length - 1;

        while(low<=high){
            int mid = low + (high-low) / 2;
            if(target == nums[mid])
                return mid;

            if(nums[low] <= nums[mid]){
                if((target>=nums[low] && target <= nums[mid])){
                    high = mid-1;
                }
                else{
                    low = mid+1;
                }

            } else{
                if(target<=nums[high] && target >= nums[mid])
                    low = mid+1;
                else
                    high = mid-1;
            }
        }

        return -1;

    }
}
