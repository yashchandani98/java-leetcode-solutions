package design;


import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class TaskSchedulerWithSpecificTime {


    class ScheduledJob implements Comparable<ScheduledJob>{
        private Runnable task;
        private Long scheduledTime;

        public ScheduledJob(Runnable task, Long scheduledTime) {
            this.task = task;
            this.scheduledTime = scheduledTime;
        }

        public Long getScheduledTime() {
            return scheduledTime;
        }

        public Runnable getTask() {
            return task;
        }

        @Override
        public int compareTo(ScheduledJob that) {
            return Long.compare(this.scheduledTime, that.scheduledTime);
        }
    }


    class JobScheduler {

        private final PriorityQueue<ScheduledJob> queue;
        private final ReentrantLock lock;
        private final Condition newTaskArrived;
        private volatile boolean isRunning;

        public JobScheduler() {
            this.queue = new PriorityQueue<>();
            this.lock = new ReentrantLock();
            this.newTaskArrived = lock.newCondition();
            this.isRunning = true;

            Thread consumerThread = new Thread(this::process);
            consumerThread.start();
        }

        private void process() {
            while(isRunning) {
                lock.lock();
                try {
                    while(queue.isEmpty()) {
                        newTaskArrived.await();
                    }

                    ScheduledJob nextJob = queue.peek();
                    if(nextJob == null) continue;

                    long currentTs = System.currentTimeMillis();
                    if(nextJob.getScheduledTime() <= currentTs) {
                        queue.poll();
                        new Thread(nextJob.getTask()).start();
                    } else {
                        newTaskArrived.await(nextJob.getScheduledTime() - currentTs, TimeUnit.MILLISECONDS);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }

        public void shutdown() {
            isRunning = false;
            lock.lock();
            newTaskArrived.signalAll();
            lock.unlock();
        }

        public void schedule(Runnable task, long delayInMs) {
            long executionTs = System.currentTimeMillis() + delayInMs;
            ScheduledJob scheduledJob = new ScheduledJob(task, executionTs);
            lock.lock();
            try {
                queue.offer(scheduledJob);
                newTaskArrived.signal();
            } finally {
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        JobScheduler scheduler = new JobScheduler();
        scheduler.schedule(() -> System.out.println("Task 1 executed"), 1200);
        scheduler.schedule(() -> System.out.println("Task 2 executed"), 1400);
        scheduler.schedule(() -> System.out.println("Task 3 executed"), 500);

        Thread.sleep(3000);
        scheduler.shutdown();
*/
    }

}
