package design;

public class DeadlockSimulation {

    // Resource 1
    static class Resource1 {
        synchronized void method1(Resource2 resource2) {
            System.out.println(Thread.currentThread().getName() + " entered Resource1 method1");
            try {
                // Simulate work with sleep
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource2.lastMethod();
        }

        synchronized void lastMethod() {
            System.out.println(Thread.currentThread().getName() + " is in Resource1 lastMethod");
        }
    }

    // Resource 2
    static class Resource2 {
        synchronized void method2(Resource1 resource1) {
            System.out.println(Thread.currentThread().getName() + " entered Resource2 method2");
            try {
                // Simulate work with sleep
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource1.lastMethod();
        }

        synchronized void lastMethod() {
            System.out.println(Thread.currentThread().getName() + " is in Resource2 lastMethod");
        }
    }

    public static void main(String[] args) {
        // Create two resources
        final Resource1 resource1 = new Resource1();
        final Resource2 resource2 = new Resource2();

        // Thread 1: Acquiring resource1 and then resource2
        Thread thread1 = new Thread(() -> {
            resource1.method1(resource2);
        }, "Thread-1");

        // Thread 2: Acquiring resource2 and then resource1
        Thread thread2 = new Thread(() -> {
            resource2.method2(resource1);
        }, "Thread-2");

        // Start the threads
        thread1.start();
        thread2.start();
    }
}
