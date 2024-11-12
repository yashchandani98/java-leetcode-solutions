package Graph;

import java.util.*;
public class Leetcode210CourseScheduleII {

    /**
     * This problem uses the combination of Topological Sort using DFS and Detect Cycle in a Directed Graph using DFS.
     * Just make sure while creating an adjacency list, use the key as the course number which is the prerequisite because if use intuition, prerequisite course should come before
     * completed course. Make a dfs call to every course if not visited and add the course in the stack if dfs calls are made to it's completed course.
     * ex: 0(prerequisites for) -> [1,2,3]  then the dfs call should be made to 1,2,3. Stack looks like |0| <- top of the stack  => res   [0,3,2,1]
     *                                                                                                  |3|
     *                                                                                                  |2|
     *                                                                                                  |1|
     * **/
    Stack<Integer> st = new Stack<>();
    Set<Integer> visited = new HashSet<>();
    Set<Integer> pathVisited = new HashSet<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        createAdjacencyList(prerequisites);

        for(int i=0; i<numCourses; i++) {
            if(!visited.contains(i)) {
                if(!dfs(i)){
                    return new int[0];
                }
            }
        }

        int idx = 0;
        while(!st.isEmpty()) {
            result[idx++] = st.pop();
        }

        return result;
    }

    private boolean dfs(int node){
        if(pathVisited.contains(node)) return false;

        if(visited.contains(node)) return true;

        pathVisited.add(node);
        visited.add(node);

        List<Integer> prereq = graph.getOrDefault(node, new ArrayList<>());

        for(int course: prereq) {
            boolean isPossible = dfs(course);
            if(!isPossible) {
                return false;
            }
        }
        pathVisited.remove(node);
        System.out.println(node);
        st.push(node);
        return true;
    }

    private void createAdjacencyList(int[][] prerequisites){
        for(int[] prereq: prerequisites) {
            graph.computeIfAbsent(prereq[1], k->new ArrayList<>()).add(prereq[0]);
        }
    }
}
