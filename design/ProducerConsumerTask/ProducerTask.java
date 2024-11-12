package design.ProducerConsumerTask;

import java.util.concurrent.atomic.AtomicInteger;

public class ProducerTask implements Runnable{
    SharedResource obj;
    ProducerTask(SharedResource runnableObject){
        this.obj = runnableObject;
    }

    @Override
    public void run(){
//        AtomicInteger
        try{
            Thread.sleep(5000);
        } catch (Exception e){
            // handle exception here
        }
        obj.addItem(12);
    }
}
