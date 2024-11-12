package Arrays;
import java.util.*;

/*
* Formulae: In pascal triangle element[i][j] = element[i-1][j-1] + element[i-1][j]; where 0<j<n-1 and 1<i<n rest elements should be 1.
* TC: O(n^2)
* SC: O(1)
* */
public class Leetcode118PascalsTraingle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<numRows; i++){
            List<Integer> ans = new ArrayList<>();
            for(int k = 0; k<= i; k++){
                if(k==0 || k == i){
                    ans.add(k, 1);
                } else{
                    List<Integer> lastRow = res.get(i-1);
                    ans.add(
                            lastRow.get(k)+lastRow.get(k-1)
                    );
                }
            }
            res.add(ans);
        }
        return res;
    }
}
