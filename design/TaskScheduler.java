package design;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class TaskScheduler {

        interface Task extends Runnable {
        }

        // Data Structure to Store Task mapped with the scheduled timestamp
        private static class TaskTime implements Comparable<TaskTime> {
            public long timestampMilis;
            public Task task;

            TaskTime(long timestamp, Task task) {
                this.task = task;
                this.timestampMilis = timestamp;
            }

            @Override
            public int compareTo(TaskTime task) {
                long diff = timestampMilis - task.timestampMilis;

                if (diff > 0) return 1;

                return diff == 0 ? 0 : -1;
            }
        }

        public static class TimerQueue {
            private BlockingQueue<TaskTime> queue = new PriorityBlockingQueue<>();

            // add in the queue
            public void addTask(long timestampMillis, Task task) {
                queue.add(new TaskTime(timestampMillis, task));
            }

            public long getSonnestTimestampMillis() {
                TaskTime taskTime = queue.peek();
                if (taskTime == null) return 0;
                return taskTime.timestampMilis;
            }

            private Task popSoonestTask() {
                TaskTime taskTime = queue.poll();
                if (taskTime == null) return null;
                return taskTime.task;
            }
        }

        public static class TaskTimerRunnable implements Runnable {
            private static final int MAX_THREADS = 100;
            private TimerQueue queue;
            private ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);
            private Thread timerThread;

            public TaskTimerRunnable() {
                this.queue = new TimerQueue();
            }

            // Consumer consuming task
            @Override
            public void run() {
                timerThread = Thread.currentThread();
                while (true) {
                    long soonestTimestampMillis = queue.getSonnestTimestampMillis();
                    long currentTimestampMillis = System.currentTimeMillis();
                    if (soonestTimestampMillis <= currentTimestampMillis) {
                        pool.submit(queue.popSoonestTask());
                    } else {
                        sleep(soonestTimestampMillis - currentTimestampMillis);
                    }
                }
            }

            private void sleep(long millis) {
                try {
                    if (millis <= 0) {
                        Thread.sleep(Long.MAX_VALUE);
                    } else {
                        Thread.sleep(millis);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }

            public void addTask(long whenTimestampMillis, Task task) {
                queue.addTask(whenTimestampMillis, task);
                long soonestTimestampMillis = queue.getSonnestTimestampMillis();
                if (whenTimestampMillis <= soonestTimestampMillis) {
                    // interrupt timer thread
                    if (timerThread != null) {
                        timerThread.interrupt();
                    }
                }
            }
        }

        public static class TestTask implements Task {
            private long timestampMillis;

            public TestTask(long timestampMillis) {
                this.timestampMillis = timestampMillis;
            }

            @Override
            public void run() {
                System.out.println("Timestamp: " + timestampMillis);
            }
        }

        // Producer which produces the job.
        public static void main(String[] args) {
            TaskTimerRunnable timer = new TaskTimerRunnable();
            Thread timerThread = new Thread(timer);

            long currentTimestamp = System.currentTimeMillis();
            for (int i = 0; i < 20; i++) {
                long whenMillis = currentTimestamp + 5000 + i * 1000;
                // To-DO: Come back
                timer.addTask(whenMillis, new TestTask(whenMillis));
            }

            timerThread.start();

            try {
                timerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
