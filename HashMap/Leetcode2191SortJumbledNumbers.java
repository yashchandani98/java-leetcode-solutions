package HashMap;

import java.util.*;


/*
* Use HashMap to store the mapping numbers, then sort the boxed numbers (stream of numbers) using value of the hashMap
*
* */

public class Leetcode2191SortJumbledNumbers {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        Map<Integer, Integer> numberMapping = new HashMap<>();

        for (int num : nums) {
            if (!numberMapping.containsKey(num)) {
                numberMapping.put(num, mapNumbers(num, mapping));
            }
        }

        Integer[] numsBoxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(numsBoxed, (a, b) -> Integer.compare(numberMapping.get(a), numberMapping.get(b)));

        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsBoxed[i];
        }

        return nums;
    }

    private int mapNumbers(int num, int[] mapping) {
        if (num == 0) {
            return mapping[0];
        }
        int res = 0;
        int curMult = 1;
        while (num > 0) {
            int digit = num % 10;
            num /= 10;
            res += mapping[digit] * curMult;
            curMult *= 10;
        }
        return res;
    }
}
