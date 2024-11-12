package BinarySearch;

public class Leetcode34FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1,-1};

        if(nums.length == 0) return res;

        int low = 0, high = nums.length-1;

        while(low<=high){
            int mid = (low + high)/2;

            if(nums[mid]<target){
                low = mid+1;
            } else if(nums[mid]>target){
                high = mid - 1;
            }

            if(nums[mid] == target){
                if(mid !=0 &&  nums[mid-1] == target){
                    high = mid-1;
                } else {
                    res[0] = mid;
                    break;
                }
            }
        }

        // if element first occurence is not found, then obviously 2nd occurence will also not be present hence return [-1, -1]
        if(res[0] == -1) return res;

        // From the next time, we will start from the first occurence where we found the target. The reason why we will not start from res[0] + 1 is that we are not
        // sure whether 2nd occurence is present or not in an array.
        low = res[0];
        high = nums.length-1;

        while(low<=high){
            int mid = (low + high)/2;

            if(nums[mid]<target){
                low = mid+1;
            } else if(nums[mid]>target){
                high = mid - 1;
            }

            if(nums[mid] == target){
                if(mid!=nums.length-1 &&  nums[mid+1] == target){
                    low = mid+1;
                } else {
                    res[1] = mid;
                    break;
                }
            }
        }
        return res;
    }
}
