package Arrays;

/*
*
*  Boyer moore coting algorithm
* Refer notes to know about this algorithm
* */
public class Leetcode169MajorityElement {
    public int majorityElement(int[] nums) {
        int res = -1, count =0;

        for(int num: nums){
            if(count ==0){
                res = num;
                count++;
            } else if(res == num){
                count++;
            } else{
                count--;
            }
        }
        return res;
    }
}
