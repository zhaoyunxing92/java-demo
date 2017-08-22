package concurrent;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author sunny
 * @className concurrent.ConcurrentMap
 * @date 2017-08-21 20:46
 * @description:
 */
public class ConcurrentMap {


    public static void main(String[] args) {
        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        //Map<String, String> map = new ConcurrentSkipListMap<>();   //高并发下并排序插入
       // Map<String, String> map = new Hashtable<>();
        Random r = new Random();

        Thread[] ths = new Thread[100];//100个线程

        CountDownLatch latch = new CountDownLatch(ths.length);//100个门闩

        long stat = System.currentTimeMillis();

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {//每个线程放160000个数据
                for (int j = 0; j < 160000; j++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                    latch.countDown();
                }
            });
           
        }

        Arrays.asList(ths).forEach(t -> t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-stat));

    }
}
