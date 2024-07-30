package PrefixSum;

public class Leetcode1653MinimumDeletionsToMakeStringBalanced {
    /**
     * This problem is related to prefix and suffix count. There are 3 approaches:
     * - (Most optimal): 1 pass with O(1) space non-dp: Here on encountering b, we will increase b count and if encountered a, we will update result by min(delreq+1, bcount).
     * - 2 pass with O(1) space: Here first we will count all the a's after in 1 pass and in the second pass we will calculate result and b's before
     * - 2 pass with O(1) space: Here first we will count all the a's after in 1 pass and in the second pass we will calculate result and b's before
     * - 2 Pass with O(N) space:  In first pass we will calculate b's before and store it in the array.in the second pass we will start from the end, calculate the
     * result and a's after
     * - 3 pass with O(2n) space: calculate b's before and a's after separetely and in the 3rd pass calculate the result
     * */
    public int minimumDeletionsApproach1(String s) {
        int bCount = 0;
        int res = 0;

        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == 'b') bCount++;
            else {
                res = Math.min(res+1, bCount);
            }
        }
        return res;
    }

    public int minimumDeletionsApproach2(String s) {
        if(s.length()==1 && s.charAt(0) == 'a') return 0;
        int b_prefix_count = s.charAt(0) == 'b' ? 1 :0;
        int a_prefix_count = 0;
        int n = s.length();
        int idx = n-1;
        for(;idx>=0;idx--){
            char ch = s.charAt(idx);
            if(ch == 'a'){
                a_prefix_count++;
            }
        }
        int res = b_prefix_count+a_prefix_count-1;
        idx = 1;
        for(;idx<n;idx++){
            if(s.charAt(idx-1) == 'a') a_prefix_count--;
            if(s.charAt(idx) == 'b') b_prefix_count++;
            res = Math.min(res, b_prefix_count+a_prefix_count-1);
        }
        return res;
    }
    public int minimumDeletionsApproach3(String s) {
        int[] bBefore = new int[s.length()];
        int[] currBCount = {0};
        int[] currACount = {0};
        int res = Integer.MAX_VALUE;
        int n = s.length();

        int idx = 0;
        for(Character ch: s.toCharArray()){
            bBefore[idx++] = currBCount[0];
            if(ch == 'b'){
                currBCount[0]++;
            }
        }
        idx--;
        for(;idx>=0;idx--){
            char ch = s.charAt(idx);
            res = Math.min(res, bBefore[idx]+currACount[0]);
            if(ch == 'a'){
                currACount[0]++;
            }
        }
        return res;
    }

    public int minimumDeletions(String s) {
        int[] bBefore = new int[s.length()];
        int[] aAfter = new int[s.length()];
        int[] currBCount = {0};
        int[] currACount = {0};

        int idx = 0;
        for(Character ch: s.toCharArray()){
            bBefore[idx++] = currBCount[0];
            if(ch == 'b'){
                currBCount[0]++;
            }
            // System.out.println(Arrays.toString(bBefore));
        }
        idx--;
        for(;idx>=0;){
            char ch = s.charAt(idx);
            aAfter[idx--] = currACount[0];
            if(ch == 'a'){
                currACount[0]++;
            }
        }
        // System.out.println(Arrays.toString(bBefore));
        // System.out.println(Arrays.toString(aAfter));

        int res = Integer.MAX_VALUE;
        idx = 0;
        for(;idx<s.length();idx++){
            res = Math.min(res, bBefore[idx]+aAfter[idx]);
        }
        return res;

    }

}
