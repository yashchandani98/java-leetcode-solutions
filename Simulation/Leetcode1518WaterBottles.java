package Simulation;

public class Leetcode1518WaterBottles {
    public int numWaterBottles(int numBottles, int numExchange) {
        int res = numBottles;
        int currentFullBottles = numBottles;
        while(currentFullBottles>=numExchange){
            res+=currentFullBottles/numExchange;
            currentFullBottles = (currentFullBottles/numExchange) + (currentFullBottles % numExchange);
        }
        return res;
    }
}
