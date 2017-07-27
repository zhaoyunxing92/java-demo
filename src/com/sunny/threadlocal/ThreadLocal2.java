package threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className threadlocal.ThreadLocal2
 * @date 2017-07-27 16:32
 * @description: ThreadLocal是使用空间换时间，synchronized是使用时间换空间，ThreadLocal效率会更高
 */
public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        },"t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
            //  System.out.println(p.name);
        },"t2").start();
    }
    
    static class Person {
        String name = "张三";
    }
}


