package Stack;

import java.util.*;
public class Leetcode1598CrawlerLogFolder {
    public int minOperationsUsingStack(String[] logs) {
        // We will use stack to track all the folders visited over parent or main folder
        Stack<String> fileStructure = new Stack<String>();
        // Actions related to file structure defined here
        String shiftParent = "../";
        String remainSame = "./";

        // Iterate over all the actions in the logs
        for(String action: logs){
            if(action.equals(shiftParent) && fileStructure.size()>0){
                fileStructure.pop();
            } else if(!action.equals(remainSame) && !action.equals(shiftParent)){
                fileStructure.push(action);
            }
        }
        // In the end we will see how many folders over main folder are present and return the result
        return fileStructure.size();
    }
    public int minOperationsUsingCount(String[] logs) {
        // Actions related to file structure defined here
        String shiftParent = "../";
        String remainSame = "./";
        // We will use count variable to decide how many folders we have visited over main folder
        int count = 0;

        // Iterate over all the actions in the logs
        for(String action: logs){
            if(action.equals(shiftParent) && count>0){
                count--;
            } else if(!action.equals(remainSame) && !action.equals(shiftParent)){
                count++;
            }
        }
        // In the end we will see how many folders over main folder are present and return the result
        return count;
    }
}
