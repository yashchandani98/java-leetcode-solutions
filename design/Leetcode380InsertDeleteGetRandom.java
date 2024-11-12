package design;

import java.util.*;

public class Leetcode380InsertDeleteGetRandom {
    Map<Integer, Integer> valToNumber;
    List<Integer> numberToVal;

    public Leetcode380InsertDeleteGetRandom() {
        valToNumber = new HashMap<>();
        numberToVal = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(valToNumber.containsKey(val)) return false;

        numberToVal.add(val);

        valToNumber.put(val, numberToVal.size()-1);

        return true;
    }

    public boolean remove(int val) {
        if(!valToNumber.containsKey(val)) return false;
        int idx = valToNumber.get(val);
        int lastElement = numberToVal.get(numberToVal.size()-1);


        numberToVal.set(idx, lastElement);
        valToNumber.put(lastElement, idx);


        numberToVal.remove(numberToVal.size()-1);
        valToNumber.remove(val);


        return true;
    }

    public int getRandom() {
        int r = (int) (Math.random() * (numberToVal.size()) );

        return numberToVal.get(r);
    }
}
