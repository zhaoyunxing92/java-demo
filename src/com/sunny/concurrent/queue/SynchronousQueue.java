package concurrent.queue;

import java.util.concurrent.BlockingQueue;

/**
 * @author sunny
 * @className concurrent.queue.SynchronousQueue
 * @date 2017-08-23 15:41
 * @description: 容量为0
 */
public class SynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new java.util.concurrent.SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //strs.add("直接添加会异常，因为容器为0") ;
        strs.put("阻塞等待消费者消费");
           
        System.out.println(strs.size());

    }
}
