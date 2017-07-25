import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test11
 * @date 2017-07-25 20:33
 * @description: 如果只是加加减减的操作可以使用Atomxxx类
 *
 * Atomxxx类本身方法就是原子性的，但是不能保证多个方法连续调用的原子性
 */
public class Test11 {
    AtomicInteger count=new AtomicInteger(0);
    
    /*synchronized*/ void method() {
        System.out.println(Thread.currentThread().getName()+" start...");
        for (int i = 0; i < 1000; i++) {
           // if(count.get()<=100){   //假如加上这段代码应该不是出现大于100的情况，但是多个线程调用的时候还是出现101的情况
                count.incrementAndGet();  //代替 count++
           // }
        }
    }

    public static void main(String[] args) {
        Test11 test11 = new Test11();
        List<Thread> threads = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(test11::method, "thread" + i));
        }
        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("count = " + test11.count);

    }
}
