package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunny
 * @className reentrantlock.Reentrantlock1
 * @date 2017-07-26 15:47
 * @description:   lock()和 lockInterruptibly()区别
 *    1.lock()不能被打断
 *    2.lockInterruptibly() 能打断
 */
public class Reentrantlock2 {
    
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

       Thread t1=new Thread(()->{
           try {
               lock.lock();
               System.out.println("t1 start..");
               TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); //t1永远睡觉
               System.out.println("t1 end..");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }finally {
               lock.unlock();
           }
       });
       t1.start();
       
        Thread t2=new Thread(()->{
            try {
              //  lock.lock();
                lock.lockInterruptibly();  //可以被t2.interrupt()打断
                System.out.println("t2 start..");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end..");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("sleep interrupted");
            }finally {
                if(lock.isLocked())
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1); //m2永远睡觉
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //t2打断自己线程
        t2.interrupt();
    }

}
