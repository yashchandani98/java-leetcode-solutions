package SlidingWindowAndTwoPointer;

import java.util.*;
public class Leetcode241DifferentWaysToAddParentheses {
    /**
     * Declare HashSet of the possible operators in the expression. Use Two pointer left and right pointer technique and recursively evaluate all the expressions. Once we encounter an expression,
     * recursively call the backtrack(left_ptr, iter-1) and all the further evaluated expressions would be in array and array is returned, also call the
     * backtrack(iter+1, right_ptr) and all the further evaluated expressions would be in an array and array is returned, Now we have to
     * perform operation which we encountered in an expression for all the numbers in both of an array and reurn the final array. Remember in the last,
     * if array is empty, that means the expresssion from  left_ptr to right_ptr would be a integer value whcih we should return in an array i.e. the base case.
     *
     * TC: O(n*2^n)
     * SC: O(n+m) => For recursive stack: n is a expression length, m => returned result in each recursive call
     * */
    Set<Character> operations = new HashSet<>(Arrays.asList('+', '-', '*'));

    public List<Integer> diffWaysToCompute(String expression) {
        // Two pointer and recursive approach
        return backtrack(0, expression.length()-1, expression);
    }

    private List<Integer> backtrack(int left_ptr, int right_ptr, String expression) {
        List<Integer> result = new ArrayList<>();

        for(int i=left_ptr; i<=right_ptr; i++){
            Character ch = expression.charAt(i);
            if(operations.contains(ch)){
                List<Integer> arr1 = backtrack(left_ptr, i-1, expression);
                List<Integer> arr2 = backtrack(i+1, right_ptr, expression);
                for(int num1: arr1){
                    for(int num2: arr2){
                        switch(ch){
                            case '+':
                                result.add(num1+num2);
                                break;
                            case '-':
                                result.add(num1-num2);
                                break;
                            case '*':
                                result.add(num1*num2);
                                break;
                        }
                    }
                }
            }
        }
        if(result.isEmpty()){
            // Base case in the recursion tree where the expression is a integer number because if it would have contained expression, the result array wouldn't be empty.
            result.add(Integer.parseInt(expression.substring(left_ptr, right_ptr+1)));
        }
        return result;
    }
}
