package Matrix;

public class Leetcode130SurroundedRegions {
        public void solve(char[][] board) {
            int m = board.length;
            int n = board[0].length;

            boolean[][] visited = new boolean[m][n];

            for(int i=0; i<m; i++) {
                dfs(i, 0, board, visited);
                dfs(i, n-1, board, visited);
            }

            for(int i=0; i<n; i++) {
                dfs(0, i, board, visited);
                dfs(m-1, i, board, visited);
            }



            for(int i=1; i<m-1; i++) {
                for(int j=1; j<n-1; j++) {
                    if(board[i][j] == 'O' && !visited[i][j]) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void dfs(int i, int j, char[][] board, boolean[][] visited) {
            int m = board.length;
            int n = board[0].length;

            if(i<0 || j<0 || i >= m || j >= n || visited[i][j] || board[i][j] == 'X') {
                return;
            }


            visited[i][j] = true;

            dfs(i+1, j, board, visited);

            dfs(i-1, j, board, visited);

            dfs(i, j+1, board, visited);

            dfs(i, j-1, board, visited);
        }
}
