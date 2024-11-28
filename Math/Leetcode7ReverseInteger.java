package Math;

public class Leetcode7ReverseInteger {
    public int reverse(int x) {
        int num = x;
        long ans = 0;

        num = Math.abs(num);

        while(num>0){
            int mod = num%10;
            num /=10;
            ans = (ans*10) + mod;
        }

        if(x<0){
            ans *=-1;
        }

        System.out.println(ans>Integer.MAX_VALUE);

        return ans > Integer.MAX_VALUE  || ans < Integer.MIN_VALUE ? 0 : (int) ans;
    }
}
