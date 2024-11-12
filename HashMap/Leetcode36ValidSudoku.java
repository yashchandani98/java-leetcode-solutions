package HashMap;


import java.util.*;
public class Leetcode36ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> colHashSet = new HashMap<>();
        Map<Integer, Set<Character>> rowHashSet = new HashMap<>();
        Map<String, Set<Character>> gridHashSet = new HashMap<>();

        int r = board.length, c = board[0].length;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                char ch = board[i][j];
                if(ch == '.') continue;

                if(colHashSet.computeIfAbsent(j, k -> new HashSet<>()).contains(ch)) return false;

                if(rowHashSet.computeIfAbsent(i, k -> new HashSet<>()).contains(ch)) return false;

                String gridKey = i/3 + "-" + j/3;
                if(gridHashSet.computeIfAbsent(gridKey, k -> new HashSet<>()).contains(ch)) return false;

                colHashSet.get(j).add(ch);

                rowHashSet.get(i).add(ch);

                gridHashSet.get(gridKey).add(ch);
            }
        }

        return true;

    }
}
