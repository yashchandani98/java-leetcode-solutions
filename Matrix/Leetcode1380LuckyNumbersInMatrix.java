package Matrix;

import java.util.*;

/*
* Here we are using extra memory to store min of each row and max of each column in two separate array and will return an element which is present in both the array.
* TC: O(N*M)
* SC: O(N+M)
*
* */
public class Leetcode1380LuckyNumbersInMatrix {

    public List<Integer> luckyNumbers (int[][] matrix) {
        int[] minElements = new int[matrix.length];
        int[] maxElements = new int[matrix[0].length];

        for(int i=0; i<matrix.length; i++){
            int minElement = Integer.MAX_VALUE;
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j]<minElement){
                    minElement = matrix[i][j];
                }
                if(matrix[i][j]>maxElements[j]){
                    maxElements[j] = matrix[i][j];
                }
            }
            minElements[i] = minElement;
        }

        Integer[] integerArraysMinElements = Arrays.stream(minElements).boxed().toArray(Integer[]::new);
        Integer[] integerArraysMaxElements = Arrays.stream(maxElements).boxed().toArray(Integer[]::new);

        Set<Integer> s1 = new HashSet<>(Arrays.asList(integerArraysMinElements));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(integerArraysMaxElements));

        s1.retainAll(s2);
        List<Integer> result = new ArrayList<>(s1);
        return new ArrayList<>(result);

    }
}
