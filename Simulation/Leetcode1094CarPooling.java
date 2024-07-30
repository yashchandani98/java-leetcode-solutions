package Simulation;

import java.util.*;

/*
* Intuition: We will have to check for each startDistance:
* - First if the passengers are seated in the car(if any) should have been vacated earlier before startDistance or need to be vacated at current distance, if yes vacant it
* and accomodate new passengers and check if they can accomodate and proceed ahead.
* Approach: Use two minHeaps, one would be sorted based upon starting distance (which we will iterate) and other one which would be sorted based upon endDistance
* (to check if the passengers are about to or should have been vacated the seats earlier)
*
* */
public class Leetcode1094CarPooling {
    private static class Trip{
        /**
         These distances are preasumed to be in the east's direction and the distance is from car's initial position
         */
        private int countOfPassengers;
        private int startDistance;
        private int endDistance;

        /**
         Initialization of class level props
         */
        public Trip(int countOfPassengers, int startDistance, int endDistance){
            this.countOfPassengers=countOfPassengers;
            this.startDistance=startDistance;
            this.endDistance=endDistance;
        }
    }
    public boolean carPooling(int[][] trips, int capacity) {
        if(trips.length == 1){
            if(trips[0][0]<=capacity)
                return true;
            return false;
        }
        // Initially initialize res by false
        boolean res = false;
        // Initially since car would be empty, so initializing by the available capacity
        int currCapcaity = capacity;
        PriorityQueue<Trip> startTrips = new PriorityQueue<>((Trip a, Trip b)->a.startDistance-b.startDistance);
        PriorityQueue<Trip> endTrips = new PriorityQueue<>((Trip a, Trip b)->a.endDistance-b.endDistance);
        // Adding the start and end trips in two separate minHeap
        for(int[] trip: trips){
            startTrips.add(new Trip(trip[0], trip[1], trip[2]));
            endTrips.add(new Trip(trip[0], trip[1], trip[2]));
        }
        // NPE Safe check for minHeap
        while(!startTrips.isEmpty()){
            // Picking the start trip which is at least distance from car's initial position
            Trip tripStart = startTrips.poll();
            // Vaccant all the seats if the endDistance of the trips is ending or ended before currentStartDistance
            while(endTrips.peek().endDistance<=tripStart.startDistance){
                Trip tripEnd = endTrips.poll();
                // Increase currCapacity if seats got vaccant
                currCapcaity+=tripEnd.countOfPassengers;
            }
            // If there is no such place to vaccant passengers, break the while loop and return result as false
            if(tripStart.countOfPassengers>currCapcaity){
                res = false;
                break;
            } else{
                res = true;
            }
            // Reduce currCapacity if the passengers have occupied the seats
            currCapcaity-=tripStart.countOfPassengers;
        }
        return res;
    }
}
