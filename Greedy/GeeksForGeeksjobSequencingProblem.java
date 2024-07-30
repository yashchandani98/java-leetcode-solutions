package Greedy;


/**
 *
 * https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
 *
 *
 * Given a set of N jobs where each jobi has a deadline and profit associated with it.
 *
 * Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit associated with job if and only if the job is completed by its deadline.
 *
 * Find the number of jobs done and the maximum profit.
 *
 * Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job. Deadline of the job is the time before which job needs to be completed to earn the profit.
 *
 *
 * Example 1:
 *
 * Input:
 * N = 4
 * Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
 * Output:
 * 2 60
 * Explanation:
 * Job1 and Job3 can be done with
 * maximum profit of 60 (20+40).
 *
 *
 *Intuition:
 *
 * The intuition behind this approach is to be greedy and solve the jobs by the maximum end of the deadline to gain more profit and to solve earliest deadline
 * jobs first
 *
 * We wil create an array of the maximum deadline and will initialize by -1 which states that there is no jobs perform and as soon as
 * we encounter any job, we will try to assign in the index = (maximum of an array less than the deadline index whose value!=-1) and update
 * array index value and add profit as soon as we assign the jobs.
 *
 * we will sort the jobs according to the profit as we willl be greedy here
 *
 */

import java.util.*;
public class GeeksForGeeksjobSequencingProblem {

    private static class Job {
        int id, profit, deadline;
        Job(int x, int y, int z) {
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }
    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n)
    {
        int[] res = new int[2];
        int count = 0, profit =0;
        int maxDeadline = 0;

        Arrays.sort(arr, (a, b)->b.profit-a.profit);

        for(int i=0; i<n;i++){
            maxDeadline = Math.max(maxDeadline, arr[i].deadline);
        }

        int [] jobsIdAccordingToDeadline = new int[maxDeadline+1];
        for(int i=1; i<=maxDeadline;i++){
            jobsIdAccordingToDeadline[i] = -1;
        }
        for(int i=0; i<n;i++){
            Job job = arr[i];
            int deadline = job.deadline;

            for (int j = arr[i].deadline; j > 0; j--) {

                // Free slot found
                if (jobsIdAccordingToDeadline[j] == -1) {
                    jobsIdAccordingToDeadline[j] = i;
                    count++;
                    profit += arr[i].profit;
                    break;
                }
            }
        }
        res[0] = count;
        res[1] = profit;
        return res;
    }
}
