package synchronization;

import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test13
 * @date 2017-07-26 09:38
 * @description: 锁定某个对象，如果对象的属性不变，不影响使用，但是如果对象改变成另外一个对象，则锁的对象改变
 */
public class Test13 {
   public static Object object = new Object();

    void m() {
        synchronized (object) { //锁定object对象
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Test13 t = new Test13();
        //第一个线程
        new Thread(() -> t.m(),"t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //第二个线程 ，假如锁定的对象不改变t2永远都不会执行
        Thread thread=new Thread(() -> t.m(),"t2");
         //重新new object对象
       t.object=new Object();

        thread.start();
    }
}
