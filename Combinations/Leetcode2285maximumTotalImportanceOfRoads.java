package Combinations;

import java.util.*;

/*
* MaxHeap, Breadth first search, HashMap
*
* */
public class Leetcode2285maximumTotalImportanceOfRoads {

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        for(Integer item: res){
            System.out.println(item);
        }

    }
    Set<Set<Integer>> visitedRoads;
    // class CitiesComparator implements Comparator<>
    class City{
        int roadsCount;
        int id;
        public City(int id, int roadsCount) {
            this.id = id;
            this.roadsCount = roadsCount;
        }
    }
    public long maximumImportance(int n, int[][] roads) {
        Map<Integer,List<Integer>> adjacencyMatrix = new HashMap<>(n);
        PriorityQueue<City> cities = new PriorityQueue<>((cityA, cityB) -> (cityB.roadsCount - cityA.roadsCount));
        int citiesConnected = 0;
        for(int[] adjacenctNode : roads){
            int nodeA = adjacenctNode[0];
            int nodeB = adjacenctNode[1];
            List<Integer> nodeAdjacencyA = adjacencyMatrix.getOrDefault(nodeA, new ArrayList<Integer>());
            nodeAdjacencyA.add(nodeB);
            List<Integer> nodeAdjacencyB = adjacencyMatrix.getOrDefault(nodeB, new ArrayList<Integer>());
            nodeAdjacencyB.add(nodeA);
            adjacencyMatrix.put(nodeA, nodeAdjacencyA);
            adjacencyMatrix.put(nodeB, nodeAdjacencyB);
        }

        for(Map.Entry<Integer, List<Integer>> items : adjacencyMatrix.entrySet()){
            cities.add(new City(items.getKey(), items.getValue().size()));
            citiesConnected++;
        }

        Map<Integer, Integer> citiesImportance = new HashMap<>(n);

        while(citiesConnected>0){
            City next = cities.poll();
            citiesImportance.put(next.id,n);
            n--;
            citiesConnected--;
        }

        long importance = 0;

        visitedRoads = new HashSet<>();

        for(Map.Entry<Integer,List<Integer>> entry : adjacencyMatrix.entrySet()){
            importance += bfs(entry.getKey(), citiesImportance, adjacencyMatrix);
        }
        return importance;
    }

    private long bfs(Integer currenctCity, Map<Integer, Integer> citiesImportance, Map<Integer,List<Integer>> adjacencyMatrix){
        long importance = 0;
        List<Integer> adjacentRoads = adjacencyMatrix.get(currenctCity);

        for(Integer adjacentCity: adjacentRoads){
            Set<Integer> adjacentCities = new HashSet<>(2);
            adjacentCities.add(adjacentCity);
            adjacentCities.add(currenctCity);
            if(!visitedRoads.contains(adjacentCities)) {
                importance+=citiesImportance.getOrDefault(currenctCity, 0);
                importance+=citiesImportance.getOrDefault(adjacentCity, 0);
            }
            visitedRoads.add(adjacentCities);
        }
        return importance;
    }
}
