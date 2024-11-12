package Simulation;

public class Leetcode1894StudentThatWillReplaceChalk {
    public int chalkReplacer(int[] chalk, int k) {
        long totalChalkNeeded = 0;

        for(int quan: chalk){
            totalChalkNeeded+=quan;
        }
        int remainChalk = (int) (k%totalChalkNeeded);


        for(int idx = 0; idx<chalk.length;idx++) {
            if(remainChalk<chalk[idx]){
                return idx;
            }
            remainChalk-=chalk[idx];
        }

        return 0;
    }
}
