package Graph;

import java.util.*;
public class Leetcode207CourseSchedule {

    /***
     * Use Kahn's Algorithm
     * */
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // DFS Approach
        // 1 -> [0,2]
        // 2 -> [3,4]

        Set<Integer> completed = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> que = new LinkedList<>();
        int[] indegree = buildGraph(prerequisites, numCourses);

        for(int i=0; i<numCourses; i++) {
            if(indegree[i] == 0) {
                que.offer(i);
            }
        }
        while(!que.isEmpty()) {
            int course = que.poll();
            completed.add(course);

            List<Integer> prerequisitesCourse = graph.get(course);
            if(prerequisitesCourse != null) {

                for(int prerequisite: prerequisitesCourse) {
                    indegree[prerequisite]--;
                    if (indegree[prerequisite] == 0) {
                        que.offer(prerequisite);
                    }
                }
            }
        }
        return completed.size() == numCourses;
    }

    private int[] buildGraph(int[][] prerequisites, int numCourses) {
        int[] indegree = new int[numCourses];
        for(int[] edge : prerequisites) {
            int from = edge[0];
            int to = edge[1];
            graph.computeIfAbsent(from, k-> new ArrayList<>()).add(to);
            indegree[to]++;
        }
        return indegree;
    }
}
