package demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className demo1.Container
 * @date 2017-07-26 10:36
 * @description:使用CountDownLatch(门闩)优化容器3的代码
 * CountDownLatch原理: 1.new CountDownLatch(1) 初始化一个(门闩)
 *                     2. latch.await()  如果当前计数大于零，则当前线程将被禁用以进行线程调度，并处于休眠状态
 *                     3.  latch.countDown() 减少锁存器的计数，如果计数达到零，释放所有等待的线程
 */
public class Container4 {
    volatile List list = new ArrayList<Integer>();

    public void add(Integer numb) {
        list.add(numb);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container4 container1 = new Container4();

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t2 启动");
            if (container1.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ">> t2结束");
        }, "t2").start();


        new Thread(() -> {
            System.out.println("t1 启动");

                for (int i = 0; i < 10; i++) {
                    container1.add(i);
                    System.out.println(Thread.currentThread().getName() + ">> add" + i);
                    if (container1.size() == 5) {
                        //打开门闩，让t2执行
                        latch.countDown();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


        }, "t1").start();

    }

}
