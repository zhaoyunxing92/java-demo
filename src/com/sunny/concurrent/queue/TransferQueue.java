package concurrent.queue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author sunny
 * @className concurrent.queue.TransferQueue
 * @date 2017-08-23 14:58
 * @description:转让队列,没有消费者线程会阻塞
 */
public class TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

//        new Thread(() -> {
//            try {
//                System.out.println(strs.take());    //消费者线程先启动
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();


       // Boolean trys = strs.tryTransfer("aaa"); //尝试转让

        //Boolean time = strs.tryTransfer("5s内转让", 5000, TimeUnit.MICROSECONDS);


        strs.transfer("aaa");   //生产者，没有消费者线程会阻塞

        //消费者后启动，程序会阻塞
        new Thread(() -> {
            try {
                System.out.println(strs.take());    //消费者线程先启动
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
