package design.ProducerConsumerTask;

public class Main {
    public static void main(String[] args){
        SharedResource obj = new SharedResource();
        Thread t1 = new Thread(new ProducerTask(obj));
        Thread t2 = new Thread(new ConsumerTask(obj));

        t1.start();
        t2.start();
    }
}
