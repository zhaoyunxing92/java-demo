package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author sunny
 * @className list.CopyOnWriteList
 * @date 2017-08-22 19:54
 * @description: 写时复制链表
 */
public class CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists =
                new ArrayList<>();//线程不安全
                 //new Vector<>();
                //new CopyOnWriteArrayList<>();  //插入加锁，读取不加锁

        Random r = new Random();
        Thread[] ths = new Thread[100];//100个线程

        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a" + r.nextInt(1000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        long stat = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - stat));
    }
}
