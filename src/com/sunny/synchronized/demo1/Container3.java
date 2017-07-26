package demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className demo1.Container
 * @date 2017-07-26 10:36
 * @description:解决容器2的问题
 */
public class Container3 {
    volatile List list = new ArrayList<Integer>();

    public void add(Integer numb) {
        list.add(numb);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container3 container1 = new Container3();
        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 启动");
                if (container1.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ">> t2结束");
                lock.notify();  //t2执行完后释放锁
            }


        }, "t2").start();


        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    container1.add(i);
                    System.out.println(Thread.currentThread().getName() + ">> add" + i);
                    if (container1.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait(); //t1主动释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, "t1").start();

    }

}
