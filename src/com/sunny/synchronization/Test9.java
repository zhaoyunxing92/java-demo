package synchronization;

import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test9
 * @date 2017-07-25 15:31
 * @description: volatile关键字，Volatile是轻量级的synchronized，它在多处理器开发中保证了共享变量的“可见性。
 * A B两个线程使用一个变量，Java默认是A线程中保留一份copy，这时假如线程B改变了变量，则线程A就不知道了，使用volatile关键字就是保证了线程A获取到的是B线程修改后的值
 * volatile会强制所有线程都去内存读取变量值
 * 使用建议：在两个或者更多的线程访问的成员变量上使用volatile。当要访问的变量已在synchronized代码块中，或者为常量时，不必使用。
 */
public class Test9 {

    /*volatile*/ boolean runnig = true;

    void m() {
        System.out.println(Thread.currentThread().getName() + "m start...");
        while (runnig) {
            //System.out.println("我在运行.....");
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        Test9 test9 = new Test9();
        new Thread(() -> test9.m(), "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test9.runnig = false;
        System.out.println("修改后的值：" + test9.runnig);

    }
}

/**
 * 即使下面这样  线程 t2也是获取不到修改后的running值
 */
class Test99 {
    public static  boolean runing = true;

    public  static void m2() {
        System.out.println(Thread.currentThread().getName() + "m2 start...");
        while (runing) {

        }
        System.out.println("m2 end");
    }

    public static void main(String[] args) {

        new Thread(() -> Test99.m2(), "t2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Test99.runing = false;


    }
}
