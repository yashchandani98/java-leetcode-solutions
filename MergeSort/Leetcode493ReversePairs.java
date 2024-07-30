package MergeSort;

/*
* Intuition behind using merge sort is to divide every sub-array in to two haves and after dividing in to two subarrays,
* look for every possible pairs and increment counter if found. After looking for every possibilities, merge two sub-array in sorted way
* and return merged array.
*
* */
public class Leetcode493ReversePairs {
    private int counter;
    public int reversePairs(int[] nums) {
        int[] sortedArray = mergeSort(nums, 0, nums.length - 1);
        return counter;
    }

    private int[] mergeSort(int[] arr, int low, int high){
        if(low == high){
            int[] tempArr = new int[1];
            tempArr[0] = arr[low];
            return tempArr;
        }
        int mid = (high + low) /2;

        int[] sortedArr1 = mergeSort(arr, low, mid);
        int[] sortedArr2 = mergeSort(arr, mid+1, high);

        countPairs(sortedArr1, sortedArr2);

        return  mergeArray(sortedArr1, sortedArr2);
    }

    private void countPairs(int[] arr1, int[] arr2){
        // First we will try possibilities of the condition
        int len1 = arr1.length, len2 = arr2.length;
        int iter1=0, iter2=0;

        while(iter1<len1 && iter2<len2){
            if(arr1[iter1]/2.0 >  arr2[iter2]){
                counter = counter + (len1-iter1);
                iter2++;
            } else{
                iter1++;
            }
        }
    }

    private int[] mergeArray(int[] arr1, int[] arr2){
        // Merge the array and convert it into sorted array
        int len1 = arr1.length, len2 = arr2.length;
        int[] tempArr = new int[len1+len2];

        int iter1=0, iter2=0;
        int idx = 0;
        while(iter1<len1 && iter2<len2){
            if(arr1[iter1]<=arr2[iter2]){
                tempArr[idx] = arr1[iter1];
                idx++;
                iter1++;
            } else{
                tempArr[idx] = arr2[iter2];
                idx++;
                iter2++;
            }
        }
        while(iter1<len1){
            tempArr[idx] = arr1[iter1];
            idx++;
            iter1++;
        }
        while(iter2<len2){
            tempArr[idx] = arr2[iter2];
            idx++;
            iter2++;
        }
        return tempArr;
    }
}
