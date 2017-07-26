package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunny
 * @className reentrantlock.Reentrantlock1
 * @date 2017-07-26 15:47
 * @description:   公平锁（根据线程等待的时间排序`等待时间长`的优先执行）
 */
public class Reentrantlock3 {
    //ReentrantLock lock = new ReentrantLock();// 
    ReentrantLock lock = new ReentrantLock(true);// 创建一个公平锁

    void m1() {
        for (int i=0;i<100;i++){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" get lock");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }




    public static void main(String[] args) {
        Reentrantlock3 reentrantlock3=new Reentrantlock3();

     
        new Thread(()->reentrantlock3.m1(),"t1").start();
        new Thread(()->reentrantlock3.m1(),"t2").start();

    }

}
