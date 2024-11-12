package dp.tabulation;

public class GeeksForGeeksMaxSumIncreasingSubsequence {

    /**
     * Solve it using 1d dp(Tabulation). It's the variation of longest increasing subsequence. Instead of storing length of the lis, store the sum of LIS in an array.
     * TC: O(n^2)
     * SC: O(n)
     * */
    public int maxSumIS(int arr[], int n)
    {
        int[] maxSumIncreasingSubSequence = new int[n];

        for(int i=0;i<n;i++){
            // Since every element itself can be a LIS. Hence storing their number in an array.
            maxSumIncreasingSubSequence[i] = arr[i];
        }


        int maxSum = 0;
        for(int i=0;i<n;i++){
            int currSum = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    currSum = Math.max(currSum, maxSumIncreasingSubSequence[j]);
                }
            }
            maxSumIncreasingSubSequence[i] = arr[i] + currSum;
            maxSum = Math.max(maxSum, maxSumIncreasingSubSequence[i]);
        }

        // System.out.println(Arrays.toString(maxSumIncreasingSubSequence));

        return maxSum;
    }
}
