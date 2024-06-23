package MaxHeap;

import java.util.*;

public class Leetcode502IPO {

    private static class Project {
        int capital;
        int profit;
        Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }
    }
    public static void main(String[] args) {
        int[] profits = {1,2,3};
        int[] capital = {1,1,2};
        System.out.println(findMaximizedCapital(1, 2, profits, capital));
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        List<Project> projects = new ArrayList<>();
        int len = profits.length;
        for(int idx = 0; idx<len; idx++) {
            projects.add(new Project(capital[idx], profits[idx]));
        }

        // Here the intention is to minimise the capital invested
        Collections.sort(projects, (a,b)->a.capital-b.capital);

        // Here the intention is to maximise the profit by storing in the PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        int j = 0;
        for(int i =0; i<k; i++) {
            // Here the intention is to add all the possible profits in the pq which call can be achieved by given capital
            while(j < len && projects.get(j).capital <= w) {
                pq.add(projects.get(j).profit);
                j++;
            }

            if(pq.isEmpty()) {
                break;
            }

            w += pq.poll();
        }
        return w;
    }
}
