package reentrantlock.demo2;

import java.util.Vector;

/**
 * @author sunny
 * @className reentrantlock.demo2.TicketSeller1
 * @date 2017-07-28 10:04
 * @description: 买火车票的例子代码
 * //   ArrayList里面的方法都不是同步的
 * //   vector 同步容器 具备原子性
 */
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    //虽然vector容器是原子性的但是  tickets.size() 和 tickets.remove()之间时间段还是存在
//                   try {
//                       TimeUnit.MILLISECONDS.sleep(10);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }

                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
