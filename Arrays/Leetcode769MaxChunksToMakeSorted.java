package Arrays;

public class Leetcode769MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        int max_val = 0, chunks = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max_val){
                max_val = arr[i];
            }

            if(max_val == i){
                chunks++;
            }
        }
        return chunks;
    }
}
