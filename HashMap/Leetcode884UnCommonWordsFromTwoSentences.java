package HashMap;

import java.util.*;

public class Leetcode884UnCommonWordsFromTwoSentences {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> occurence = new HashMap<>();

        // First we will split string1 by space delimiter and store count of each word
        for(String word : s1.split(" ")) {
            if(occurence.keySet().contains(word)){
                occurence.put(word, occurence.get(word)+1);
            } else {
                occurence.put(word, 1);
            }
        }

        // Then we will split string2 by space delimiter and store count of each word

        for(String word : s2.split(" ")) {
            if(occurence.keySet().contains(word)){
                occurence.put(word, occurence.get(word)+1);
            } else {
                occurence.put(word, 1);
            }
        }

        List<String> result = new ArrayList<>();

        for(Map.Entry<String, Integer> entrySet : occurence.entrySet()){
            if(entrySet.getValue() == 1){
                result.add(entrySet.getKey());
            }
        }

        String[] stringArray = new String[result.size()];
        stringArray = result.toArray(stringArray);
        return stringArray;
    }
}
