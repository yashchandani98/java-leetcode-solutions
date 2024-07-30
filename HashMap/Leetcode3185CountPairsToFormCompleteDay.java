package HashMap;

/**
 (a+b)modm = (a modm + b modm) modm
 Hence use this formulae to store count of the second number of possible pair for each hour:
 (24 - num1 % 24) % 24 = num2 % 24
 */
public class Leetcode3185CountPairsToFormCompleteDay {
    public long countCompleteDayPairs(int[] hours) {
        long res = 0;
        int[] count = new int[24];
        for(int i=0; i<hours.length; i++){
            res += count[(24-hours[i]%24)%24];
            count[hours[i]%24]++;
        }
        System.out.println(24- 24%24);
        return res;
    }
}
