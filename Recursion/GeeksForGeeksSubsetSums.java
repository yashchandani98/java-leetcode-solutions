package Recursion;

import java.util.*;

/*
*
* Recursive approach:
Intuition: We have to decide between if we have to consider an index or not and we will keep on calling recursive function and in the base case(when idx== array size), we will simply add the currSum to the result and will return the conreol flow from there. In this way it will cover all the cases.
Time complexity: O(2^N)
* */
public class GeeksForGeeksSubsetSums {
    private static ArrayList<Integer> result;
    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>();
         result = new ArrayList<Integer>();
        arr.add(2);
        arr.add(3);
        System.out.println(subsetSums(arr,2));
    }
    private static ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        solve(0,0,arr);
        return result;
    }

    private static void solve(int idx, int currSum, ArrayList<Integer> arr){
        if(idx>=arr.size()){
            result.add(currSum);
            return;
        }
        solve(idx+1, currSum + arr.get(idx), arr);
        solve(idx+1, currSum, arr);
    }
}
