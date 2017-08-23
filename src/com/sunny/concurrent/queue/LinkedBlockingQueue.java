package concurrent.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className concurrent.queue.LinkedBlockingQueue
 * @date 2017-08-23 11:37
 * @description:  阻塞式容器（无界队列）
 */
public class LinkedBlockingQueue {
    static BlockingQueue<String> strs = new java.util.concurrent.LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);//容器满了就等待
                    TimeUnit.MICROSECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"pl").start();

        
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for(;;){
                    try {
                        System.out.println(Thread.currentThread().getName()+" take -"+strs.take()); //容器空了就等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }

    }
}
