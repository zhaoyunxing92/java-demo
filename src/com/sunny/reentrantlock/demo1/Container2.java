package reentrantlock.demo1;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunny
 * @className reentrantlock.demo1.Container1
 * @date 2017-07-27 09:18
 * @description: 容器2优化前代码 ,可以精确通知那个线程执行，不用唤醒全部线程 
 */
public class Container2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int max = 10; //最多是10个消费者
    private int count = 0;//当前有几个

    private ReentrantLock lock = new ReentrantLock();
    //生产者条件
    private Condition produceer = lock.newCondition();
    //消费者条件
    private Condition consumer = lock.newCondition();

    //生产者线程
    public  void put(T t) {
        try {
            lock.lock();
            while (lists.size() == max) {
                produceer.await();  //生产者等待
            }
            lists.add(t);
            ++count;
            consumer.signalAll();//通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //消费者线程
    public  T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();  //消费者等待
            }
            lists.add(t);
            ++count;
            produceer.signalAll();//通知生产者生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        Container2<String> container = new Container2<>();

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
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }
}
