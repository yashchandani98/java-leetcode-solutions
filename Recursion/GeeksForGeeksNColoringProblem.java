package Recursion;


/*

link: https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1

Given an undirected graph and an integer M. The task is to determine if the graph can be colored with at most M colors such
that no two adjacent vertices of the graph are colored with the same color. Here coloring of a graph means the assignment of
colors to all vertices. Print 1 if it is possible to colour vertices and 0 otherwise.

Input:
N = 4
M = 3
E = 5
Edges[] = {(0,1),(1,2),(2,3),(3,0),(0,2)}
Output: 1
Explanation: It is possible to colour the
given graph using 3 colours.




Approach:


Solved it using recursion and backtracking. Refer to local(Git solution). we'll try for every color for every node, if. coloring is possible, return true else return false recursively. We will have one array named as verticesColoured of length equivalent to number of vertices. If a vertice i is coloured with color j mark verticesColoured[i] = j. Then see for every adjacent node of current node if j exist in any of the adjacent nodes index in verticesColoured. if yes, it is not possible to color the vertice i with color j.

Base condition: if node count reaches the end, return true that means it is possible to color all the nodes with given m colours.
*/

public class GeeksForGeeksNColoringProblem {

    public static void main(String[] args){
        int[][] graph = new int[5][2];
        graph[0][0] = 0;
        graph[0][1] = 1;

        graph[1][0] = 1;
        graph[1][1] = 2;

        graph[2][0] = 2;
        graph[2][1] = 3;

        graph[3][0] = 3;
        graph[3][1] = 0;

        graph[4][0] = 0;
        graph[4][1] = 2;

        System.out.println(graphColoring(graph, 3, 4));
    }
    public static boolean graphColoring(int graph[][], int m, int n) {
        int[] verticesColoured = new int[n];
        if(!solve(0, graph, m, verticesColoured)){
            return false;
        }
        return true;
    }

    public static boolean canColor(int node, int[][] graph, int color, int[] verticesColoured){
        for(int adjacentNode: graph[node]){
            if (verticesColoured[adjacentNode] == color) return false;
        }
        return true;
    }

    public static boolean solve(int node, int[][] graph, int colors, int[] verticesColoured){
        if(node >= verticesColoured.length){
            return true;
        }
        for(int i=0; i< colors;i++){
            if(canColor(node, graph, i, verticesColoured)){
                verticesColoured[node] = i;
                if(solve(node+1, graph, colors, verticesColoured)) return true;
                verticesColoured[node] = 0;
            }
        }
        return false;
    }
}
