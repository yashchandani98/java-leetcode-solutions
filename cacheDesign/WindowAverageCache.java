package cacheDesign;

import java.util.*;

public class WindowAverageCache {
        public int expirationTime = 5;
        public Deque<int[]> cache = new ArrayDeque<>();

        // Add value with its timestamp
        public void addValue(int value, int timestamp) {
            cache.addLast(new int[]{value, timestamp});
        }

        public double getAverage(int timeStamp) {
            while (cache.size() > 0 && cache.getFirst()[1] < timeStamp - expirationTime) {
                cache.removeFirst();
            }

            double totalSum = 0.0;
            int count = 0;

            for (int[] val : cache) {
                count++;
                totalSum += val[0];
            }


            if (count == 0) {
                return 0.0;
            }

            return totalSum / count;
        }
    public static void main(String[] args) {
        WindowAverageCache wv = new WindowAverageCache();
        wv.addValue(5, 1);
        wv.addValue(10, 2);
        wv.addValue(15, 6);
        System.out.println("Average at time 6: " + wv.getAverage(6));
        System.out.println("Average at time 7: " + wv.getAverage(7));
    }
}
