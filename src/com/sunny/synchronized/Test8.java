import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test8
 * @date 2017-07-25 15:06
 * @description:出现异常时，锁会被释放，异常处理不好就会出现数据不一致问题
 * eg:下面是一个死循环，如果不出现异常t2永远不会被执行
 *
 */
public class Test8 {
    int count = 0;

    synchronized void method() {
        System.out.println("method start...");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                //try {
                    int i = 1 / 0;//这里会抛出异常，锁被释放，要是不想释放锁，可以进行try catch，然后循环继续
               // } catch (Exception e) {
                //    e.printStackTrace();
                System.out.println("异常处理后..");
               // }
            }
        }
    }

    public static void main(String[] args) {
        Test8 test8=new Test8();
        new Thread(()->test8.method(),"t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->test8.method(),"t2").start();
    }
}
