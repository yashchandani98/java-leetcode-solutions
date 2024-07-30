package Recursion;

import java.util.*;

/*
*
* Solve this question using backtracking approach. We will try to first put the queen in the column one by one and then for every column, we will try to fit the queen for every row by using this condition recursively:
if (leftRow[row] == 0 && leftlowerDiagonal[row + col] == 0 && leftupperDiagonal[board.length - 1 + col - row] == 0)

Reason being, we will always check towards left side if the queen is attakcing or occupied because of the column traversal and this formulae will save us the time complexity. Every time we will try to accomodate the queen in a specific row, we will update these values and after calling function, we will revert back the changes to it's original state.

*
* */
public class Leetcode51N_Queens {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        List < List < String >> res = new ArrayList < List < String >> ();
        int leftRow[] = new int[n];
        int leftupperDiagonal[] = new int[2 * n - 1];
        int leftlowerDiagonal[] = new int[2 * n - 1];
        solve(0, board, res, leftRow, leftlowerDiagonal, leftupperDiagonal);
        return res;
    }

    private void solve(int col, char[][] board, List < List < String >> res, int leftRow[], int leftlowerDiagonal[], int leftupperDiagonal[]) {
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (leftRow[row] == 0 && leftlowerDiagonal[row + col] == 0 && leftupperDiagonal[board.length - 1 + col - row] == 0) {
                board[row][col] = 'Q';
                leftRow[row] = 1;
                leftlowerDiagonal[row + col] = 1;
                leftupperDiagonal[board.length - 1 + col - row] = 1;
                solve(col + 1, board, res, leftRow, leftlowerDiagonal, leftupperDiagonal);
                board[row][col] = '.';
                leftRow[row] = 0;
                leftlowerDiagonal[row + col] = 0;
                leftupperDiagonal[board.length - 1 + col - row] = 0;
            }
        }
    }

    private List < String > construct(char[][] board) {
        List < String > res = new ArrayList < String > ();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}
