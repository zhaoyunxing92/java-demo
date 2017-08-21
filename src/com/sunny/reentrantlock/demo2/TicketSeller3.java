package reentrantlock.demo2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author sunny
 * @className reentrantlock.demo2.TicketSeller1
 * @date 2017-07-28 10:04
 * @description: 使用队列优化代码 >> 并发连接队列
 */
public class TicketSeller3 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号：" + i);
           // tickets.add(null);  // 禁止插入null值，会抛空指针异常
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();//先进先出  获取不到就是null
                    if (s == null) break;
                    System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
