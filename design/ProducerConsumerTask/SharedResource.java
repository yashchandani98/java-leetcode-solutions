package design.ProducerConsumerTask;

import java.util.LinkedList;
import java.util.Queue;
public class SharedResource {


    Queue<Integer> data = new LinkedList<>();
    boolean isItemAvailable = false;

    int cap = 10;

    public synchronized void addItem(int item){
        System.out.println("Item is added by: " + Thread.currentThread().getName());
        // make the boolean flag as true
//        isItemAvailable = true;
        if(data.size() == cap){
            try{
                wait();
            } catch (Exception e){
                // handle exception here
            }
        }
        data.add(item);

        // notify all the waiting threads to consume an item.
        notifyAll();
    }


    public synchronized int consumeItem(){
        System.out.println("Item is consumed by: " + Thread.currentThread().getName());


//        while(!isItemAvailable){
//            try{
//                System.out.println("Thread:"+ Thread.currentThread().getName() + "is waiting now!");
//                wait();
//            } catch (Exception e){
//                System.out.println("Exception:"+e);
//            }
//        }

        while(data.isEmpty()){
            try{
                wait();
            } catch (Exception e){

            }
        }

        int item = data.poll();
        notifyAll();

        System.out.println("Item consumed : " + item);
        return item;
    }
}
