package Recursion;

import java.util.*;
public class Leetcode386LexicoGraphicalNumbers {

    /**
     * Recursive approach: TC: O(n), SC: O(n) (Recursion stack) (height of a recursion tree)
     * Iterative approach: TC: O(n), SC: O(1)
     * */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();

        for(int i=1; i<=9; i++){
            dfs(i, result, n);
        }

        return result;
    }

    private void dfs(int currNum, List<Integer> result, int n){
        if(currNum>n){
            return;
        }

        result.add(currNum);

        for(int i=0; i<=9; i++){
            int newNum = currNum * 10 + i;
            if(newNum>n) break;
            dfs(newNum, result, n);
        }
    }


    public List<Integer> lexicalOrderIterativeApproach(int n) {
        List<Integer> result = new ArrayList<>();
        int num = 1;
        for(int i=0; i<n; i++){
            result.add(num);
            if(num*10<=n){
                num = num * 10;
            } else {
                while(num%10 == 9 || num==n){
                    num = num/10;
                }
                num++;
            }
        }
        return result;
    }
}
