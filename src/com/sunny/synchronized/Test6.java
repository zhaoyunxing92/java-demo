import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test6
 * @date 2017-07-24 23:14
 * @description:
 * <p>
 * 一个同步方法可调用另一个同步方法
 * method1()已经获得了  Test6对象的锁，再次申请依然可以得到锁，也就是说synchronized获的锁是可 `重入`的
 * </p>
 */
public class Test6 {
    synchronized void method1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
        System.out.println("m1 end.....");
    }

    synchronized void method2() {
        System.out.println("m2 start.....");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end.....");
    }

    public static void main(String[] args) {
        Test6 t=new Test6();
        new Thread(()->t.method1()).start();
    }
}
