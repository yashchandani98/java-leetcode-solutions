package Combinations;

import java.util.*;


/*
* Use stack and HashMap here, if an atom is independedent i.e it is not enclosed within the braces, it means it must have an element value equivalent to it's next
* digit. If an element is enclosed in the braces, add it in the stack and as soon as you encounter ) multiply all the element values with the next occuring digit. and add it
* back in the stack. in the end pop all the elements of the stack with it's weight and add it in the hashMap. sort a hashMap(here we have used TreeMap which sorts according to the keys)
*
* */
public class Leetcode726NumberofAtoms {

    private static class AtomsCount {
        private String ch;
        private int count;
        public AtomsCount(String ch, int count){
            this.ch = ch;
            this.count = count;
        }
    }

    public String countOfAtoms(String formula) {
        Stack<AtomsCount> st = new Stack<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        int idx = 0;
        for(Character ch:formula.toCharArray()){
            boolean isSingleAtom = Character.isUpperCase(ch) && (
                    (idx < formula.length()-1 && !Character.isLowerCase(formula.charAt(idx+1)))  ||
                            (idx == formula.length() - 1 && Character.isUpperCase(ch))
            ) ;
            boolean isMultiAtom = Character.isLetter(ch) && !Character.isLowerCase(ch);
            if(ch=='('){
                st.add(new AtomsCount(String.valueOf(ch), 1));
            } else if(ch==')'){
                int multiplier = 1;
                if(idx< formula.length() - 1 && Character.isDigit(formula.charAt(idx+1))){
                    int tempIdx = idx+1;
                    multiplier = 0;
                    System.out.println("Multiplier of idx:"+idx+multiplier);
                    while(tempIdx<formula.length() && Character.isDigit(formula.charAt(tempIdx))){
                        multiplier = (multiplier*10)+Character.getNumericValue(formula.charAt(tempIdx));
                        System.out.println("Multiplier:"+multiplier);
                        tempIdx++;
                    }
                }
                Stack<AtomsCount> helperStack = new Stack<>();
                while (!st.isEmpty() && !st.peek().ch.equals("(")) {
                    AtomsCount atom = st.pop();
                    atom.count*=multiplier;
                    helperStack.add(atom);
                }
                if(!st.isEmpty() && st.peek().ch.equals("(")){
                    st.pop();
                }
                while(!helperStack.isEmpty()){
                    st.push(helperStack.pop());
                }
            } else if(isSingleAtom) { // Single atom case
                if(st.isEmpty()){
                    if(idx<formula.length()-1 && Character.isDigit(formula.charAt(idx+1))){
                        int tempIdx = idx+1;
                        int value = 0;
                        while(tempIdx<formula.length() && Character.isDigit(formula.charAt(tempIdx))){
                            value = (value*10)+Character.getNumericValue(formula.charAt(tempIdx));
                            tempIdx++;
                        }
                        hashMap.put(String.valueOf(ch), hashMap.getOrDefault(String.valueOf(ch), 0)+value);
                    } else {
                        hashMap.put(String.valueOf(ch), hashMap.getOrDefault(String.valueOf(ch), 0)+1);
                    }
                } else{
                    if(idx < formula.length()-1 && Character.isDigit(formula.charAt(idx+1))){
                        int tempIdx = idx+1;
                        int value = 0;
                        while(tempIdx<formula.length() && Character.isDigit(formula.charAt(tempIdx))){
                            value = (value*10)+Character.getNumericValue(formula.charAt(tempIdx));
                            tempIdx++;
                        }
                        st.push(new AtomsCount(String.valueOf(ch), value));
                    } else{
                        st.push(new AtomsCount(String.valueOf(ch), 1));
                    }
                }
            } else if(isMultiAtom){ // Multi atom case
                String str = String.valueOf(ch) + String.valueOf(formula.charAt(idx+1));
                if(st.isEmpty()){
                    if(idx<formula.length()-2 && Character.isDigit(formula.charAt(idx+2))){
                        int tempIdx = idx+2;
                        int value = 0;
                        while(tempIdx<formula.length() && Character.isDigit(formula.charAt(tempIdx))){
                            value = (value*10)+Character.getNumericValue(formula.charAt(tempIdx));
                            tempIdx++;
                        }
                        hashMap.put(str, hashMap.getOrDefault(str, 0)+value);
                    } else {
                        hashMap.put(str, hashMap.getOrDefault(str, 0)+1);
                    }
                } else{
                    if(idx<formula.length()-2 && Character.isDigit(formula.charAt(idx+2))){
                        int tempIdx = idx+2;
                        int value = 0;
                        while(tempIdx<formula.length() && Character.isDigit(formula.charAt(tempIdx))){
                            value = (value*10)+Character.getNumericValue(formula.charAt(tempIdx));
                            tempIdx++;
                        }
                        st.push(new AtomsCount(str, value));
                    } else{
                        st.push(new AtomsCount(str, 1));
                    }
                }
            }

            idx++;
        }

        while(!st.isEmpty()){
            AtomsCount at = st.pop();
            hashMap.put(at.ch, hashMap.getOrDefault(at.ch, 0)+at.count);
        }

        Map<String, Integer> map = new TreeMap<>(hashMap);

        String result = "";

        for(Map.Entry<String, Integer> mp: map.entrySet() ){
            if(mp.getValue() == 1){
                result+=mp.getKey();
            } else{
                result+=mp.getKey();
                result+=mp.getValue();
            }
        }
        return result;
    }
}
