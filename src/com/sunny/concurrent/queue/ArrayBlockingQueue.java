package concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * @author sunny
 * @className concurrent.queue.ArrayBlockingQueue
 * @date 2017-08-23 14:05
 * @description:
 */
public class ArrayBlockingQueue {

    static BlockingQueue<String> queue = new java.util.concurrent.ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        
        for (int i = 0; i < 10; i++) {
            queue.put("a" + i);
        }

       // queue.add("容器满了继续添加会异常");

      //  queue.offer("添加不会异常，但满了添加不了");

       // queue.offer("时间段阻塞，1s添加不进去就放弃",1, TimeUnit.MICROSECONDS);

         queue.put("容器满了就阻塞");

        System.out.println(queue);


    }

}
