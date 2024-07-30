package Graph;

public class Leetcode2045SecondMinimumTimeToReachDestination {
    /** Video solution: https://www.youtube.com/watch?v=2F7gwxfy1CU&ab_channel=NeetCodeIO
     * Intuition:
     *
     * We will Perform BFS to visit every node layer by layer. and once layer is visited increment the time. There is a catch, we have to increment the curr_time by the
     * required time to visit the node iff the signal is green or else if it is red, we have to increment the curr_time by the waiting time for signal to be green first
     * then increment it by the required time. We can visit every node twice
     * Python code:
     * def secondMinimum(self, n: int, edges: List[List[int]], time: int, change: int) -> int:
     *         adjacencymatrix = defaultdict(list)
     *
     *         for v1, v2 in edges:
     *             adjacencymatrix[v1].append(v2)
     *             adjacencymatrix[v2].append(v1)
     *
     *         dq = deque([1])
     *         res = -1
     *         curr_time = 0
     *         visited = defaultdict(list)
     *         while dq:
     *             for i in range(len(dq)):
     *                 vertex = dq.popleft()
     *                 if(vertex == n):
     *                     if res!=-1:
     *                         res = curr_time
     *                         return res
     *                     res = curr_time
     *                 for neighbour in adjacencymatrix[vertex]:
     *                     vis = visited[neighbour]
     *                     if len(vis) == 0 or (len(vis) == 1 and vis[0] != curr_time):
     *                         visited[neighbour].append(curr_time)
     *                         dq.append(neighbour)
     *             if (curr_time//change) % 2 == 1:
     *                 curr_time += change - (curr_time%change)
     *             curr_time+=time
     *         return -1
     *
     *
     *
     * */
}
