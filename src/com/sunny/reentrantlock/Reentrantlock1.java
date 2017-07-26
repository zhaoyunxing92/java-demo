package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunny
 * @className reentrantlock.Reentrantlock1
 * @date 2017-07-26 15:47
 * @description: ReentrantLock是手工锁，即便是出现异常了也不会释放
 */
public class Reentrantlock1 {
    ReentrantLock lock=new ReentrantLock();
    void m1(){
        try {
            lock.lock(); //相当于 synchronized (this)
            m3();
            System.out.println(Thread.currentThread().getName()+" >m1.....");
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
                if(i==5){
                    lock.unlock();//释放锁
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isLocked())
            lock.unlock();//释放锁
        }
    }
    void m2(){
        lock.lock();
        m3();
        System.out.println(Thread.currentThread().getName()+" >m2.....");
        lock.unlock();//释放锁 
    }
    void m3(){
       // lock.lock();
        System.out.println(Thread.currentThread().getName()+" >m3 线程被"+lock.toString()+"锁定");
        //lock.lock();
        System.out.println(Thread.currentThread().getName()+" >m3 锁["+lock.getHoldCount()+"]个数");
        //lock.unlock();//释放锁
    }

    public static void main(String[] args) {
        Reentrantlock1 reentrantlock1=new Reentrantlock1();

        new Thread(()->reentrantlock1.m1(),"t1").start();
        new Thread(()->reentrantlock1.m2(),"t2").start();
       // new Thread(()->reentrantlock1.m3(),"t3").start();
    }
}
