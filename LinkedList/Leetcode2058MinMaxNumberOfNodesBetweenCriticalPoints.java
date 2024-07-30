package LinkedList;

import java.util.*;

/*
* Traverse the linkedlist and keep on comparing prev and next value and add indexes in an arrayList if condition suffice. In the end traverse
* an array to get minimum. Remember since array node numbers are in sirted one, minimum distance could be find between adjacent node numbers
* and max distance could be always the difference between critical points of lastNode number and first Node number in an array.
* */

public class Leetcode2058MinMaxNumberOfNodesBetweenCriticalPoints {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        List<Integer> criticalPoints = new ArrayList<>();
        int[] res = {-1, -1};
        int prev = 0, next = 0;
        ListNode temp = head;
        int currNode = 1;
        while(temp!=null){
            if(prev == 0){
                prev = temp.val;
            } else if(next == 0 && temp.next!=null){
                next = temp.next.val;
            }
            if(prev!=0 && next!=0 && temp.next!=null){
                // Check for local minima
                if(temp.val < prev && temp.val<next){
                    criticalPoints.add(currNode);
                }
                // Check for local maxima
                else if(temp.val > prev && temp.val > next){
                    criticalPoints.add(currNode);
                }
            }
            next = 0;
            prev = temp.val;
            temp = temp.next;
            currNode++;
        }
        int minDistance = Integer.MAX_VALUE;
        for(int nodeidx=0;nodeidx<criticalPoints.size();nodeidx++){
            if(nodeidx<criticalPoints.size()-1){
                minDistance = Math.min(minDistance, criticalPoints.get(nodeidx+1) - criticalPoints.get(nodeidx));
            }
        }
        if(criticalPoints.size()>1){
            res[0] = minDistance;
            res[1] = criticalPoints.get(criticalPoints.size()-1) - criticalPoints.get(0);
        }
        return res;
    }
}
