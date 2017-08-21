package reentrantlock.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunny
 * @className reentrantlock.demo2.TicketSeller1
 * @date 2017-07-28 10:04
 * @description:  买火车票的例子代码
 * //   ArrayList里面的方法都不是同步的
 * // 
 */
public class TicketSeller1 {
    static List<String> tickets=new ArrayList<>();
    static {
        for (int i=0;i<1000;i++){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<100000;i++){
           new Thread(()->{
               while (tickets.size()>0){
                   System.out.println("销售了--"+tickets.remove(0));
               }
           }).start();
        }
    }
}
