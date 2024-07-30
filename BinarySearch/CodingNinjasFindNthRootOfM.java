package BinarySearch;

/*
Link: https://www.naukri.com/code360/problems/1062679?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTabValue=PROBLEM
Problem: You are given two positive integers 'n' and 'm'. You have to return the 'nth' root of 'm', i.e. 'm(1/n)'. If the 'nth root is not an integer, return -1.

* Brute force approach: Iterate from startNumber =1 until endNumber=m/n to see if any number is satisfying the criteria, if yes, return the result. By default return -1
* TC: O(n)
*
* Optimal approach: Use Binary search between start and endNumber and look for every possibility. startNumber =1 until endNumber=m/n
* TC: O(logn)
*
* */
public class CodingNinjasFindNthRootOfM {
    public static int NthRoot(int n, int m) {
        int startNumber = 1, endNumber = m/n;
        int mid = (endNumber+startNumber)/2;

        int res = -1;

        while(startNumber<=endNumber){
            if(Math.pow(mid, n) == m)
                return mid;
            else if(Math.pow(mid, n)<m)
                startNumber = mid+1;
            else
                endNumber = mid-1;
            mid = (endNumber+startNumber)/2;
        }
        return res;
    }
}
