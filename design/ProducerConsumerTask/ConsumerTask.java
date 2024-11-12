package design.ProducerConsumerTask;

import javax.sound.midi.SysexMessage;

public class ConsumerTask implements Runnable{
    SharedResource obj;
    ConsumerTask(SharedResource runnableObject){
        this.obj = runnableObject;
    }

    @Override
    public void run(){
        System.out.println("Consumer Thread:" + Thread.currentThread().getName());
        obj.consumeItem();
    }
}
