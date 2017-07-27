package reentrantlock.demo1;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className reentrantlock.demo1.Container1
 * @date 2017-07-27 09:18
 * @description: 写一个固定容器量的同步容器，有 put、get、getCount方法，能支持2个生产者和10个消费者线程阻塞调用
 * 使用    wait()和 notifyAll()实现
 */
public class Container1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int max = 10; //最多是10个消费者
    private int count = 0;//当前有几个

    //生产者线程
    public synchronized void put(T t) {
        while (lists.size() == max) { //使用while而不使用if的原因是，this.wait()方法往下走的时候还会到while这里判断下，这样高并发的时候不容易错
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程可以消费

    }

    //消费者线程
    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll(); //锁释放，通知生产者生产。this.notify()是唤醒一个线程，这时有可能还是当前线程

        return t;
    }

    public static void main(String[] args) {
        Container1<String> container = new Container1<>();

        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者线程
        for(int i=0;i<2;i++){
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName()+" "+j);
                }
            }, "p" + i).start();
        }
    }
}
