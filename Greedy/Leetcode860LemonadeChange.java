package Greedy;
/*
* Greedy approach
* We will use array of length 3 to store count of 5$, 10$ and 20$ notes to accomodate customers in the respected indexes
* */
public class Leetcode860LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int[] changeCount = new int[3];
        for(int bill: bills){
            if(bill == 5){
                changeCount[0]++;
            } else if(bill == 10){
                if(changeCount[0]>0){
                    changeCount[0]--;
                    changeCount[1]++;
                } else{
                    return false;
                }
            }
            else{
                if(changeCount[1]>0 && changeCount[0]>0){
                    changeCount[0]--;
                    changeCount[1]--;
                    changeCount[2]++;
                } else if(changeCount[1] == 0 && changeCount[0]>=3){
                    changeCount[0]-=3;
                    changeCount[2]++;
                } else{
                    return false;
                }
            }
        }
        return true;
    }
}
