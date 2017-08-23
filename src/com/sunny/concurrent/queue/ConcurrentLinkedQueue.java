package concurrent.queue;

import java.util.Queue;

/**
 * @author sunny
 * @className concurrent.queue.ConcurrentLinkedQueue
 * @date 2017-08-22 23:44
 * @description:
 */
public class ConcurrentLinkedQueue {
    
    public static void main(String[] args) {
        Queue<String> queue=new java.util.concurrent.ConcurrentLinkedQueue<>();

        for( int i=0;i<10 ; i++ ){
            queue.offer("a"+i); //add
        }
        System.out.println(queue);
        System.out.println(queue.size());

        System.out.println(queue.poll());  //获取到数据并删除
        System.out.println(queue.size());

        System.out.println(queue.peek()); //只获取数据不删除
        System.out.println(queue.size());

      

        Queue<String> queue2=new java.util.concurrent.ConcurrentLinkedDeque<>();

     
    }

}
