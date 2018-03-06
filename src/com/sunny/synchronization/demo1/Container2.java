package synchronization.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className demo1.Container
 * @date 2017-07-26 10:36
 * @description: 使用wait和notify解决上一个问题,但是这样只能等t1执行完了t2才能执行
 * 注释:wait会释放锁，notify不会释放锁
 */
public class Container2 {
    volatile List list = new ArrayList<Integer>();

    public void add(Integer numb) {
        list.add(numb);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container2 container1 = new Container2();
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
