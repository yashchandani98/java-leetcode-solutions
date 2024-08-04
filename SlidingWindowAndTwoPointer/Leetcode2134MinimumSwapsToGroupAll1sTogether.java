package SlidingWindowAndTwoPointer;

public class Leetcode2134MinimumSwapsToGroupAll1sTogether {
    /**
     * Sliding Window Approach:
     * - Size of the sliding window should be equals to the number of 1 present in an array. We will count how many numbers of o present in each slidinw window. The number of 1 present in each window should be the swap needed to make consecutive 1's. We will find minimum swap in every window. For cyclic array, we will make left_ptr and right_ptr to 0 once it has surpassed array index.
     * - We will use hashSet to store if left_ptr index value is already present in the hashset to stop the cyclic array iteration.
     * TC: O(n)
     * SC: O(n)
     * */

    public static void main(String[] args){
        /**
         *
         * def minSwaps(self, nums: List[int]) -> int:
         *         if len(nums) == 1 and nums[0] == 0:
         *             return 0
         *         win_size = Counter(nums).get(1, 0)
         *         if win_size == 0:
         *             return win_size
         *         left_ptr, right_ptr = 0, win_size-1
         *         hashSet = set()
         *         count = float("inf")
         *         counter = None
         *         while left_ptr not in hashSet:
         *             if not counter:
         *                 counter = Counter(nums[left_ptr:right_ptr+1])
         *             if counter.get(1,0) > 0:
         *                 count = min(count, counter.get(0,0))
         *             hashSet.add(left_ptr)
         *             counter[nums[left_ptr]]-=1
         *             left_ptr+=1
         *             right_ptr+=1
         *             if left_ptr == len(nums):
         *                 left_ptr = 0
         *             if right_ptr == len(nums):
         *                 right_ptr = 0
         *             counter[nums[right_ptr]]+=1
         *
         *         return count
         *
         * */
    }
}
