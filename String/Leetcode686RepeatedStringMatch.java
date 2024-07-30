package String;

public class Leetcode686RepeatedStringMatch {
    /**
     * We will use Java contains and repeat method. We will repeat until B.length/A.length times and see if substring exists or not?
     * **/
    public int repeatedStringMatch(String a, String b) {
        if(a.contains(b)) return 1;
        int numberoftimes = (int)Math.ceil(b.length()/a.length());
        for(int i=2; i<=numberoftimes+2; i++){
            String s = a.repeat(i);
            if(s.contains(b)) return i;
        }
        return -1;
    }}
