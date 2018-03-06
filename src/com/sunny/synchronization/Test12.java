package synchronization;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test12
 * @date 2017-07-25 22:09
 * @description:多线程随机数问题   ThreadLocalRandom（Java 1.7后解决多线程能够解决多个线程发生的竞争争夺）
 */
public class Test12 /*implements Runnable*/{
    //private static Random random = new Random();
    public  void run() {
        //ThreadLocalRandom.current()是返回当前线程的 ThreadLocalRandom ，是内部使用的
        //当所有用法都是这种形式时，绝对不可能跨多个线程共享一个ThreadLocalRandom
        // java api https://blog.fondme.cn/apidoc/jdk-1.8-google/
        System.out.println( ThreadLocalRandom.current().nextInt(4,77));
       // System.out.println( random.nextInt(4));
    }

    public static void main(String[] args) {
        Test12 test12=new Test12();
        for(int n = 1; n < 10; n++) {
            new Thread(()->test12.run()).start();
        }
    }
}
