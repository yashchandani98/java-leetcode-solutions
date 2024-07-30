package Recursion;
import java.util.*;


/*
*
* Here we will use backtracking and recursion approach to traverse all the possible ways. But make sure here to use Lexicographically order while following the path and once a path is visited
* should never be visited again and the cell whose value is 0 should not be visited as there is no path following there.
* */
public class GeeksForGeeksRateInMaze {
    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> result = new ArrayList<>();
        int[][] visited = new int[n][n];
        String currpath = "";
        dfs(0, 0, m, n, currpath, result, visited);
        return result;
    }

    private static void dfs(int row, int col, int[][] m, int n, String currPath, ArrayList<String> result, int[][] visited){
        if(row>=n || col>=n || row<0 || col<0){
            return;
        }
        else if(row == n-1 && col == n-1 && m[row][col] == 1){
            result.add(new String(currPath));
            return;
        }
        else if(m[row][col] == 0){
            return;
        }
        if(row+1<n && m[row+1][col] == 1 && visited[row+1][col] ==0 ){
            visited[row][col] = 1;
            dfs(row+1, col, m, n, currPath+"D", result, visited);
            visited[row][col] = 0;
        }
        if(col-1>=0 && m[row][col-1] == 1 && visited[row][col-1] ==0 ){
            visited[row][col] = 1;
            dfs(row, col-1, m, n, currPath+"L", result, visited);
            visited[row][col] = 0;
        }
        if(col+1<n && m[row][col+1] == 1 && visited[row][col+1] ==0 ){
            visited[row][col] = 1;
            dfs(row, col+1, m, n, currPath+"R", result, visited);
            visited[row][col] = 0;
        }
        if(row-1>=0 && m[row-1][col] == 1 && visited[row-1][col] ==0 ){
            visited[row][col] = 1;
            dfs(row-1, col, m, n, currPath+"U", result, visited);
            visited[row][col] = 0;
        }
    }
}
