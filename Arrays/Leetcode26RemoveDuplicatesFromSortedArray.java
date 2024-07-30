package Arrays;


/*
* Iteration and swapping approach.
*
* */
public class Leetcode26RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] arr) {
        int i = 0;
        for(int j=1; j<arr.length;j++){
            if(arr[i]!=arr[j]){
                i++;
                arr[i] = arr[j];
            }
        }
        return i+1;
    }
}
