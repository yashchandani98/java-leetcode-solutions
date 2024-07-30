package Graph;

/**
 Intuition: Make a note that Star node will be present in every edge. So it should be present either at 0th index or 1st index of the second edge.
 */
public class Leetcode1791CentreOfAStarGraph {
    public static void main(String[] args){
        int[][] edges = {{1,2},{2,3},{4,2}};
        System.out.println(findCenter(edges));
    }
    public static int findCenter(int[][] edges) {
        if(edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        }
        return edges[0][1];
    }
}
