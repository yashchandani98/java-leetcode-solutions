package String;

import java.util.*;

public class ReverseWordsInAString {
    public static void main(String[] args) {
        String sentence = "Hello , My name is yash Chandani ";
        StringBuilder sentence2 = new StringBuilder(sentence);
        // sentence.trim();
        String[] split = sentence.split(" ");
        Queue<Integer> lengths = new LinkedList<>();
        for(String str: split) {
            lengths.add(str.length());
        }

        for(int i=0; i<sentence.length(); i++){
            if(sentence.charAt(i) == ' ') continue;
            int len = lengths.poll();

            int startidx = i;
            int endidx = i+len-1;
            while(startidx<endidx){
                char temp = sentence2.charAt(endidx);
                sentence2.setCharAt(endidx, sentence2.charAt(startidx));
                sentence2.setCharAt(startidx, temp);
                startidx++;
                endidx--;
            }
            i = i+len-1;
        }

        System.out.println(sentence2.toString());
    }
}
