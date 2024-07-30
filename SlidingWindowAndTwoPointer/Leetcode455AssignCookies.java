package SlidingWindowAndTwoPointer;

import java.util.*;
public class Leetcode455AssignCookies {
    // use two pointer approach for matching greed factor and cookie size
    public int findContentChildren(int[] g, int[] s) {
        // We will sort the greed factor of students and available cookie size to accomodate the students the smalles t size cookie so that the students with more greed factor can get the cookie size equivalent to their greed.
        Arrays.sort(g);
        Arrays.sort(s);

        int cookie_index = 0;
        int count = 0;

        for(int greed: g){
            int temp = cookie_index;
            // We will keep on matching available cookie size which will be most suitable according to the child's greed factoe
            while(cookie_index<s.length && greed>s[cookie_index])
                cookie_index++;
            if(cookie_index<s.length && greed<=s[cookie_index]){
                // If the child greed factor has been fullfilled, reduce the cookie sixe to value =-1 stating that cookies has been fed to the child and increase the count
                count++;
                s[cookie_index]= -1;
            } else{
                // If the greed factor is not fullfilled, assign the pointer to it's original position
                cookie_index = temp;
            }
        }
        return count;
    }
}
