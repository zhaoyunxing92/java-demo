package threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className threadlocal.ThreadLocal1
 * @date 2017-07-27 16:23
 * @description:  线程t1最后获取的值是t2修改过的
 */
public class ThreadLocal1 {
    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        },"t1").start();
        
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
          //  System.out.println(p.name);
        },"t1").start();
    }
    static class Person {
        String name = "张三";
    }
}

/*class Person {
    String name = "张三";
}*/
