package MergeSort;

public class Leetcode912MergeSort {
    public int[] sortArray(int[] nums) {
        int[] result = mergeSort(nums, 0, nums.length-1);
        return result;
    }

    private int[] mergeSort(int[]nums, int left, int right){
        if(right==left){
            int[] arr = {nums[right]};
            return arr;
        }

        int mid = (right+left)/2;
        int[] leftSortedHalf = mergeSort(nums, left, mid);
        int[] rightSortedHalf = mergeSort(nums, mid+1, right);

        return mergeArray(leftSortedHalf, rightSortedHalf);
    }

    private int[] mergeArray(int[] arr1, int[] arr2){
        int ptr1 = 0, ptr2 = 0;
        int[] result = new int[arr1.length+arr2.length];
        int idx = 0;
        while(ptr1<arr1.length && ptr2<arr2.length){
            if(arr1[ptr1] <= arr2[ptr2]){
                result[idx++] = arr1[ptr1++];
            } else{
                result[idx++] = arr2[ptr2++];
            }
        }
        while(ptr1<arr1.length){
            result[idx++] = arr1[ptr1++];
        }
        while(ptr2<arr2.length){
            result[idx++] = arr2[ptr2++];
        }

        return result;
    }
}
