package concurrent.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className concurrent.queue.DelayQueue
 * @date 2017-08-23 14:24
 * @description:  定时任务队列
 */
public class DelayQueue {
    static BlockingQueue<MyTask> tasks = new java.util.concurrent.DelayQueue();
    static Random r = new Random();

    private static class MyTask implements Delayed {
        long runningTime;

        MyTask(Long time) {
            this.runningTime = time;
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }
        @Override
        public String toString() {
            return runningTime + "";
        }

        public static void main(String[] args) throws InterruptedException {



            long now = System.currentTimeMillis();
            MyTask task1 = new MyTask(now + 1000);
            MyTask task2 = new MyTask(now + 3500);
            MyTask task3 = new MyTask(now + 2000);

            tasks.put(task1);
            tasks.put(task2);
            tasks.put(task3);

            System.out.println(tasks);

            for (int i = 0; i < 3; i++) {
                System.out.println(tasks.take());
            }


        }
    }
}
