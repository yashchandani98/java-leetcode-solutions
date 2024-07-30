package Greedy;


import java.util.PriorityQueue;

/*
*
*
* Link: https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1

Question: There is one meeting room in a firm. There are n meetings in the form of (start[i], end[i]) where start[i] is start time of meeting i and end[i] is finish time of meeting i.
What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can be held in the meeting room at a particular time? Return the maximum number of meetings that can be held in the meeting room.
Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
Ex:
Input: n = 6, start[] = {1,3,0,5,8,5}, end[] =  {2,4,6,7,9,9}
Output: 4
Explanation: Maximum four meetings can be held with given start and end timings. The meetings are - (1, 2),(3, 4), (5,7) and (8,9)
*
* Intuition: Here we will be greedy and willl create that ds which can be sorted according to least endTime. Reason being, We will
* be greedy here and will pick all those meetings whose end time is smaller. He nce in this way we can occupied as much meetings as possible

*
*
*
* */
public class GeeksForGeeksNMeetingsInOneRoom {

    private static class Meeting{
        private int start;
        private int end;
        private int pos;
        private Meeting(int start, int end, int pos){
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
    }
    public static void main(String[] args){
        int[] start = {1,3,0,5,8,5};
        int[] end = {3,4,6,7,9,9};
        System.out.println(maximizeMeetings(start, end, 6));
    }

    private static int maximizeMeetings(int[] start, int[] end, int n){
        PriorityQueue<Meeting> pq = new PriorityQueue<>((Meeting a,Meeting b)-> a.end - b.end );
        for(int idx=0;idx<n;idx++){
            pq.add(new Meeting(start[idx], end[idx], idx+1));
        }
        int res = 1;
        int endTime = pq.poll().end;
        while(!pq.isEmpty()){
            Meeting nextPossibleMeeting = pq.poll();
            if(endTime<nextPossibleMeeting.start){
                endTime = nextPossibleMeeting.end;
                res++;
            }
        }
        return res;
    }
}
