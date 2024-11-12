package Simulation;


import java.util.*;

public class Leetcode2028FindMissingObservations {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int givenSum = 0;
        int totalRolls = rolls.length + n;
        int totalSum = mean*totalRolls;
        for(int roll: rolls) {
            givenSum+=roll;
        }

        int requiredSum = totalSum-givenSum;

        if(requiredSum > 6* n || requiredSum<n){
            return new int[0];
        }


        int[] result = new int[n];

        int avg = requiredSum/n;

        int mod = requiredSum%n;

        Arrays.fill(result, avg);

        for(int i=0;i<mod;i++){
            result[i]++;
        }
        return result;
    }
}
