package Arrays;


/*
* Store Count in an integer array
* */
public class Leetcode2965FindMissingAndRepeatedValues {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] count = new int[grid.length*grid.length];
        int[] res = new int[2];

        for(int i=0; i<grid.length*grid.length; i++){
            count[i] = 0;
        }

        for(int[] item: grid){
            for(int el: item){
                count[el-1]++;
            }
        }

        for(int k =0; k<count.length; k++){
            if(count[k] == 0){
                res[1] = k+1;
            } else if(count[k] > 1){
                res[0] = k+1;
            }
        }
        return res;
    }
}
