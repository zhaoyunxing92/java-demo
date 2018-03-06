package synchronization.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className demo1.Container
 * @date 2017-07-26 10:36
 * @description: 实现一个容器，两个方法 add size
 * 两个线程，线程1给容器添加数据，线程2监控容器个数，监控到5个的时候给出提示
 * <p>
 * 使用volatile关键字
 * 代码的问题：1.线程没有同步有可能t1已经到6了t2才执行
 *            2.t2一直循环消耗cpu
 *
 */
public class Container1 {
    volatile List list = new ArrayList<Integer>();

    public void add(Integer numb) {
        list.add(numb);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container1 container1 = new Container1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container1.add(i);
                System.out.println(Thread.currentThread().getName() + ">> add" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {//t2一直循环直到容器到5才停止
                if (container1.size() == 5) {
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + ">> t2结束");
        }, "t2").start();
    }

}
