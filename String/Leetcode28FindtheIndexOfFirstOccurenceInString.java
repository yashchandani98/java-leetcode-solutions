package String;

public class Leetcode28FindtheIndexOfFirstOccurenceInString {
    /**
     * 2 pointer approach. For every index in hayStack, check if needle exists in haystack startingfrom that particular index.
     * TC: O(n)^2 or O(n * min(str1_len, str2_len))
     * */
    public int strStr(String haystack, String needle) {
        if(haystack.length() < needle.length()) return -1;
        int ptr1 = 0, ptr2 = 0;
        for(;ptr1<haystack.length();ptr1++){
            int temp = ptr1;
            while(temp< haystack.length() && ptr2<needle.length() && needle.charAt(ptr2) == haystack.charAt(temp)){
                temp++;
                ptr2++;
            }
            if(ptr2 == needle.length())
                return ptr1;
            ptr2=0;
        }
        return -1;
    }
}
