package dp.tabulation;

public class Leetcode1395CountNumberOfTeams {

    /**
     * Tabulation Approach: In this approach we will iterate for count 2 to 3 to find number of triplets. and in the end every element's 4th index will contain the count for the triplets.
     * **/
    public int numTeamsTabulationApproach(int[] rating) {
        int count = 0;
        int len = rating.length;
        int[][] ascen = new int[len][4];
        int[][] descen = new int[len][4];

        for(int i=0; i<len;i++){
            // Set pair of group as a length = 1
            ascen[i][1] = 1;
            descen[i][1] = 1;
        }

        for(int i=2;i<=3;i++){
            for(int j=0; j<len; j++){
                for(int k=j+1;k<len;k++){
                    if(rating[j]<rating[k]){
                        // Set pair of group  as a length = i
                        ascen[j][i]+= ascen[k][i-1];
                    }
                    else if(rating[j]>rating[k]){
                        // Set pair of group  as a length = i
                        descen[j][i]+= descen[k][i-1];
                    }
                }
            }
        }

        for(int i=0; i<len;i++){
            count+=ascen[i][3];
            count+=descen[i][3];
        }
        return count;
    }

    /**
     * Count Approach: In this approach, for every element (index = j), we will do the looping for i->0 to j-1 and
     * we will compare both the elements and consider jth element as the middle element and perform the count.
     * **/
    public int numTeamsCountingApproach(int[] rating) {
        // Counting approach

        int count=0;
        int n = rating.length;

        for(int i=0; i<rating.length;i++){
            int smallerLeft = 0;
            int largerRight = 0;
            int smallerRight = 0;
            int largerLeft = 0;
            for(int j=0;j<i;j++){
                if(rating[i]>rating[j]){
                    // Check for increasing ratings
                    smallerLeft++;
                } else if(rating[i]<rating[j]){
                    // Check for Decreasing ratings
                    largerLeft++;
                }
            }
            for(int j=i+1;j<n;j++){
                if(rating[i]<rating[j]){
                    largerRight++;
                } else if(rating[i]>rating[j]){
                    smallerRight++;
                }
            }

            count+=smallerLeft*largerRight;//increasing
            count+=largerLeft*smallerRight;//decreasing
        }
        return count;
    }
}
