package SlidingWindowAndTwoPointer;

public class Leetcode125ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^A-Za-z0-9]", "");
        s = s.toLowerCase();
        int lptr = 0, rptr = s.length() - 1;
        boolean res = false;
        if(s.isEmpty() || s.length() == 1) {
            return true;
        }

        while(lptr<rptr){
            if(s.charAt(lptr) == s.charAt(rptr)){
                res = true;
                lptr++;
                rptr--;
            }else{
                res = false;
                break;
            }
        }
        return res;
    }
}
