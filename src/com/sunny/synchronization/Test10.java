package synchronization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test
 * @date 2017-07-25 16:53
 * @description:
 */
public class Test10 {
    volatile int count = 0;

    /*synchronized*/ void method() {
        System.out.println(Thread.currentThread().getName()+" start...");
        for (int i = 0; i < 1000; i++) {
            count++;    //  
        }
    }

    public static void main(String[] args) {
        Test10 test10 = new Test10();
        List<Thread> threads = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(test10::method, "thread" + i));
        }
        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("count = " + test10.count);
        
    }
}
