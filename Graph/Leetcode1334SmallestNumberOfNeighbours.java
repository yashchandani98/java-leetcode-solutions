package Graph;
import java.util.*;


/*
*
* Approach: Build the graph, use Dijkstra's algorithm to find the number of reachable neighbors for each city, and then determine the city with the smallest number of
* reachable neighbors.
Data Structures: Adjacency list for graph representation, priority queue for efficient shortest path calculation, and distance array to store shortest path distances.
Algorithm: Dijkstra's algorithm for shortest path calculation, combined with an iteration to select the city with the minimum number of neighbors within the distance threshold.
*
* Graph Construction: 𝑂(𝐸)
* Dijkstra’s Algorithm (per city): O(nlogn+E)
* Total Time Complexity: O(n⋅(nlogn+E))
* */
public class Leetcode1334SmallestNumberOfNeighbours {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        Map<Integer, List<int[]>> adjacencyList = new HashMap<>();

        for(int[] edge: edges){
            int fromCity = edge[0];
            int toCity = edge[1];
            int distance = edge[2];
            List<int[]> value;
            if(adjacencyList.keySet().contains(fromCity)){
                value = adjacencyList.get(fromCity);
            } else{
                value = new ArrayList<>();
            }
            value.add(new int[]{distance, toCity});
            adjacencyList.put(fromCity, value);

            // Swapping to and from


            if(adjacencyList.keySet().contains(toCity)){
                value = adjacencyList.get(toCity);
            } else{
                value = new ArrayList<>();
            }
            value.add(new int[]{distance, fromCity});
            adjacencyList.put(toCity, value);

        }

        int res = Integer.MIN_VALUE;
        int neighboursCount = Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            int currCount = calculate(i, adjacencyList, distanceThreshold, n);
            System.out.println("Count for "+i+" is "+currCount);
            if(currCount<=neighboursCount){
                res = Math.max(res, i);
                neighboursCount = Math.min(neighboursCount, currCount);
            }
        }

        return res;

    }

    // Returns city's neighbours count
    private int calculate(int city, Map<Integer, List<int[]>> adjacencyList, int distanceThreshold, int n){
        // Create minHeap with an int[] where 0th index will contain distance from current city and
        // 1st index will contain city label.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        // Distance array to keep the track of neighbour cities of currenct city
        int[] distances = new int[n];
        // Initialize to max value to get the minimum distance
        for(int i=0; i<n;i++){
            distances[i] = Integer.MAX_VALUE;
        }

        distances[city] = 0;


        minHeap.offer(new int[] {0, city});

        while(minHeap.size()>0){
            int[] minCityDistance = minHeap.poll();
            int currDistance = minCityDistance[0];
            List<int[]> adjacentCities = adjacencyList.get(minCityDistance[1]);
            if (currDistance > distanceThreshold) break;
            if(adjacentCities!=null)
                for(int[] cities: adjacentCities){
                    int nextCity = cities[1];
                    int edgeDistance = cities[0];
                    if(currDistance+edgeDistance<=distances[nextCity]){
                        distances[nextCity] = Math.min(distances[nextCity], currDistance+edgeDistance);
                        minHeap.offer(new int[] {currDistance+edgeDistance, nextCity});
                    }
                }
        }

        int neighbourCount = 0;

        for(int i=0;i<n;i++){
            if(i!=city && distances[i]>0 && distances[i]<=distanceThreshold){
                neighbourCount++;
            }
        }
        return neighbourCount;
    }
}
