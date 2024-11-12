package BinarySearch;

public class Leetcode540SingleElementInASortedArray {
    /**
     * We will use binary search. Reason being the array is already sorted and we will leverage the property that is mentioned: Every element in an array
     * appears twice except one which we have to return it.
     * condition to reduce search space:
     * Target = single element
     * left = 0, right = nums.length -1
     * if mid == even:
     *      the element appears at mid index should not appear at mid -1, if so that means our target is on the left side. i.e. right = mid-1
     *      otherwise left = mid + 1
     * if mid == odd:
     *      the element appears at mid index should not appear at mid + 1, if so that means our target is on the left side. i.e. right = mid-1
     *       otherwise left = mid + 1
     * Base case:
     *  if (mid ==0 && ele[mid]!=ele[mid+1]) || (mid ==len(nums)-1 && ele[mid]!=ele[mid-1]) || (ele[mid]!=ele[mid-1] && nums[mid]!=nums[mid+1])
     *      return nums[mid]
     *
     * TC: O(Logn)
     * SC: O(1)
     * */
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1) return nums[0];
        int left = 0, right = nums.length - 1;
        while(left<=right){
            int mid = (left+right)/2;
            if((mid == 0 && nums[mid] !=nums[mid+1]) || (mid==nums.length-1 && nums[mid] != nums[mid-1]) ||  (nums[mid] != nums[mid-1] && nums[mid] !=nums[mid+1])){
                return nums[mid];
            }
            if(mid%2==0){
                if(nums[mid] == nums[mid-1]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if(nums[mid] == nums[mid+1]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
