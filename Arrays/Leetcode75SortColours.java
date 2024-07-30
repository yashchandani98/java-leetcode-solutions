package Arrays;

/**
 Use 3 pointer approach here
 - We will use left_ptr in the begining to make sure 0 is situated in correct position
 - We will use right_ptr in the end to make sure 2 is situated in correct position.
 - Middle elements (1) will be sorted automatically
 - use iter to iterate an array
 - if the nums[iter] value is 0, replace it with nums[left_ptr] and increment left_ptr
 - if the nums[iter] value is 2, replace it with nums[right_ptr] and decrement right_ptr
 - else increment iter
 */

public class Leetcode75SortColours {

    public void sortColors(int[] nums) {
        int left_ptr=0, right_ptr = nums.length - 1, iter = 0;
        while(iter<=right_ptr){
            if(nums[iter] == 0){
                int temp = nums[left_ptr];
                nums[left_ptr] = nums[iter];
                nums[iter] = temp;
                left_ptr++;
            }
            if(nums[iter] == 2){
                int temp = nums[right_ptr];
                nums[right_ptr] = nums[iter];
                nums[iter] = temp;
                right_ptr--;
            }else{
                iter++;
            }
        }
    }
}
