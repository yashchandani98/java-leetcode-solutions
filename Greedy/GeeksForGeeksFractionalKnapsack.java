package Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}

/*
*Link: https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
*
*
* Given weights and values of n items, we need to put these items in a knapsack of capacity w to get the maximum total value in the knapsack. Return a double value representing the maximum value in knapsack.
Note: Unlike 0/1 knapsack, you are allowed to break the item here. The details of structure/class is defined in the comments above the given function.

Examples :

Input: n = 3, w = 50, value[] = [60,100,120], weight[] = [10,20,30]
Output: 240.000000
Explanation: Take the item with value 60 and weight 10, value 100 and weight 20 and split the third item with value 120 and weight 30,
* to fit it into weight 20. so it becomes (120/30)*20=80, so the total value becomes 60+100+80.0=240.0 Thus, total maximum value of item we can have
* is 240.00 from the given capacity of sack.
*
*
*
* Intuition:
* We have to be greedy here that means we have to consider those items whose profit per unit is greater than other items (value/weight) then keep on adding value in the final
* profit if item's weight is less than or equals to current knapsack capacity else add (profit per unit * available knapsack capacity)
*
* */
class SortByProfit implements Comparator<Item> {

    // Method
    // Sorting in ascending order of roll number
    public int compare(Item a, Item b) {
        double profit1 = (double) a.value/a.weight;
        double profit2 = (double) b.value/b.weight;

        return profit2>profit1 ? 1 :-1;
    }
}
public class GeeksForGeeksFractionalKnapsack {
    private static double fractionalKnapsack(int w, Item arr[], int n) {

        PriorityQueue<Item> pq = new PriorityQueue<>(new SortByProfit());
        pq.addAll(Arrays.asList(arr));
        double profit = 0;
        while (w>0 && !pq.isEmpty()){
            Item item = pq.poll();
            if(item.weight<=w){
                profit+=(double) item.value;
                w-=item.weight;
                System.out.println("!Value:"+item.value+"weight:"+item.weight);
            } else {
                System.out.println(((double)item.value/(double)item.weight)*(w));
                System.out.println("Value:"+item.value+"weight:"+item.weight);
                profit+=((double)item.value/(double)item.weight)*(w);
                w=0;
            }
        }
        System.out.println(w);
        System.out.println(w);
        return profit;
    }

    public static void main(String[] args){
        Item it1 = new Item(8,10);
        Item it2 = new Item(2,1);
        Item it3 = new Item(10,7);
        Item it4 = new Item(1,7);
        Item it5 = new Item(9,5);
        Item it6 = new Item(9,7);
        Item it7 = new Item(7,1);
        Item it8 = new Item(2,8);
        Item it9 = new Item(6,6);
        Item it10 = new Item(4,8);
        Item[] arr = {it1, it2,it3,it4,it5,it6,it7,it8,it9,it10};
        System.out.println(fractionalKnapsack(21, arr, 10));
    }
}
